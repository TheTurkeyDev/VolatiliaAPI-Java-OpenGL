package main.java.VolatiliaOGL.graphics.textures;

public class Texture
{
	private int id;

	private boolean hasTransparency = false;
	private boolean useFakeLighting = false;

	public Texture(int id)
	{
		this.id = id;
	}

	public int getID()
	{
		return this.id;
	}

	public boolean hasTransparency()
	{
		return hasTransparency;
	}

	public void setHasTransparency(boolean hasTransparency)
	{
		this.hasTransparency = hasTransparency;
	}

	public boolean hasFakeLighting()
	{
		return useFakeLighting;
	}

	public void setUseFakeLighting(boolean useFakeLighting)
	{
		this.useFakeLighting = useFakeLighting;
	}
}