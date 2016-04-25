package main.java.VolatiliaOGL.shaders.particle;

import main.java.VolatiliaOGL.shaders.BaseShader;

import org.lwjgl.util.vector.Matrix4f;

public class ParticleShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/particle/particleVertexShader.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/particleFragmentShader.txt";

	private int locationNumberOfRows;
	private int locationProjectionMatrix;

	public ParticleShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		locationNumberOfRows = super.getUniformLocation("numberofRows");
		locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "modelViewMatrix");
		super.bindAttribute(5, "texOffsets");
		super.bindAttribute(6, "blendFactor");
		
	}

	public void loadNumberofRows(float numOfRows)
	{
		super.loadFloat(this.locationNumberOfRows, numOfRows);
	}

	public void loadProjectionMatrix(Matrix4f projectionMatrix)
	{
		super.loadMatrix(locationProjectionMatrix, projectionMatrix);
	}
}