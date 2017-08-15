package main.java.VolatiliaOGL.shaders.postProcessing.waterEffect;

import main.java.VolatiliaOGL.shaders.BaseShader;

public class WaterEffectShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/waterEffect/waterEffectVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/postProcessing/waterEffect/waterEffectFragment.txt";

	private int offset;
	private int elptime;

	public WaterEffectShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.offset = super.getUniformLocation("offset");
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}

	public void updateOffset()
	{
		elptime += 40;
		float move = (float) (elptime / 1000.0 * 2 * Math.PI * .75);
		super.loadFloat(this.offset, move);

	}
}
