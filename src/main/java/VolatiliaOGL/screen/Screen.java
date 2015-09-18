package main.java.VolatiliaOGL.screen;

import java.util.ArrayList;

import main.java.VolatiliaOGL.gui.GuiComponent;

import org.lwjgl.opengl.Display;

public class Screen
{
	protected String name;
	public int height, width;
	protected ArrayList<GuiComponent> components = new ArrayList<GuiComponent>();

	public Screen(String n)
	{
		name = n;
		height = Display.getHeight();
		width = Display.getWidth();
	}

	/**
	 * Updates the screen
	 */
	public void update()
	{
		
	}

	/**
	 * renders the screen
	 */
	public void render()
	{
		for(GuiComponent comp: components)
			comp.render();
	}

	/**
	 * Polls the input of the user
	 */
	public void pollInput()
	{

	}

	/**
	 * called to the new screen when the screen is changed
	 */
	public void setUp()
	{

	}

	/**
	 * called to the old screen when the screen is changed
	 */
	public void cleanUp()
	{

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
}
