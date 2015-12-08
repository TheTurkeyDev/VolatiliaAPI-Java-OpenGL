package test.main.game;

import java.awt.Color;
import java.io.File;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Entity;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.entities.Player;
import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.gui.text.FontType;
import main.java.VolatiliaOGL.gui.text.GuiText;
import main.java.VolatiliaOGL.models.TexturedModel;
import main.java.VolatiliaOGL.objLoading.NormalMappedObjLoader;
import main.java.VolatiliaOGL.objLoading.OBJLoader;
import main.java.VolatiliaOGL.screen.Screen;
import main.java.VolatiliaOGL.terrains.Terrain;
import main.java.VolatiliaOGL.terrains.WaterTile;
import main.java.VolatiliaOGL.textures.ModelTexture;
import main.java.VolatiliaOGL.textures.TerrainTexture;
import main.java.VolatiliaOGL.textures.TerrainTexturePack;
import main.java.VolatiliaOGL.util.Loader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Game
{
	private Player player;
	private Camera camera;

	private World world = new World(0);
	
	private Screen screen;
	private FontType font = new FontType(Loader.INSTANCE.loadTexture("font/TestFont"), new File("res/font/TestFont.fnt"));
	private GuiText text = new GuiText("This is a test >:)", 3, font, new Vector2f(0.0f, 0.0f), 1f, true);
	
	private float color = 0, boarder = 0;
	private boolean up = true;
	
	public Game(Screen screen)
	{
		this.screen = screen;
		this.screen.addText(text);
	}

	public void loadGame()
	{
		TexturedModel dragon = new TexturedModel(OBJLoader.loadOBJ("models/dragon"), new ModelTexture(Loader.INSTANCE.loadTexture("textures/models/white")));
		dragon.getTexture().setReflectivity(1);
		dragon.getTexture().setShineDamper(10);

		TexturedModel bunny = new TexturedModel(OBJLoader.loadOBJ("models/stanfordBunny"), new ModelTexture(Loader.INSTANCE.loadTexture("textures/models/white")));

		TexturedModel barrel = new TexturedModel(NormalMappedObjLoader.loadOBJ("models/barrel"), new ModelTexture(Loader.INSTANCE.loadTexture("textures/models/barrel")));
		barrel.getTexture().setNormalMapID(Loader.INSTANCE.loadTexture("textures/normalMaps/barrelNormal"));
		barrel.getTexture().setShineDamper(10);
		barrel.getTexture().setReflectivity(0.5f);

		TerrainTexturePack texturePack = new TerrainTexturePack("textures/terrain/grassy2", "textures/terrain/mud", "textures/terrain/flowers", "textures/terrain/path");
		TerrainTexture blendMap = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/blendMap"));
		
		player = new Player(bunny, new Vector3f(153, 5, 0), 0, 0, 0, 0.6f);
		camera = new Camera(player);
		
		world.addTerrain(new Terrain(0, 0, texturePack, blendMap, "textures/terrain/heightMap"));
		world.addTerrain(new Terrain(0, -1, texturePack, blendMap, "textures/terrain/heightMap"));
		world.addWater(new WaterTile(75, 80, -2));
		world.addWater(new WaterTile(195, 80, -2));
		world.addWater(new WaterTile(125, 200, -2));
		world.addEntityToWorld(new Entity(dragon, new Vector3f(50, 0, -50), 0, 0, 0, 1));
		world.addEntityToWorld(player);
		world.addNormalEntityToWorld(new Entity(barrel, new Vector3f(75, 10, -75), 0, 0, 0, 1f));
		world.setSun(new Light(new Vector3f(0, 1000, -7000), new Vector3f(1f, 1f, 1f)));
	}
	
	public void render()
	{
		camera.move();
		player.move(world.getTerrainAt(player.getPosition().x, player.getPosition().z));
		
		world.renderWorld(camera);
		
		color += 0.005;
		Color tmpClr = new Color(Color.HSBtoRGB(color, 0.5F, 1f));
		text.setColor(new Vector3f(tmpClr.getRed() / 255F, tmpClr.getGreen() / 255F, tmpClr.getBlue() / 255F));
		if(up)
		{
			boarder += 0.01;
			if(boarder > 0.75)
				up = false;
		}
		else
		{
			boarder -= 0.01;
			if(boarder < 0.5)
				up = true;
		}
		text.setBoarderWidth(boarder);
	}
	
	public void cleanUp()
	{
		world.cleanUp();
	}
}
