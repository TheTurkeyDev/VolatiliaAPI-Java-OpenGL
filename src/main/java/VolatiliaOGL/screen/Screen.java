package main.java.VolatiliaOGL.screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.gui.GuiComponent;
import main.java.VolatiliaOGL.gui.text.FontType;
import main.java.VolatiliaOGL.gui.text.GuiText;
import main.java.VolatiliaOGL.gui.text.TextMeshData;
import main.java.VolatiliaOGL.renderEngine.FontRenderer;
import main.java.VolatiliaOGL.renderEngine.GuiRenderer;
import main.java.VolatiliaOGL.util.Loader;

import org.lwjgl.opengl.Display;

public class Screen
{
	protected String name;
	public int height, width;
	protected ArrayList<GuiComponent> components = new ArrayList<GuiComponent>();
	private Map<FontType, List<GuiText>> texts = new HashMap<FontType, List<GuiText>>();
	
	private FontRenderer renderer = new FontRenderer();;
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
		renderer.render(texts);
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
	
	/**
	 * Adds text to the screen
	 * 
	 * @param GuiText
	 *            to add
	 */	
	public void addText(GuiText text)
	{
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = Loader.INSTANCE.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GuiText> textBatch = texts.get(font);
		if(textBatch == null)
		{
			textBatch = new ArrayList<GuiText>();
			texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public void removeText(GuiText text)
	{
		List<GuiText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if(textBatch.isEmpty())
		{
			texts.remove(text.getFont());
		}
	}
	
	public void finalCleanUp()
	{
		renderer.cleanUp();
	}
}
