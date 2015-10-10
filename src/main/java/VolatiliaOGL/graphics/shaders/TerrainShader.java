package main.java.VolatiliaOGL.graphics.shaders;

import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.player.DisplayView;
import main.java.VolatiliaOGL.util.MatrixMath;

import org.lwjgl.util.vector.Matrix4f;

public class TerrainShader extends BaseShader
{
	private static final String VERTEX_FILE = "src/main/java/VolatiliaOGL/graphics/shaders/terrainVertextShader.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolatiliaOGL/graphics/shaders/terrainFragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColor;
	private int location_shineDampen;
	private int location_reflection;

	public TerrainShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		this.location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		this.location_viewMatrix = super.getUniformLocation("viewMatrix");
		this.location_lightPosition = super.getUniformLocation("lightPosition");
		this.location_lightColor = super.getUniformLocation("lightColor");
		this.location_shineDampen = super.getUniformLocation("shineDampen");
		this.location_reflection = super.getUniformLocation("reflection");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(this.location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(this.location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(DisplayView view)
	{
		Matrix4f viewMatrix = MatrixMath.createViewMatrix(view);
		super.loadMatrix(this.location_viewMatrix, viewMatrix);
	}
	
	public void loadLight(LightEntity light)
	{
		super.loadVector(this.location_lightPosition, light.getPosition());
		super.loadVector(this.location_lightColor, light.getColor());
	}
	
	public void loadShineValues(float shineDampen, float reflection)
	{
		super.loadFloat(this.location_shineDampen, shineDampen);
		super.loadFloat(this.location_reflection, reflection);
	}

}