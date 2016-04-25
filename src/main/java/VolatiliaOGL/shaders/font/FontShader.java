package main.java.VolatiliaOGL.shaders.font;

import main.java.VolatiliaOGL.shaders.BaseShader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class FontShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/font/fontVertex.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/font/fontFragment.txt";

	private int locationColor;
	private int locationTranslation;
	private int locationWidth;
	private int locationEdge;
	private int locationBoarderWidth;
	private int locationBoarderEdge;
	private int locationOffset;
	private int locationOutlineColor;

	public FontShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations()
	{
		this.locationColor = super.getUniformLocation("color");
		this.locationTranslation = super.getUniformLocation("translation");
		this.locationWidth = super.getUniformLocation("width");
		this.locationEdge = super.getUniformLocation("edge");
		this.locationBoarderWidth = super.getUniformLocation("borderWidth");
		this.locationBoarderEdge = super.getUniformLocation("borderEdge");
		this.locationOffset = super.getUniformLocation("offset");
		this.locationOutlineColor = super.getUniformLocation("outlineColor");
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
	
	public void loadTextFormat(float width, float edge)
	{
		super.loadFloat(this.locationWidth, width);
		super.loadFloat(this.locationEdge, edge);
	}
	
	public void loadBoarderFormat(float width, float edge)
	{
		super.loadFloat(this.locationBoarderWidth, width);
		super.loadFloat(this.locationBoarderEdge, edge);
	}
	
	public void loadOffset(Vector2f offset)
	{
		super.loadVector(this.locationOffset, offset);
	}
	
	public void loadOutlineColor(Vector3f color)
	{
		super.loadVector(this.locationOutlineColor, color);
	}
}