package main.java.VolatiliaOGL.graphics;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import main.java.VolatiliaOGL.graphics.renderers.Draw2D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

public class FontManager
{
	public static FontManager instance;

	public Texture characters;

	private int charOffset = 32;

	public FontManager()
	{
		instance = this;
		loadFont();
	}

	private void loadFont()
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(FontManager.class.getResource("/textures/font/Font.png"));
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		for(int x = 0; x < image.getWidth() / 8; x++)
		{
			for(int y = 0; y < image.getHeight() / 8; y++)
			{
				ByteBuffer buffer = BufferUtils.createByteBuffer(8 * 8 * 4);

				for(int yy = 0; yy < 8; yy++)
				{
					for(int xx = 0; xx < 8; xx++)
					{
						int pixel = pixels[(y * 8 + yy) * image.getWidth() + (x * 8 + xx)];
						buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red
						// component
						buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green
						// component
						buffer.put((byte) (pixel & 0xFF)); // Blue component
						buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
						// component.
						// Only for RGBA
					}
				}
				
				System.out.println(buffer.limit());
				
				for(int remain = buffer.remaining(); remain > 0; remain--)
				{
					System.out.println("here");
					buffer.put((byte) -1);
				}

				buffer.flip();
				int textureID = glGenTextures(); // Generate texture ID
				glBindTexture(GL_TEXTURE_2D, textureID); // Bind texture ID

				// Setup wrap mode
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

				// Setup texture scaling filtering
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

				// Send texel data to OpenGL
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

				characters = new Texture(textureID, image.getWidth(), image.getHeight());
			}
		}
	}

	public void RenderStringAt(String s, float x, float y)
	{
		float xx = x;
		for(char c : s.toCharArray())
		{
			if(!(c - charOffset >= 0))
				continue;
			Draw2D.drawTextured(xx, y, 8, 8, 0, this.characters.getID(), 50);
			xx += 8;
		}
	}

	public int getStringWidth(String s)
	{
		return s.length() * 8;
	}
}
