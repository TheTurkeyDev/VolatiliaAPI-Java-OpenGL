package test.main.game;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.game.GameBase;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.shaders.StaticShader;
import main.java.VolatiliaOGL.graphics.textures.Texture;
import main.java.VolatiliaOGL.graphics.textures.TextureManager;
import main.java.VolatiliaOGL.map.Map;
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
		Map map = new Map(1, 1, 1);
		
		super.setMap(map);

		light = new LightEntity(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		view = new DisplayView();
		
		dragon = new Entity(map);
		dragon.setLocation(new Vector3f(0, 0, -25));
		TexturedModel model = new TexturedModel(OBJFileLoader.loadOBJFile(TestGame.class, "/models/dragon.obj"), new Texture(TextureManager.INSTANCE.loadTexture(TestGame.class, "/textures/modelTextures/white.png")));
		dragon.setTextureModel(model);
		map.spawnEntity(dragon);
	}

	public void render()
	{
		StaticShader shader = StaticShader.INSTANCE;
		shader.start();
		shader.loadLight(light);
		shader.loadViewMatrix(view);
		super.render();
		shader.stop();
	}
}
