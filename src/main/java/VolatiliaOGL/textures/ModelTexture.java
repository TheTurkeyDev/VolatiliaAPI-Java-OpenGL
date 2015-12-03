package main.java.VolatiliaOGL.textures;

public class ModelTexture
{
	private int textureID;
	private int normalMapID;

	private float shineDamper = 1;
	private float reflectivity = 0;

	private boolean hasTransparency = false;
	private boolean useFakeLighting = false;

	private int numberOfRows = 1;

	public ModelTexture(int id)
	{
		this.textureID = id;
	}

	public int getID()
	{
		return this.textureID;
	}

	public int getTextureID()
	{
		return textureID;
	}

	public void setTextureID(int textureID)
	{
		this.textureID = textureID;
	}

	public int getNormalMapID()
	{
		return normalMapID;
	}

	public void setNormalMapID(int normalMapID)
	{
		this.normalMapID = normalMapID;
	}

	public float getShineDamper()
	{
		return shineDamper;
	}

	public void setShineDamper(float shineDamper)
	{
		this.shineDamper = shineDamper;
	}

	public float getReflectivity()
	{
		return reflectivity;
	}

	public void setReflectivity(float reflectivity)
	{
		this.reflectivity = reflectivity;
	}

	public boolean hasTransparency()
	{
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency)
	{
		this.hasTransparency = hasTransparency;
	}

	public boolean usesFakeLighting()
	{
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting)
	{
		this.useFakeLighting = useFakeLighting;
	}

	public int getNumberOfRows()
	{
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows)
	{
		this.numberOfRows = numberOfRows;
	}

}