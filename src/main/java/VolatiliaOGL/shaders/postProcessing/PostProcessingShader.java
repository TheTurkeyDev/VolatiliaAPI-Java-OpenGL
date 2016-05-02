package main.java.VolatiliaOGL.shaders.postProcessing;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class PostProcessingShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/postProcessingVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/postProcessingFragment.txt";

	public PostProcessingShader()
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
