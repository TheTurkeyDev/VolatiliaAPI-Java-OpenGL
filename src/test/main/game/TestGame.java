package test.main.game;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.game.GameBase;
import main.java.VolatiliaOGL.game.Terrain;
import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.renderers.RenderManager;
import main.java.VolatiliaOGL.graphics.textures.TerrainTexture;
import main.java.VolatiliaOGL.graphics.textures.TerrainTexturePack;
import main.java.VolatiliaOGL.graphics.textures.Texture;
import main.java.VolatiliaOGL.graphics.textures.TextureManager;
import main.java.VolatiliaOGL.player.DisplayView;
import main.java.VolatiliaOGL.util.OBJFileLoader;

import org.lwjgl.util.vector.Vector3f;

public class TestGame extends GameBase
{
	private LightEntity light;
	private DisplayView view;
	private Entity dragon;

	public TestGame()
	{
		World world = new World(1, 1, 1);

		super.setWorld(world);

		light = new LightEntity(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));
		view = new DisplayView();
		view.setY(5);

		RenderManager.setLight(light);
		RenderManager.setDisplayView(view);
		
		TerrainTexture bgT = new TerrainTexture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/terrain/grassy2.png"));
		TerrainTexture rT = new TerrainTexture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/terrain/mud.png"));
		TerrainTexture bT = new TerrainTexture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/terrain/grassFlowers.png"));
		TerrainTexture gT = new TerrainTexture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/terrain/path.png"));

		TerrainTexturePack pack = new TerrainTexturePack(bgT, rT, gT, bT);
		TerrainTexture bm = new TerrainTexture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/terrain/blendMap/blendMap.png"));
		
		Terrain t1 = new Terrain(0, -1, pack, bm);
		Terrain t2 = new Terrain(-1, -1, pack, bm);
		
		world.addTerrain(t1);
		world.addTerrain(t2);
		
		TexturedModel model = new TexturedModel(OBJFileLoader.loadOBJFile(TestGame.class, "/models/dragon"), new Texture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/modelTextures/white.png")));
		model.getModelData().setShineDampen(10);
		model.getModelData().setRefelction(0.25f);
		
		dragon = new Entity(world);
		dragon.setLocation(new Vector3f(0, -5, -15));
		dragon.setTextureModel(model);
		// world.spawnEntity(dragon);
	}
}
