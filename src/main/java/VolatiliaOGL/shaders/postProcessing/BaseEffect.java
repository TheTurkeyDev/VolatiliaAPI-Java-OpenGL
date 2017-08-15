package main.java.VolatiliaOGL.shaders.postProcessing;

import main.java.VolatiliaOGL.postProcessing.BasePostProcessor;
import main.java.VolatiliaOGL.postProcessing.ImageRenderer;
import main.java.VolatiliaOGL.shaders.BaseShader;
import main.java.VolatiliaOGL.shaders.postProcessing.colorShift.ColorShiftShader;

public class BaseEffect extends BasePostProcessor<BaseShader>
{
	public BaseEffect()
	{
		super(new ImageRenderer(), new ColorShiftShader());
	}
}
