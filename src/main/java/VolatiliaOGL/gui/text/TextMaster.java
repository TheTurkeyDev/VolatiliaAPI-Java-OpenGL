package main.java.VolatiliaOGL.gui.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.renderEngine.FontRenderer;
import main.java.VolatiliaOGL.util.Loader;

public class TextMaster
{
	private static Map<FontType, List<GuiText>> texts = new HashMap<FontType, List<GuiText>>();
	private static FontRenderer renderer;

	public static void init()
	{
		renderer = new FontRenderer();
	}
	
	public static void render()
	{
		renderer.render(texts);
	}
	
	public static void loadText(GuiText text)
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
	
	public static void removeText(GuiText text)
	{
		List<GuiText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if(textBatch.isEmpty())
		{
			texts.remove(text.getFont());
		}
	}
	
	public static void cleanUp()
	{
		renderer.cleanUp();
	}
}
