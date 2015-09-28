package main.java.VolatiliaOGL.graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.Display;

public class Draw2D
{

	/**
	 * Draws a 2D rectangle
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param width
	 * @param height
	 * @param rotation
	 * @param Color
	 */
	public static void drawRect(float x, float y, float width, float height, float rotate, Color color)
	{
		set2D();
		glDisable(GL_DEPTH_TEST);
		glPushMatrix();
		{
			float[] colors = color.getFloats(color);
			glColor3f(colors[0], colors[1], colors[2]);
			glTranslatef(x, y, 0);
			glRotatef(rotate, 1, 1, 0);
			glBegin(GL_QUADS);
			{
				// Face 1
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			}
			glEnd();
		}
		glPopMatrix();
		set3D();
	}

	/**
	 * Draws a 2D rectangle
	 * 
	 * @param x
	 *            location
	 * @param y
	 *            location
	 * @param z
	 *            location
	 * @param width
	 *            of rectangle
	 * @param height
	 *            of rectangle
	 * @param rotation
	 *            of rectangle
	 * @param Color
	 *            of rectangle
	 */
	public static void drawTextured(float x, float y, float width, float height, float rotate, int textureID, float scale)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glPushMatrix();
		glTranslatef(x, y, 0);
		glScalef(scale, scale, 0);
		glBindTexture(GL_TEXTURE_2D, textureID);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
			glTexCoord2f(0, 1);
			glVertex2f(0, height);
			glTexCoord2f(1, 1);
			glVertex2f(width, height);
			glTexCoord2f(1, 0);
			glVertex2f(width, 0);
		}
		glEnd();
		glPopMatrix();
	}

	public static void set2D()
	{ // Set Up An Ortho View
		glMatrixMode(GL_PROJECTION); // Select Projection
		glPushMatrix(); // Push The Matrix
		glLoadIdentity(); // Reset The Matrix
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1); // Select
																		// Ortho
																		// Mode
		glMatrixMode(GL_MODELVIEW); // Select Modelview Matrix
		glPushMatrix(); // Push The Matrix
		glLoadIdentity(); // Reset The Matrix
	}

	public static void set3D() // Set Up A Perspective View
	{
		glMatrixMode(GL_PROJECTION); // Select Projection
		glPopMatrix(); // Pop The Matrix
		glMatrixMode(GL_MODELVIEW); // Select Modelview
		glPopMatrix(); // Pop The Matrix
	}
}
