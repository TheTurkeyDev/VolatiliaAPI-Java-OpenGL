package main.java.VolatiliaOGL.renderEngine;

import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.gui.text.FontType;
import main.java.VolatiliaOGL.gui.text.GuiText;
import main.java.VolatiliaOGL.shaders.font.FontShader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class FontRenderer
{

	private FontShader shader;

	public FontRenderer()
	{
		shader = new FontShader();
	}

	public void cleanUp()
	{
		shader.cleanUp();
	}

	public void render(Map<FontType, List<GuiText>> texts)
	{
		this.prepare();
		for(FontType font : texts.keySet())
		{
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.getTextureAtlas());
			for(GuiText text : texts.get(font))
				if(text.isVisible())
					this.renderText(text);
		}
		this.endRendering();
	}

	private void prepare()
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		shader.start();
	}

	private void renderText(GuiText text)
	{
		GL30.glBindVertexArray(text.getMesh());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		shader.loadColor(text.getColor());
		shader.loadTranslation(text.getPosition());
		shader.loadTextFormat(text.getWidth(), text.getEdge());
		shader.loadBoarderFormat(text.getBoarderWidth(), text.getBoarderEdge());
		shader.loadOffset(text.getOffset());
		shader.loadOutlineColor(text.getOutlineColor());
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	private void endRendering()
	{
		shader.stop();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
}