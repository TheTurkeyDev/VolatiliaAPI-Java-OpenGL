package main.java.VolatiliaOGL.gui;

import main.java.VolatiliaOGL.screen.Screen;

public class GuiButton extends GuiComponent
{
	public String displayText;
	
	public GuiButton(Screen s, String name, String text, int x, int y, int width, int height)
	{
		super(s, name, x, y, width, height);
		this.displayText = text;
	}

	public void render()
	{
		super.render();
	}
}