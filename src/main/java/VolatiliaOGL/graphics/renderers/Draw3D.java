package main.java.VolatiliaOGL.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Draw3D
{
	
	/**
	 * Draws a 3D rectangle
	 * @param x location
	 * @param y location
	 * @param z location
	 * @param width of rectangle
	 * @param height of rectangle
	 * @param depth of rectangle
	 * @param rotate of rectangle
	 * @param color of rectangle
	 */
	public static void drawRect(float x, float y, float z, float width, float height, float depth, float rotate, Color color)
	{
		glEnable(GL_DEPTH_TEST);
		glPushMatrix();
		{
			float[] rgb = color.getFloats(color);
			glColor3f(rgb[0], rgb[1], rgb[2]);
			glTranslatef(x,y,z);
			glRotatef(rotate,1,1,0);
			glBegin(GL_QUADS);
			{
				
				//Face 1
				glVertex3f(0,0,0);
				glVertex3f(0,height,0);
				glVertex3f(width,height,0);
				glVertex3f(width,0,0);
				
				//Face 2
				glVertex3f(0,0,0);
				glVertex3f(0,0,depth);
				glVertex3f(0,height,depth);
				glVertex3f(0,height,0);
				
				//Face 3
				glVertex3f(0,0,0);
				glVertex3f(width,0,0);
				glVertex3f(width,0,depth);
				glVertex3f(0,0,depth);
				
				
				//Face 4
				glVertex3f(0,0,depth);
				glVertex3f(0,height,depth);
				glVertex3f(width,height,depth);
				glVertex3f(width,0,depth);
				
				//Face 5
				glVertex3f(width,0,0);
				glVertex3f(width,0,depth);
				glVertex3f(width,height,depth);
				glVertex3f(width,height,0);
				
				
				//Face 6
				glVertex3f(0,height,0);
				glVertex3f(width,height,0);
				glVertex3f(width,height,depth);
				glVertex3f(0,height,depth);
			}
			glEnd();
		}
		glPopMatrix();
	}
}
