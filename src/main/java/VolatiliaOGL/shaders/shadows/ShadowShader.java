package main.java.VolatiliaOGL.shaders.shadows;

import org.lwjgl.util.vector.Matrix4f;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class ShadowShader extends BaseShader
{

	private static final String VERTEX_FILE = "/shaders/shadows/shadowVertexShader.txt";
	private static final String FRAGMENT_FILE = "/shaders/shadows/shadowFragmentShader.txt";

	private int location_mvpMatrix;

	public ShadowShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		location_mvpMatrix = super.getUniformLocation("mvpMatrix");

	}

	public void loadMvpMatrix(Matrix4f mvpMatrix)
	{
		super.loadMatrix(location_mvpMatrix, mvpMatrix);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "in_position");
		super.bindAttribute(1, "in_textureCoords");
	}

}
