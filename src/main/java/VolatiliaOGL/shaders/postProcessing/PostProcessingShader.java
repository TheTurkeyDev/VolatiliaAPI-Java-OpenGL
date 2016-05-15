package main.java.VolatiliaOGL.shaders.postProcessing;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class PostProcessingShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/postProcessingVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/postProcessingFragment.txt";

	private int useContrastChange;
	private int useColorChange;
	private int useWaterEffect;
	private int useGrayScale;

	private int offset;
	private int elptime;

	public PostProcessingShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.useContrastChange = super.getUniformLocation("doContrastChange");
		this.useColorChange = super.getUniformLocation("doColorShift");
		this.useWaterEffect = super.getUniformLocation("doWaterEffect");
		this.useGrayScale = super.getUniformLocation("doGrayScale");
		
		this.offset = super.getUniformLocation("offset");
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}

	public void setContrastChanges(boolean changes)
	{
		super.loadBoolean(this.useContrastChange, changes);
	}

	public void setColorChanges(boolean changes)
	{
		super.loadBoolean(this.useColorChange, changes);
	}

	public void setUseWaterEffect(boolean use)
	{
		super.loadBoolean(this.useWaterEffect, use);
	}
	
	public void setUseGrayScale(boolean use)
	{
		super.loadBoolean(this.useGrayScale, use);
	}

	public void updateOffset()
	{
		elptime += 40;
		float move = (float) (elptime / 1000.0 * 2 * Math.PI * .75);
		super.loadFloat(this.offset, move);

	}
}
