package main.java.VolatiliaOGL.gui;

import main.java.VolatiliaOGL.graphics.Draw2D;
import main.java.VolatiliaOGL.graphics.Texture;
import main.java.VolatiliaOGL.screen.Screen;
import main.java.VolatiliaOGL.util.ImageLoader;

public class GuiComponent
{
	public Screen screen;

	private String name;

	public float x;
	public float y;

	public float width;
	public float height;

	public String texturePath = "/textures/gui/test.png";

	public Texture texture;

	public GuiComponent(Screen s, String name)
	{
		this.screen = s;

		this.name = name;

		texture = ImageLoader.loadImage(GuiComponent.class, this.texturePath);

		this.x = 0;
		this.y = 0;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
	}

	public void render()
	{
		Draw2D.drawTextured(x, y, width, height, 0, texture.getID(), 1);
	}

	public String getName()
	{
		return this.name;
	}
}
