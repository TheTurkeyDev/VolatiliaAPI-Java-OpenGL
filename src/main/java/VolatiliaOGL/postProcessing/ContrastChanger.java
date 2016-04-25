package main.java.VolatiliaOGL.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import main.java.VolatiliaOGL.shaders.postProcessing.PostProcessingShader;

public class ContrastChanger
{
	private ImageRenderer renderer;
	private PostProcessingShader shader;
	
	public ContrastChanger()
	{
		renderer = new ImageRenderer();
		shader = new PostProcessingShader();	
	}
	
	public void render(int texture)
	{
		shader.start();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		renderer.renderQuad();
		shader.stop();
	}
	
	public void cleanUp()
	{
		renderer.cleanUp();
		shader.cleanUp();
	}
}
