package test.main.game;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.game.GameBase;
import main.java.VolatiliaOGL.game.Terrain;
import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.renderers.RenderManager;
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
		
		for(int x = -1; x < 2; x++)
		{
			for(int z = -1; z < 2; z++)
			{
				Terrain terrain = new Terrain(x, z, new Texture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/modelTextures/white.png")));
				world.addTerrain(terrain);
			}
		}

		TexturedModel model = new TexturedModel(OBJFileLoader.loadOBJFile(TestGame.class, "/models/dragon.obj"), new Texture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/modelTextures/white.png")));
		model.getModelData().setShineDampen(10);
		model.getModelData().setRefelction(0.25f);
		
		dragon = new Entity(world);
		dragon.setLocation(new Vector3f(0, 0, -10));
		dragon.setTextureModel(model);
		world.spawnEntity(dragon);
	}
}
