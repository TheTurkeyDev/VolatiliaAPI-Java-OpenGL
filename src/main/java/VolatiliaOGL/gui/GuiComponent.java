package main.java.VolatiliaOGL.gui;

import org.lwjgl.util.vector.Vector2f;

import main.java.VolatiliaOGL.screen.Screen;

public class GuiComponent
{
	public Screen screen;

	private String name;
	
	private int texureID;
	
	private Vector2f position;
	private Vector2f scale;

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

	public GuiComponent(Screen s, String name, int texureID, Vector2f position, Vector2f scale)
	{
		this.screen = s;
		this.name = name;
		this.texureID = texureID;
		this.position = position;
		this.scale = scale;
	}

	public void render()
	{
		
	}

	public String getName()
	{
		return this.name;
	}
}
