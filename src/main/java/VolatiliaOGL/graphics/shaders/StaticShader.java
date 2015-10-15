package main.java.VolatiliaOGL.graphics.shaders;

import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.player.DisplayView;
import main.java.VolatiliaOGL.util.MatrixMath;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class StaticShader extends BaseShader
{
	private static final String VERTEX_FILE = "src/main/java/VolatiliaOGL/graphics/shaders/vertextShader.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolatiliaOGL/graphics/shaders/fragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColor;
	private int location_shineDampen;
	private int location_reflection;
	private int location_useFakeLighting;
	private int location_fogDensity;
	private int location_skyColor;

	public StaticShader()
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
		this.location_useFakeLighting = super.getUniformLocation("useFakeLighting");
		this.location_fogDensity = super.getUniformLocation("density");
		this.location_skyColor = super.getUniformLocation("skyColor");
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

	public void loadFakeLighting(boolean fake)
	{
		super.loadBoolean(this.location_useFakeLighting, fake);
	}

	public void loadFogDensity(float density)
	{
		super.loadFloat(this.location_fogDensity, density / 1000);
	}

	public void loadSkyColor(float r, float g, float b)
	{
		super.loadVector(this.location_skyColor, new Vector3f(r, g, b));
	}
}
