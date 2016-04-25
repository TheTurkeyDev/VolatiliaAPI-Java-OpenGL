package main.java.VolatiliaOGL.shaders.basic;

import java.util.List;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.shaders.BaseShader;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class StaticShader extends BaseShader
{
	private static final int MAX_LIGHTS = 4;

	private static final String VERTEX_FILE = "src/main/java/VolatiliaOGL/shaders/basic/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolatiliaOGL/shaders/basic/fragmentShader.txt";

	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int[] locationLightPosition;
	private int[] locationLightColor;
	private int[] locationLightAttenuation;
	private int locationShineDampen;
	private int locationReflectivity;
	private int locationUseFakeLighting;
	private int locationSkyColor;
	private int locationTextureAtlasRows;
	private int locationTextureOffset;
	private int locationClipPlane;
	private int locationDensity;
	private int locationGradient;

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		this.locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		this.locationViewMatrix = super.getUniformLocation("viewMatrix");
		this.locationShineDampen = super.getUniformLocation("shineDamper");
		this.locationReflectivity = super.getUniformLocation("reflectivity");
		this.locationUseFakeLighting = super.getUniformLocation("useFakeLighting");
		this.locationSkyColor = super.getUniformLocation("skyColor");
		this.locationTextureAtlasRows = super.getUniformLocation("numberOfRows");
		this.locationTextureOffset = super.getUniformLocation("offset");
		this.locationClipPlane = super.getUniformLocation("plane");
		this.locationDensity = super.getUniformLocation("density");
		this.locationGradient = super.getUniformLocation("gradient");

		locationLightPosition = new int[MAX_LIGHTS];
		locationLightColor = new int[MAX_LIGHTS];
		locationLightAttenuation = new int[MAX_LIGHTS];
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			locationLightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
			locationLightColor[i] = super.getUniformLocation("lightColor[" + i + "]");
			locationLightAttenuation[i] = super.getUniformLocation("attenuation[" + i + "]");
		}
	}

	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(this.locationTransformationMatrix, matrix);
	}

	public void loadProjectionMatrix(Matrix4f matrix)
	{
		super.loadMatrix(this.locationProjectionMatrix, matrix);
	}

	public void loadViewMatrix(Camera camera)
	{
		super.loadMatrix(this.locationViewMatrix, MathUtil.createViewMatrix(camera));
	}

	public void loadLights(List<Light> lights)
	{
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			if(i < lights.size())
			{
				super.loadVector(this.locationLightPosition[i], lights.get(i).getPosition());
				super.loadVector(this.locationLightColor[i], lights.get(i).getColor());
				super.loadVector(this.locationLightAttenuation[i], lights.get(i).getAttenuation());
			}
			else
			{
				super.loadVector(this.locationLightPosition[i], new Vector3f(0, 0, 0));
				super.loadVector(this.locationLightColor[i], new Vector3f(0, 0, 0));
				super.loadVector(this.locationLightAttenuation[i], new Vector3f(1, 0, 0));
			}
		}
	}

	public void loadShineVariables(float dampen, float reflectivity)
	{
		super.loadFloat(this.locationShineDampen, dampen);
		super.loadFloat(this.locationReflectivity, reflectivity);
	}

	public void loadUsesFakeLighting(boolean usesFakeLight)
	{
		super.loadBoolean(this.locationUseFakeLighting, usesFakeLight);
	}

	public void loadSkyColor(float r, float g, float b)
	{
		super.loadVector(this.locationSkyColor, new Vector3f(r, g, b));
	}

	public void loadNumberOfTextureAtlasRows(int num)
	{
		super.loadFloat(this.locationTextureAtlasRows, num);
	}

	public void loadTextureOffset(float x, float y)
	{
		super.loadVector(this.locationTextureOffset, new Vector2f(x, y));
	}

	public void loadClipedPlane(Vector4f plane)
	{
		super.loadVector(this.locationClipPlane, plane);
	}

	public void loadFogData(float density, float gradient)
	{
		super.loadFloat(this.locationDensity, density);
		super.loadFloat(this.locationGradient, gradient);
	}
}
