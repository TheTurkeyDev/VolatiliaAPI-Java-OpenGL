package main.java.VolatiliaOGL.postProcessing;

import main.java.VolatiliaOGL.shaders.postProcessing.grayScale.GrayScaleShader;

public class GrayScale extends BasePostProcessor<GrayScaleShader>
{
	public GrayScale(int targetFboWidth, int targetFboHeight)
	{
		super(new ImageRenderer(targetFboWidth, targetFboHeight), new GrayScaleShader());
	}

	public void render(int texture)
	{
		super.render(texture);
	}
}