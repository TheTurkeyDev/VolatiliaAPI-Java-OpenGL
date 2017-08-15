package main.java.VolatiliaOGL.postProcessing;

import main.java.VolatiliaOGL.shaders.postProcessing.waterEffect.WaterEffectShader;

public class WaterEffect extends BasePostProcessor<WaterEffectShader>
{
	public WaterEffect(int targetFboWidth, int targetFboHeight)
	{
		super(new ImageRenderer(targetFboWidth, targetFboHeight), new WaterEffectShader());
	}

	public void render(int texture)
	{
		super.render(texture);
		shader.start();
		((WaterEffectShader) super.shader).updateOffset();
		shader.stop();
	}
}
