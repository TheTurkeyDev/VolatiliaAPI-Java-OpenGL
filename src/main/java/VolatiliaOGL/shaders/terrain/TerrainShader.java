package main.java.VolatiliaOGL.shaders.terrain;

import java.util.List;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.shaders.BaseShader;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class TerrainShader extends BaseShader
{
	private static final int MAX_LIGHTS = 4;
	
	private static final String VERTEX_FILE = "src/main/java/VolatiliaOGL/shaders/terrain/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolatiliaOGL/shaders/terrain/terrainFragmentShader.txt";

	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int[] locationLightPosition;
	private int[] locationLightColor;
	private int[] locationLightAttenuation;
	private int locationShineDampen;
	private int locationReflectivity;
	private int locationSkyColor;
	private int locationBackgroundTexture;
	private int locationRTexture;
	private int locationGTexture;
	private int locationBTexture;
	private int locationBlendMap;
	private int locationClipPlane;

	public TerrainShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal ");
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		this.locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		this.locationViewMatrix = super.getUniformLocation("viewMatrix");
		this.locationShineDampen = super.getUniformLocation("shineDamper");
		this.locationReflectivity = super.getUniformLocation("reflectivity");
		this.locationSkyColor = super.getUniformLocation("skyColor");
		this.locationBackgroundTexture = super.getUniformLocation("backgroundTexture");
		this.locationRTexture = super.getUniformLocation("rTexture");
		this.locationGTexture = super.getUniformLocation("gTexture");
		this.locationBTexture = super.getUniformLocation("bTexture");
		this.locationBlendMap = super.getUniformLocation("blendMap");
		this.locationClipPlane = super.getUniformLocation("plane");

		locationLightPosition = new int[MAX_LIGHTS];
		locationLightColor = new int[MAX_LIGHTS];
		locationLightAttenuation = new int[MAX_LIGHTS];
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			locationLightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
			locationLightColor[i] = super.getUniformLocation("lightColor[" + i + "]");
			locationLightAttenuation[i] =  super.getUniformLocation("attenuation[" + i + "]");
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
	
	public void loadSkyColor(float r, float g, float b)
	{
		super.loadVector(this.locationSkyColor, new Vector3f(r, g, b));
	}
	
	public void connectTextureUnits()
	{
		super.loadInt(this.locationBackgroundTexture, 0);
		super.loadInt(this.locationRTexture, 1);
		super.loadInt(this.locationGTexture, 2);
		super.loadInt(this.locationBTexture, 3);
		super.loadInt(this.locationBlendMap, 4);
	}
	
	public void loadClipedPlane(Vector4f plane)
	{
		super.loadVector(this.locationClipPlane, plane);
	}
}