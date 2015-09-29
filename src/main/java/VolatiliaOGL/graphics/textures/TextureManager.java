package main.java.VolatiliaOGL.graphics.textures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureManager
{
	public static final TextureManager INSTANCE = new TextureManager();
	private List<Integer> textures = new ArrayList<Integer>();

	public int loadTexture(Class<?> clazz, String path)
	{
		Texture texture = null;

		try
		{
			texture = TextureLoader.getTexture("PNG", clazz.getResourceAsStream(path));
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}

	public void cleanUp()
	{
		for(int texture : this.textures)
		{
			GL11.glDeleteTextures(texture);
		}
	}
}
