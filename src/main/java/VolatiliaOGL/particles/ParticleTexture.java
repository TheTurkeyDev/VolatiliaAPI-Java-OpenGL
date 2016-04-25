package main.java.VolatiliaOGL.particles;

public class ParticleTexture
{
	private int textureID;
	private int numberOfRows;
	private ParticleBlendType blendType = ParticleBlendType.Alpha;

	public ParticleTexture(int textureID, int numberOfRows)
	{
		super();
		this.textureID = textureID;
		this.numberOfRows = numberOfRows;
	}

	public int getTextureID()
	{
		return textureID;
	}

	public int getNumberOfRows()
	{
		return numberOfRows;
	}

	public ParticleBlendType getBlendType()
	{
		return blendType;
	}

	public void setBlendType(ParticleBlendType blendType)
	{
		this.blendType = blendType;
	}

	public enum ParticleBlendType
	{
		Additive, Alpha, Additive_No_Alpha;
	}
}