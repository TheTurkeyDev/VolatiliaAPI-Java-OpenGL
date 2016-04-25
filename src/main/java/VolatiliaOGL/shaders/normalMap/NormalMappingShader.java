package main.java.VolatiliaOGL.shaders.normalMap;

import java.util.List;

import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.shaders.BaseShader;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class NormalMappingShader extends BaseShader
{

	private static final int MAX_LIGHTS = 4;

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/normalMap/normalMapVShader.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/normalMap/normalMapFShader.txt";

	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int locationLightPositionEyeSpace[];
	private int locationLightColour[];
	private int locationAttenuation[];
	private int locationShineDamper;
	private int locationReflectivity;
	private int locationSkyColour;
	private int locationNumberOfRows;
	private int locationOffset;
	private int locationPlane;
	private int locationModelTexture;
	private int locationNormalMap;
	private int locationDensity;
	private int locationGradient;

	public NormalMappingShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
		super.bindAttribute(3, "tangent");
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		this.locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		this.locationViewMatrix = super.getUniformLocation("viewMatrix");
		this.locationShineDamper = super.getUniformLocation("shineDamper");
		this.locationReflectivity = super.getUniformLocation("reflectivity");
		this.locationSkyColour = super.getUniformLocation("skyColour");
		this.locationNumberOfRows = super.getUniformLocation("numberOfRows");
		this.locationOffset = super.getUniformLocation("offset");
		this.locationPlane = super.getUniformLocation("plane");
		this.locationModelTexture = super.getUniformLocation("modelTexture");
		this.locationNormalMap = super.getUniformLocation("normalMap");
		this.locationDensity = super.getUniformLocation("density");
		this.locationGradient = super.getUniformLocation("gradient");

		this.locationLightPositionEyeSpace = new int[MAX_LIGHTS];
		this.locationLightColour = new int[MAX_LIGHTS];
		this.locationAttenuation = new int[MAX_LIGHTS];
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			this.locationLightPositionEyeSpace[i] = super.getUniformLocation("lightPositionEyeSpace[" + i + "]");
			this.locationLightColour[i] = super.getUniformLocation("lightColour[" + i + "]");
			this.locationAttenuation[i] = super.getUniformLocation("attenuation[" + i + "]");
		}
	}

	public void connectTextureUnits()
	{
		super.loadInt(locationModelTexture, 0);
		super.loadInt(this.locationNormalMap, 1);
	}

	public void loadClipPlane(Vector4f plane)
	{
		super.loadVector(locationPlane, plane);
	}

	public void loadNumberOfRows(int numberOfRows)
	{
		super.loadFloat(locationNumberOfRows, numberOfRows);
	}

	public void loadOffset(float x, float y)
	{
		super.loadVector(locationOffset, new Vector2f(x, y));
	}

	public void loadSkyColour(float r, float g, float b)
	{
		super.loadVector(locationSkyColour, new Vector3f(r, g, b));
	}

	public void loadShineVariables(float damper, float reflectivity)
	{
		super.loadFloat(locationShineDamper, damper);
		super.loadFloat(locationReflectivity, reflectivity);
	}

	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(locationTransformationMatrix, matrix);
	}

	public void loadLights(List<Light> lights, Matrix4f viewMatrix)
	{
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			if(i < lights.size())
			{
				super.loadVector(locationLightPositionEyeSpace[i], getEyeSpacePosition(lights.get(i), viewMatrix));
				super.loadVector(locationLightColour[i], lights.get(i).getColor());
				super.loadVector(locationAttenuation[i], lights.get(i).getAttenuation());
			}
			else
			{
				super.loadVector(locationLightPositionEyeSpace[i], new Vector3f(0, 0, 0));
				super.loadVector(locationLightColour[i], new Vector3f(0, 0, 0));
				super.loadVector(locationAttenuation[i], new Vector3f(1, 0, 0));
			}
		}
	}

	public void loadViewMatrix(Matrix4f viewMatrix)
	{
		super.loadMatrix(locationViewMatrix, viewMatrix);
	}

	public void loadProjectionMatrix(Matrix4f projection)
	{
		super.loadMatrix(locationProjectionMatrix, projection);
	}

	private Vector3f getEyeSpacePosition(Light light, Matrix4f viewMatrix)
	{
		Vector3f position = light.getPosition();
		Vector4f eyeSpacePos = new Vector4f(position.x, position.y, position.z, 1f);
		Matrix4f.transform(viewMatrix, eyeSpacePos, eyeSpacePos);
		return new Vector3f(eyeSpacePos);
	}

	public void loadFogData(float density, float gradient)
	{
		super.loadFloat(this.locationDensity, density);
		super.loadFloat(this.locationGradient, gradient);
	}
}