package test.main.screens;

import java.awt.Color;
import java.io.File;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import main.java.VolatiliaOGL.gui.text.FontType;
import main.java.VolatiliaOGL.gui.text.GuiText;
import main.java.VolatiliaOGL.screen.Screen;
import main.java.VolatiliaOGL.util.Loader;

public class TestScreen extends Screen
{
	FontType font = new FontType(Loader.INSTANCE.loadTexture("font/TestFont"), new File("res/font/TestFont.fnt"));
	GuiText text = new GuiText("This is a test >:)", 3, font, new Vector2f(0.0f, 0.0f), 1f, true);

	float color = 0, boarder = 0;
	boolean up = true;

	public TestScreen()
	{
		super("Test Screen");
		text.setColor(0, 0, 0);
		super.addText(text);

	}

	public void render()
	{
		color += 0.005;
		Color tmpClr = new Color(Color.HSBtoRGB(color, 1F, 1F));
		text.setColor(new Vector3f(tmpClr.getRed() / 255F, tmpClr.getGreen() / 255F, tmpClr.getBlue() / 255F));
		if(up)
		{
			boarder += 0.01;
			if(boarder > 0.75)
				up = false;
		}
		else
		{
			boarder -= 0.01;
			if(boarder < 0.5)
				up = true;
		}
		text.setBoarderWidth(boarder);
		super.render();
	}
}