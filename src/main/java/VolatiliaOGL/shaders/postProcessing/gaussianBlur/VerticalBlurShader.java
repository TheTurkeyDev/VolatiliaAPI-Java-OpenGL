package main.java.VolatiliaOGL.shaders.postProcessing.gaussianBlur;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class VerticalBlurShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/gaussianBlur/verticalBlurVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/gaussianBlur/blurFragment.txt";

	private int location_targetHeight;

	public VerticalBlurShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void loadTargetHeight(float height)
	{
		super.loadFloat(location_targetHeight, height);
	}

	@Override
	public void getAllUniformLocations()
	{
		location_targetHeight = super.getUniformLocation("targetHeight");
	}

	@Override
	public void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}
}
