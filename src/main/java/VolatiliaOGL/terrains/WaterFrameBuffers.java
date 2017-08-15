package main.java.VolatiliaOGL.terrains;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import main.java.VolatiliaOGL.util.Fbo;

public class WaterFrameBuffers
{
	protected static final int REFLECTION_WIDTH = 320;
	private static final int REFLECTION_HEIGHT = 180;

	protected static final int REFRACTION_WIDTH = 1280;
	private static final int REFRACTION_HEIGHT = 720;

	private Fbo reflectionFbo;
	private Fbo refractionFbo;

	public WaterFrameBuffers()
	{// call when loading the game
		reflectionFbo = new Fbo(REFLECTION_WIDTH, REFLECTION_HEIGHT, Fbo.DEPTH_TEXTURE);
		refractionFbo = new Fbo(REFRACTION_WIDTH, REFRACTION_HEIGHT, Fbo.DEPTH_TEXTURE);
	}

	public void cleanUp()
	{// call when closing the game
		reflectionFbo.cleanUp();
		refractionFbo.cleanUp();
	}

	public void bindReflectionFrameBuffer()
	{// call before rendering to this FBO
		reflectionFbo.bindFrameBuffer();
	}

	public void bindRefractionFrameBuffer()
	{// call before rendering to this FBO
		refractionFbo.bindFrameBuffer();
	}

	public void unbindCurrentFrameBuffer()
	{// call to switch to default frame buffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

	public int getReflectionTexture()
	{// get the resulting texture
		return reflectionFbo.getColourTexture();
	}

	public int getRefractionTexture()
	{// get the resulting texture
		return refractionFbo.getColourTexture();
	}

	public int getRefractionDepthTexture()
	{// get the resulting depth texture
		return refractionFbo.getDepthTexture();
	}
}