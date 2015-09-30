package main.java.VolatiliaOGL.graphics.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends BaseShader
{
	public static final StaticShader INSTANCE = new StaticShader();
	private static final String VERTEX_FILE = "src/main/java/VolitiliaOGL/graphics/shaders/vertextShader.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolitiliaOGL/graphics/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
	}

	@Override
	protected void getAllUniformLocations()
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(this.location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(this.location_projectionMatrix, matrix);
	}

}
