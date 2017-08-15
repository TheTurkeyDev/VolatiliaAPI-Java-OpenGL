package main.java.VolatiliaOGL.postProcessing;

import main.java.VolatiliaOGL.shaders.postProcessing.gaussianBlur.VerticalBlurShader;

public class VerticalBlur extends BasePostProcessor<VerticalBlurShader>
{
	public VerticalBlur(int targetFboWidth, int targetFboHeight)
	{
		super(new ImageRenderer(targetFboWidth, targetFboHeight), new VerticalBlurShader());
		shader.start();
		shader.loadTargetHeight(targetFboHeight);
		shader.stop();
	}

	public void render(int texture)
	{
		super.render(texture);
	}
}
