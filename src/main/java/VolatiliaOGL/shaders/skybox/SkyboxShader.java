package main.java.VolatiliaOGL.shaders.skybox;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.shaders.BaseShader;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class SkyboxShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/skybox/skyboxVertexShader.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/skybox/skyboxFragmentShader.txt";

	private static final float ROTATE_SPEED = 1f;

	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int locationFogColor;
	private int locationCubeMap;
	private int locationCubeMap2;
	private int locationBlendFactor;

	private float rotation = 0;

	public SkyboxShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		this.locationViewMatrix = super.getUniformLocation("viewMatrix");
		this.locationFogColor = super.getUniformLocation("fogColor");
		this.locationBlendFactor = super.getUniformLocation("blendFactor");
		this.locationCubeMap = super.getUniformLocation("cubeMap");
		this.locationCubeMap2 = super.getUniformLocation("cubeMap2");
	}

	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(locationProjectionMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera)
	{
		Matrix4f matrix = MathUtil.createViewMatrix(camera);
		matrix.m30 = 0;
		matrix.m31 = 0;
		matrix.m32 = 0;
		rotation += ROTATE_SPEED * VolatiliaAPI.getFrameTimeSeconds();
		rotation %= 360;
		Matrix4f.rotate((float) Math.toRadians(rotation), new Vector3f(0, 1, 0), matrix, matrix);
		super.loadMatrix(locationViewMatrix, matrix);
	}

	public void loadFogColor(float r, float g, float b)
	{
		super.loadVector(this.locationFogColor, new Vector3f(r, g, b));
	}

	public void loadBlendFactor(float blend)
	{
		super.loadFloat(this.locationBlendFactor, blend);
	}

	public void connectTextureUnits()
	{
		super.loadInt(this.locationCubeMap, 0);
		super.loadInt(this.locationCubeMap2, 1);
	}
}
