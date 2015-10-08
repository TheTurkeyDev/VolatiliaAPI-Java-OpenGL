package test.main.game;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.game.GameBase;
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

		light = new LightEntity(new Vector3f(10, 10, -60), new Vector3f(1, 1, 1));
		view = new DisplayView();

		RenderManager.setLight(light);
		RenderManager.setDisplayView(view);

		TexturedModel model = new TexturedModel(OBJFileLoader.loadOBJFile(TestGame.class, "/models/dragon.obj"), new Texture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/modelTextures/white.png")));
		model.getModelData().setShineDampen(10);
		model.getModelData().setRefelction(0.25f);
		
		dragon = new Entity(world);
		dragon.setLocation(new Vector3f(100, 100, -75));
		dragon.setTextureModel(model);
		world.spawnEntity(dragon);
	}
}
