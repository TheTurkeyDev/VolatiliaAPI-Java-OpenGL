package main.java.VolatiliaOGL.shaders.water;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.shaders.BaseShader;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class WaterShader extends BaseShader
{

	private final static String VERTEX_FILE = "src/main/java/VolatiliaOGL/shaders/water/waterVertex.txt";
	private final static String FRAGMENT_FILE = "src/main/java/VolatiliaOGL/shaders/water/waterFragment.txt";

	private int locationModelMatrix;
	private int locationViewMatrix;
	private int locationProjectionMatrix;
	private int locationReflectionTexture;
	private int locationRefractionTexture;
	private int locationDuDvMap;
	private int locationNormalMap;
	private int locationMoveFactor;
	private int locationCameraPosition;
	private int locationLightPosition;
	private int locationLightColor;
	private int locationDepthMap;
	private int locationDensity;
	private int locationGradient;
	private int locationSkyColor;

	public WaterShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		this.locationViewMatrix = super.getUniformLocation("viewMatrix");
		this.locationModelMatrix = super.getUniformLocation("modelMatrix");
		this.locationReflectionTexture = super.getUniformLocation("reflectionTexture");
		this.locationRefractionTexture = super.getUniformLocation("refractionTexture");
		this.locationDuDvMap = super.getUniformLocation("dudvMap");
		this.locationNormalMap = super.getUniformLocation("normalMap");
		this.locationMoveFactor = super.getUniformLocation("moveFactor");
		this.locationCameraPosition = super.getUniformLocation("cameraPosition");
		this.locationLightPosition = super.getUniformLocation("lightPosition");
		this.locationLightColor = super.getUniformLocation("lightColor");
		this.locationDepthMap = super.getUniformLocation("depthMap");
		this.locationDensity = super.getUniformLocation("density");
		this.locationGradient = super.getUniformLocation("gradient");
		this.locationSkyColor = super.getUniformLocation("skyColor");
	}

	public void loadProjectionMatrix(Matrix4f projection)
	{
		loadMatrix(locationProjectionMatrix, projection);
	}

	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = MathUtil.createViewMatrix(camera);
		loadMatrix(locationViewMatrix, viewMatrix);
		super.loadVector(this.locationCameraPosition, camera.getPosition());
	}

	public void loadModelMatrix(Matrix4f modelMatrix)
	{
		loadMatrix(locationModelMatrix, modelMatrix);
	}

	public void connectTextureUnits()
	{
		super.loadInt(this.locationReflectionTexture, 0);
		super.loadInt(this.locationRefractionTexture, 1);
		super.loadInt(this.locationDuDvMap, 2);
		super.loadInt(this.locationNormalMap, 3);
		super.loadInt(this.locationDepthMap, 4);
	}

	public void loadMoveFactor(float factor)
	{
		super.loadFloat(this.locationMoveFactor, factor);
	}

	public void loadLight(Light sun)
	{
		super.loadVector(this.locationLightPosition, sun.getPosition());
		super.loadVector(this.locationLightColor, sun.getColor());
	}
	
	public void loadFogData(float density, float gradient)
	{
		super.loadFloat(this.locationDensity, density);
		super.loadFloat(this.locationGradient, gradient);
	}
	
	public void loadSkyColor(float r, float g, float b)
	{
		super.loadVector(this.locationSkyColor, new Vector3f(r, g, b));
	}
}