package main.java.VolatiliaOGL.renderEngine;

import java.util.List;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.shaders.water.WaterShader;
import main.java.VolatiliaOGL.terrains.WaterFrameBuffers;
import main.java.VolatiliaOGL.terrains.WaterTile;
import main.java.VolatiliaOGL.util.Loader;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class WaterRenderer
{
	private static final String NORMAL_MAP = "textures/water/matchingNormalMap";
	private static final String DUDV_MAP = "textures/water/waterDUDV";
	private static final float WAVE_SPEED = 0.03f;

	private RawModel quad;
	private WaterShader shader;
	private WaterFrameBuffers fbos;

	private float moveFactor = 0;

	private int dudvTexture;
	private int normalMapTexture;

	public WaterRenderer(WaterShader shader, Matrix4f projectionMatrix, WaterFrameBuffers fbos)
	{
		this.shader = shader;
		this.fbos = fbos;
		this.dudvTexture = Loader.INSTANCE.loadTexture(DUDV_MAP);
		this.normalMapTexture = Loader.INSTANCE.loadTexture(NORMAL_MAP);
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.connectTextureUnits();
		shader.loadFogData(World.fogDensity, World.fogGradient);
		shader.stop();
		setUpVAO();
	}

	public void render(List<WaterTile> water, Camera camera, Light sun)
	{
		prepareRender(camera, sun);
		for(WaterTile tile : water)
		{
			Matrix4f modelMatrix = MathUtil.createTransformationMatrix(new Vector3f(tile.getX(), tile.getHeight(), tile.getZ()), 0, 0, 0, WaterTile.TILE_SIZE);
			shader.loadModelMatrix(modelMatrix);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, quad.getVertexCount());
		}
		unbind();
	}

	private void prepareRender(Camera camera, Light sun)
	{
		shader.start();
		shader.loadViewMatrix(camera);
		moveFactor += WAVE_SPEED * VolatiliaAPI.getFrameTimeSeconds();
		moveFactor %= 1;
		shader.loadMoveFactor(moveFactor);
		shader.loadLight(sun);
		shader.loadSkyColor(MasterRenderer.SKYRED, MasterRenderer.SKYGREEN, MasterRenderer.SKYBLUE);
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbos.getReflectionTexture());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbos.getRefractionTexture());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.dudvTexture);
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.normalMapTexture);
		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbos.getRefractionDepthTexture());
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	private void unbind()
	{
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	private void setUpVAO()
	{
		// Just x and z vectex positions here, y is set to 0 in v.shader
		float[] vertices = { -1, -1, -1, 1, 1, -1, 1, -1, -1, 1, 1, 1 };
		quad = Loader.INSTANCE.loadToVAO(vertices, 2);
	}

}
