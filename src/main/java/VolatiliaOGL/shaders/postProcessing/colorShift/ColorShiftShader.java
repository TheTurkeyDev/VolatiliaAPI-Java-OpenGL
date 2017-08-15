package main.java.VolatiliaOGL.shaders.postProcessing.colorShift;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class ColorShiftShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/colorShift/colorShiftVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/colorShift/colorShiftFragment.txt";

	private int rShift;
	private int gShift;
	private int bShift;
	
	public ColorShiftShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.rShift = super.getUniformLocation("rChange");
		this.gShift = super.getUniformLocation("gChange");
		this.bShift = super.getUniformLocation("bChange");
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}
	
	public void setColorOffsets(float r, float g, float b)
	{
		super.loadFloat(this.rShift, r);
		super.loadFloat(this.gShift, g);
		super.loadFloat(this.bShift, b);
	}
}
