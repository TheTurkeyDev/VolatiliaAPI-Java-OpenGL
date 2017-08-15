package main.java.VolatiliaOGL.shaders.postProcessing.grayScale;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class GrayScaleShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/grayScale/grayScaleVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/grayScale/grayScaleFragment.txt";

	public GrayScaleShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{

	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}
}
