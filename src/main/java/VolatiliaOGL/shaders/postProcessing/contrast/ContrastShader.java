package main.java.VolatiliaOGL.shaders.postProcessing.contrast;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class ContrastShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/contrast/contrastVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/contrast/contrastFragment.txt";

	public ContrastShader()
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
