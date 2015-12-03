package main.java.VolatiliaOGL.gui;

import main.java.VolatiliaOGL.screen.Screen;

import org.lwjgl.util.vector.Vector2f;

public class GuiButton extends GuiComponent
{
	public String displayText;
	
	public GuiButton(Screen s, String name, int textID, Vector2f position, Vector2f scale, String text)
	{
		super(s, name, textID, position, scale);
		this.displayText = text;
	}

	public void render()
	{
		super.render();
	}
}