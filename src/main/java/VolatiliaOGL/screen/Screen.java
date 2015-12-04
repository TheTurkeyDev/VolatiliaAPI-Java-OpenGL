package main.java.VolatiliaOGL.screen;

import java.util.ArrayList;

import main.java.VolatiliaOGL.gui.GuiComponent;
import main.java.VolatiliaOGL.gui.text.GuiText;
import main.java.VolatiliaOGL.gui.text.TextMaster;
import main.java.VolatiliaOGL.renderEngine.GuiRenderer;

import org.lwjgl.opengl.Display;

public class Screen
{
	protected String name;
	public int height, width;
	protected ArrayList<GuiComponent> components = new ArrayList<GuiComponent>();
	protected ArrayList<GuiText> text = new ArrayList<GuiText>();
	
	protected GuiRenderer guiRenderer = new GuiRenderer();

	public Screen(String n)
	{
		name = n;
		height = Display.getHeight();
		width = Display.getWidth();
	}

	/**
	 * renders the screen
	 */
	public void render()
	{
		for(GuiComponent comp: components)
			comp.render();
		TextMaster.render();
	}

	/**
	 * called to the new screen when the screen is changed
	 */
	public void setUp()
	{
		for(GuiText t: text)
			TextMaster.loadText(t);
	}

	/**
	 * called to the old screen when the screen is changed
	 */
	public void cleanUp()
	{
		for(GuiText t: text)
			TextMaster.removeText(t);
	}

	/**
	 * gets the name of the screen
	 * 
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * gets the screen object with the specified name
	 */
	public ArrayList<GuiComponent> getGuiComponents()
	{
		return components;
	}

	/**
	 * gets the screen object with the specified name
	 */
	public GuiComponent getGuiComponent(String name)
	{
		for (GuiComponent so : components)
			if (so.getName().equalsIgnoreCase(name)) return so;
		return null;
	}

	/**
	 * Adds a screen object to the possible objects for screens to use
	 * 
	 * @param ScreenObject
	 *            to add
	 */
	public void addGuiComponent(GuiComponent so)
	{
		components.add(so);
	}
	
	/**
	 * Adds a screen object to the possible objects for screens to use
	 * 
	 * @param ScreenObject
	 *            to add
	 */
	public void addGuiText(GuiText text)
	{
		this.text.add(text);
		TextMaster.loadText(text);
	}
}
