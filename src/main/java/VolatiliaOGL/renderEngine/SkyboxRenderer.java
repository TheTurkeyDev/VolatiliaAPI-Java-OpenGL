package main.java.VolatiliaOGL.renderEngine;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.shaders.skybox.SkyboxShader;
import main.java.VolatiliaOGL.util.Loader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

public class SkyboxRenderer
{
	private static final float SIZE = 500f;

	private static final float[] VERTICES = { -SIZE, SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE,

	-SIZE, -SIZE, SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, -SIZE, SIZE,

	SIZE, -SIZE, -SIZE, SIZE, -SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, -SIZE, SIZE, -SIZE, -SIZE,

	-SIZE, -SIZE, SIZE, -SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, -SIZE, SIZE, -SIZE, -SIZE, SIZE,

	-SIZE, SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE, -SIZE,

	-SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, -SIZE, SIZE, -SIZE, -SIZE, -SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE };

	private static String[] TEXTURE_FILES = { "right", "left", "top", "bottom", "back", "front" };
	private static String[] NIGHT_TEXTURE_FILES = { "nightRight", "nightLeft", "nightTop", "nightBottom", "nightBack", "nightFront" };

	private RawModel cube;
	private int texture;
	private int nightTexture;
	private SkyboxShader shader;

	public SkyboxRenderer()
	{
		cube = Loader.INSTANCE.loadToVAO(VERTICES, 3);
		texture = Loader.INSTANCE.loadCubeMap(TEXTURE_FILES);
		nightTexture = Loader.INSTANCE.loadCubeMap(NIGHT_TEXTURE_FILES);
		shader = new SkyboxShader();
		shader.start();
		shader.connectTextureUnits();
		shader.stop();
	}

	public void render(Camera camera, float r, float g, float b)
	{
		shader.start();
		shader.loadViewMatrix(camera);
		shader.loadFogColor(r, g, b);
		GL30.glBindVertexArray(cube.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		bindTextures();
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	private void bindTextures()
	{
		int texture1 = texture;
		int texture2 = nightTexture;
		float blendFactor = 0;

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture1);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture2);
		shader.loadBlendFactor(blendFactor);
	}
	
	public void updateProjectionMatrix(Matrix4f projectionMatrix)
	{
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
}
