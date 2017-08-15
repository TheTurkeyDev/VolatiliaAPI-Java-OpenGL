package main.java.VolatiliaOGL.postProcessing;

import main.java.VolatiliaOGL.shaders.postProcessing.contrast.ContrastShader;

public class ContrastChanger extends BasePostProcessor<ContrastShader>
{
	public ContrastChanger(int targetFboWidth, int targetFboHeight)
	{
		super(new ImageRenderer(targetFboWidth, targetFboHeight), new ContrastShader());
	}

	public void render(int texture)
	{
		super.render(texture);
	}
}
