package main.java.VolatiliaOGL.gui;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture
{
	private int texureID;
	private Vector2f position;
	private Vector2f scale;

	public GuiTexture(int texureID, Vector2f position, Vector2f scale)
	{
		super();
		this.texureID = texureID;
		this.position = position;
		this.scale = scale;
	}

	public int getTexureID()
	{
		return texureID;
	}

	public Vector2f getPosition()
	{
		return position;
	}

	public Vector2f getScale()
	{
		return scale;
	}
}