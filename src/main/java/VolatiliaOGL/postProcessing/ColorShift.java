package main.java.VolatiliaOGL.postProcessing;

import main.java.VolatiliaOGL.shaders.postProcessing.colorShift.ColorShiftShader;

public class ColorShift extends BasePostProcessor<ColorShiftShader>
{
	public ColorShift(int targetFboWidth, int targetFboHeight)
	{
		super(new ImageRenderer(targetFboWidth, targetFboHeight), new ColorShiftShader());
		setColorOffsets(0.9f, 0.0f, 0.0f);
	}

	public void render(int texture)
	{
		super.render(texture);
	}

	public void setColorOffsets(float r, float g, float b)
	{
		shader.start();
		shader.setColorOffsets(r, g, b);
		shader.stop();
	}
}
