package main.java.VolatiliaOGL.gui;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.graphics.models.ModelData;
import main.java.VolatiliaOGL.graphics.models.ModelLoader;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.renderers.Draw2D;
import main.java.VolatiliaOGL.graphics.textures.Texture;
import main.java.VolatiliaOGL.graphics.textures.TextureManager;
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

	private TexturedModel texturedModel;
	private ModelData modelData;

	public GuiComponent(Screen s, String name, int x, int y, int width, int height)
	{
		this.screen = s;
		this.name = name;

		int sw = VolatiliaAPI.instance.getWidth();
		int sh = VolatiliaAPI.instance.getHeight();

		float[] vertacies = { (x - (sw / 2)) / sw, (y - (sh / 2)) / sh, 0, ((x + width) - (sw / 2)) / sw, (y - (sh / 2)) / sh, 0, (x - (sw / 2)) / sw, ((y + height) - (sh / 2)) / sh, 0, ((x + width) - (sw / 2)) / sw, ((y + height) - (sh / 2)) / sh, 0 };
		int[] indices = { 0, 1, 3, 3, 1, 2 };

		modelData = ModelLoader.INSTANCE.loadToModelData(vertacies, indices);
		Texture texture = new Texture(TextureManager.INSTANCE.loadTexture(GuiComponent.class, texturePath));
		texturedModel = new TexturedModel(modelData, texture);

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render()
	{
		Draw2D.draw2D(texturedModel);
	}

	public String getName()
	{
		return this.name;
	}
}
