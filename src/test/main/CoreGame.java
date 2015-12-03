package test.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Entity;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.entities.Player;
import main.java.VolatiliaOGL.gui.text.FontType;
import main.java.VolatiliaOGL.gui.text.GuiText;
import main.java.VolatiliaOGL.gui.text.TextMaster;
import main.java.VolatiliaOGL.models.ModelData;
import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.models.TexturedModel;
import main.java.VolatiliaOGL.objLoading.NormalMappedObjLoader;
import main.java.VolatiliaOGL.objLoading.OBJLoader;
import main.java.VolatiliaOGL.renderEngine.GuiRenderer;
import main.java.VolatiliaOGL.renderEngine.MasterRenderer;
import main.java.VolatiliaOGL.renderEngine.WaterRenderer;
import main.java.VolatiliaOGL.shaders.water.WaterShader;
import main.java.VolatiliaOGL.terrains.Terrain;
import main.java.VolatiliaOGL.terrains.WaterFrameBuffers;
import main.java.VolatiliaOGL.terrains.WaterTile;
import main.java.VolatiliaOGL.textures.ModelTexture;
import main.java.VolatiliaOGL.textures.TerrainTexture;
import main.java.VolatiliaOGL.textures.TerrainTexturePack;
import main.java.VolatiliaOGL.util.Loader;
import main.java.VolatiliaOGL.util.MousePicker;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class CoreGame
{

	public static void main(String[] args)
	{
		VolatiliaAPI.createDisplay();

		TextMaster.init();

		FontType font = new FontType(Loader.INSTANCE.loadTexture("font/TestFont"), new File("res/font/TestFont.fnt"));
		GuiText text = new GuiText("This is a test >:)", 3, font, new Vector2f(0.0f, 0.4f), 1f, true);
		text.setColor(0.1f, 0.1f, 0.1f);

		ModelData data = OBJLoader.loadOBJ("models/dragon");
		RawModel model = Loader.INSTANCE.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		ModelTexture texture = new ModelTexture(Loader.INSTANCE.loadTexture("textures/models/white"));
		TexturedModel tmodel = new TexturedModel(model, texture);
		texture.setReflectivity(1);
		texture.setShineDamper(10);

		ModelData bunnyModel = OBJLoader.loadOBJ("models/stanfordBunny");
		RawModel bunnyRawModel = Loader.INSTANCE.loadToVAO(bunnyModel.getVertices(), bunnyModel.getTextureCoords(), bunnyModel.getNormals(), bunnyModel.getIndices());
		ModelTexture bunnyTexture = new ModelTexture(Loader.INSTANCE.loadTexture("textures/models/white"));
		TexturedModel bunny = new TexturedModel(bunnyRawModel, bunnyTexture);

		TexturedModel barrel = new TexturedModel(NormalMappedObjLoader.loadOBJ("models/barrel"), new ModelTexture(Loader.INSTANCE.loadTexture("textures/models/barrel")));
		barrel.getTexture().setNormalMapID(Loader.INSTANCE.loadTexture("textures/normalMaps/barrelNormal"));
		barrel.getTexture().setShineDamper(10);
		barrel.getTexture().setReflectivity(0.5f);

		Entity dragon = new Entity(tmodel, new Vector3f(50, 0, -50), 0, 0, 0, 1);

		List<Light> lights = new ArrayList<Light>();
		lights.add(new Light(new Vector3f(0, 1000, -7000), new Vector3f(1f, 1f, 1f)));

		TerrainTexture backgroundTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/grassy2"));
		TerrainTexture rTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/mud"));
		TerrainTexture gTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/flowers"));
		TerrainTexture bTexture = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/path"));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(Loader.INSTANCE.loadTexture("textures/terrain/blendMap"));

		Terrain terrain = new Terrain(0, 0, texturePack, blendMap, "textures/terrain/heightMap");
		Terrain terrain2 = new Terrain(0, -1, texturePack, blendMap, "textures/terrain/heightMap");

		Player player = new Player(bunny, new Vector3f(153, 5, 0), 0, 0, 0, 0.6f);
		Camera camera = new Camera(player);

		MasterRenderer renderer = new MasterRenderer();
		GuiRenderer guiRenderer = new GuiRenderer();

		WaterFrameBuffers fbos = new WaterFrameBuffers();
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(waterShader, renderer.getProjectionMatrix(), fbos);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		WaterTile water = new WaterTile(75, 125, 0);
		waters.add(water);

		MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix());

		List<Entity> entities = new ArrayList<Entity>();
		entities.add(dragon);
		entities.add(player);

		List<Entity> normalEntities = new ArrayList<Entity>();
		normalEntities.add(new Entity(barrel, new Vector3f(75, 10, -75), 0, 0, 0, 1f));

		List<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(terrain);
		terrains.add(terrain2);

		while(!Display.isCloseRequested())
		{
			camera.move();
			player.move(terrain);
			picker.update();

			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);

			fbos.bindReflectionFrameBuffer();
			float distance = 2 * (camera.getPosition().y - water.getHeight());
			camera.getPosition().y -= distance;
			camera.invertPitch();
			renderer.renderScene(entities, normalEntities, terrains, lights, camera, new Vector4f(0, 1, 0, -water.getHeight() + 1f));
			camera.getPosition().y += distance;
			camera.invertPitch();

			fbos.bindRefractionFrameBuffer();
			renderer.renderScene(entities, normalEntities, terrains, lights, camera, new Vector4f(0, -1, 0, water.getHeight()));

			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			fbos.unbindCurrentFrameBuffer();
			renderer.renderScene(entities, normalEntities, terrains, lights, camera, new Vector4f(0, -1, 0, 100000));
			waterRenderer.render(waters, camera, lights.get(0));

			TextMaster.render();

			VolatiliaAPI.updateDisplay();
		}

		renderer.cleanUp();
		guiRenderer.cleanUp();
		waterShader.cleanUp();

		TextMaster.cleanUp();

		Loader.INSTANCE.CleanUp();

		fbos.cleanUp();

		VolatiliaAPI.closeDisplay();
	}
}
