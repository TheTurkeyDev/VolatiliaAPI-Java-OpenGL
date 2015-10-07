package main.java.VolatiliaOGL.gui;

import main.java.VolatiliaOGL.screen.Screen;

public class GuiComponent
{
	public Screen screen;

	private String name;

	public float x;
	public float y;

	public float width;
	public float height;

	public String texturePath = "/textures/gui/test.png";

	public GuiComponent(Screen s, String name, int x, int y, int width, int height)
	{
		this.screen = s;
		this.name = name;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render()
	{
		
	}

	public String getName()
	{
		return this.name;
	}
}
