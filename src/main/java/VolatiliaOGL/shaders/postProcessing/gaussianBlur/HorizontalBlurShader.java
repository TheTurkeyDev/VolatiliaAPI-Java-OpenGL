package main.java.VolatiliaOGL.shaders.postProcessing.gaussianBlur;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class HorizontalBlurShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/gaussianBlur/horizontalBlurVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/gaussianBlur/blurFragment.txt";

	private int location_targetWidth;

	public HorizontalBlurShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void loadTargetWidth(float width)
	{
		super.loadFloat(location_targetWidth, width);
	}

	@Override
	public void getAllUniformLocations()
	{
		location_targetWidth = super.getUniformLocation("targetWidth");
	}

	@Override
	public void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}

}
