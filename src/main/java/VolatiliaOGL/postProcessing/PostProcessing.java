package main.java.VolatiliaOGL.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.shaders.postProcessing.PostProcessingShader;
import main.java.VolatiliaOGL.util.Loader;

public class PostProcessing
{

	private static final float[] POSITIONS = { -1, 1, -1, -1, 1, 1, 1, -1 };
	private static RawModel quad = Loader.INSTANCE.loadToVAO(POSITIONS, 2);
	private static PostProcessingShader shader = new PostProcessingShader();
	private static ImageRenderer renderer = new ImageRenderer();;

	public static void doPostProcessing(int texture)
	{
		start();
		shader.start();
		shader.updateOffset();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		renderer.renderQuad();
		shader.stop();
		end();
	}

	public static void cleanUp()
	{
		renderer.cleanUp();
		shader.cleanUp();
	}

	private static void start()
	{
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	private static void end()
	{
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public static void changeContrast(boolean toggle)
	{
		shader.start();
		shader.setContrastChanges(toggle);
		shader.stop();
	}

	public static void changeColor(boolean toggle)
	{
		shader.start();
		shader.setColorChanges(toggle);
		shader.stop();
	}
	
	public static void useWaterEffect(boolean toggle)
	{
		shader.start();
		shader.setUseWaterEffect(toggle);
		shader.stop();
	}
	
	public static void useGrayScale(boolean toggle)
	{
		shader.start();
		shader.setUseGrayScale(toggle);
		shader.stop();
	}
}