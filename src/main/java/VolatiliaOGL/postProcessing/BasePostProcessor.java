package main.java.VolatiliaOGL.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import main.java.VolatiliaOGL.shaders.BaseShader;

public abstract class BasePostProcessor<T extends BaseShader>
{
	protected ImageRenderer renderer;
	protected T shader;

	public BasePostProcessor(ImageRenderer renderer, T shader)
	{
		this.renderer = renderer;
		this.shader = shader;
	}

	public void render(int texture)
	{
		shader.start();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		renderer.renderQuad();
		shader.stop();
	}

	public int getOutputTexture()
	{
		return renderer.getOutputTexture();
	}
	
	public void cleanUp()
	{
		renderer.cleanUp();
		shader.cleanUp();
	}
}
