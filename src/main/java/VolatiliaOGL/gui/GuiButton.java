package main.java.VolatiliaOGL.gui;

import main.java.VolatiliaOGL.graphics.FontManager;
import main.java.VolatiliaOGL.screen.Screen;

public class GuiButton extends GuiComponent
{
	public String displayText;

	/**
	 * Creates a Gui Button
	 * 
	 * @param screen
	 * @param name
	 *            Button name
	 * @param text
	 *            Button text
	 */
	public GuiButton(Screen s, String name, String text)
	{
		super(s, name);
		this.displayText = text;
	}

	public void render()
	{
		super.render();
		FontManager.instance.RenderStringAt(this.displayText, x, y);
	}
}