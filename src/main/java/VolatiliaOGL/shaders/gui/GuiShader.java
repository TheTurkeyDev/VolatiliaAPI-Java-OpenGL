package main.java.VolatiliaOGL.shaders.gui;

import main.java.VolatiliaOGL.shaders.BaseShader;

import org.lwjgl.util.vector.Matrix4f;

public class GuiShader extends BaseShader
{

	private static final String VERTEX_FILE = "/main/java/VolatiliaOGL/shaders/gui/guiVertexShader.txt";
	private static final String FRAGMENT_FILE = "/main/java/VolatiliaOGL/shaders/gui/guiFragmentShader.txt";

	private int location_transformationMatrix;

	public GuiShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	public void loadTransformation(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	@Override
	protected void getAllUniformLocations()
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}

}