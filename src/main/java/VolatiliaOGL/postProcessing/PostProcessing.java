package main.java.VolatiliaOGL.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.util.Loader;

public class PostProcessing
{

	private static final float[] POSITIONS = { -1, 1, -1, -1, 1, 1, 1, -1 };
	private static RawModel quad = Loader.INSTANCE.loadToVAO(POSITIONS, 2);
	private static ContrastChanger contrastChanger = new ContrastChanger();

	public static void doPostProcessing(int texture)
	{
		start();
		contrastChanger.render(texture);
		end();
	}

	public static void cleanUp()
	{

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

}
