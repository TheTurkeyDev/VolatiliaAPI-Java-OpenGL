package main.java.VolatiliaOGL.postProcessing;

import main.java.VolatiliaOGL.shaders.postProcessing.gaussianBlur.HorizontalBlurShader;

public class HorizontalBlur extends BasePostProcessor<HorizontalBlurShader>
{
	public HorizontalBlur(int targetFboWidth, int targetFboHeight)
	{
		super(new ImageRenderer(targetFboWidth, targetFboHeight), new HorizontalBlurShader());
		shader.start();
		shader.loadTargetWidth(targetFboWidth);
		shader.stop();
	}

	public void render(int texture)
	{
		super.render(texture);
	}
}
