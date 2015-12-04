package test.main.game;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Entity;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.entities.Player;
import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.models.TexturedModel;
import main.java.VolatiliaOGL.objLoading.NormalMappedObjLoader;
import main.java.VolatiliaOGL.objLoading.OBJLoader;
import main.java.VolatiliaOGL.terrains.Terrain;
import main.java.VolatiliaOGL.terrains.WaterTile;
import main.java.VolatiliaOGL.textures.ModelTexture;
import main.java.VolatiliaOGL.textures.TerrainTexture;
import main.java.VolatiliaOGL.textures.TerrainTexturePack;
import main.java.VolatiliaOGL.util.Loader;

import org.lwjgl.util.vector.Vector3f;

public class Game
{
	Player player;
	Camera camera;

	World world = new World(0);
	
	public Game()
	{

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

		TerrainTexture backgroundTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/grassy2"));
		TerrainTexture rTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/mud"));
		TerrainTexture gTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/flowers"));
		TerrainTexture bTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/path"));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/blendMap"));
		
		player = new Player(bunny, new Vector3f(153, 5, 0), 0, 0, 0, 0.6f);
		camera = new Camera(player);
		
		world.addTerrain(new Terrain(0, 0, texturePack, blendMap, "textures/terrain/heightMap"));
		world.addTerrain(new Terrain(0, -1, texturePack, blendMap, "textures/terrain/heightMap"));
		world.addWater(new WaterTile(75, 125, 0));
		//world.addWater(new WaterTile(150, 125, 5));
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
	}
	
	public void cleanUp()
	{
		world.cleanUp();
	}
}
