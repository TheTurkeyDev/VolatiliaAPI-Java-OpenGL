package main.java.VolatiliaOGL.graphics;

import main.java.VolatiliaOGL.graphics.textures.Texture;

public class FontManager
{
	public static FontManager instance;

	public Texture characters;

	public FontManager()
	{
		instance = this;
		loadFont();
	}

	private void loadFont()
	{
		
	}
}
