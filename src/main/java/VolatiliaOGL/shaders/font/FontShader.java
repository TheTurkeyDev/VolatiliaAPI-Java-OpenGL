package main.java.VolatiliaOGL.shaders.font;

import main.java.VolatiliaOGL.shaders.BaseShader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class FontShader extends BaseShader
{

	private static final String VERTEX_FILE = "src/main/java/VolatiliaOGL/shaders/font/fontVertex.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolatiliaOGL/shaders/font/fontFragment.txt";

	private int locationColor;
	private int locationTranslation;

	public FontShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationColor = super.getUniformLocation("color");
		this.locationTranslation = super.getUniformLocation("translation");
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	public void loadColor(Vector3f color)
	{
		super.loadVector(this.locationColor, color);
	}

	public void loadTranslation(Vector2f translation)
	{
		super.loadVector(this.locationTranslation, translation);
	}
}