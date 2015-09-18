package main.java.VolatiliaOGL.player;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class DisplayView
{
	private float x;
	private float y;
	private float z;
	private float rx;
	private float ry;
	private float rz;

	private float fov;
	private float aspect;
	private float near;
	private float far;
	
	private String name;
	
	private boolean moveThroughWalls = false;

	/**
	 * Creates a view that can be displayed on the screen
	 * @param feild of view
	 * @param aspect
	 * @param distance to start rendering panes
	 * @param far
	 * @param nname of view
	 */
	public DisplayView(float fov, float aspect, float near, float far, String n)
	{
		x = 0;
		y = 0;
		z = 0;
		rx = 0;
		ry = 0;
		rz = 0;

		this.fov = fov;
		this.aspect = aspect;
		this.near = near;
		this.far = far;
		
		name = n;
		initProjection();
	}

	/**
	 * Initiates the view
	 */
	private void initProjection()
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov,aspect,near,far);
		glMatrixMode(GL_MODELVIEW);
	}

	/**
	 * Displays the view on the players Screen
	 */
	public void useView()
	{
		glRotatef(rx,1,0,0);
		glRotatef(ry,0,1,0);
		glRotatef(rz,0,0,1);
		glTranslatef(x,y,z);
	}

	/**
	 * Gets the current x Cord of the View
	 * @return X
	 */
	public float getX()
	{
		return x*-1;
	}

	/**
	 * gets the current y cord of the view
	 * @return Y
	 */
	public float getY()
	{
		return y*-1;
	}

	/**
	 * gets the current z cord of the view
	 * @return Z
	 */
	public float getZ()
	{
		return z*-1;
	}

	/**
	 * Sets the x cord
	 * @param X coordinate
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * Sets the y cord
	 * @param Y coordinate
	 */
	public void setY(float y)
	{
		this.y = y;
	}

	/**
	 * Sets the z cord
	 * @param Z coordinate
	 */
	public void setZ(float z)
	{
		this.z = z;
	}

	/**
	 * gets the X axis rotation
	 * @return X axis rotation
	 */
	public float getRX()
	{
		return rx;
	}

	/**
	 * gets the Y axis rotation
	 * @return Y axis rotation
	 */
	public float getRY()
	{
		return ry;
	}

	/**
	 * gets the Y axis rotation
	 * @return Y axis rotation
	 */
	public float getRZ()
	{
		return rz;
	}

	/**
	 * sets the X axis rotation
	 * @param X axis rotation
	 */
	public void setRX(float rx)
	{
		this.rx = rx;
	}

	/**
	 * sets the Y axis rotation
	 * @param Y axis rotation
	 */
	public void setRY(float ry)
	{
		this.ry = ry;
	}

	/**
	 * sets the Z axis rotation
	 * @param Z axis rotation
	 */
	public void setRZ(float rz)
	{
		this.rz = rz;
	}
	
	/**
	 * Rotates the view a given amount on the X axis
	 * @param ammount to rotate
	 */
	public void rotateX(float r)
	{
		rx+=r;
	}
	
	/**
	 * Rotates the view a given amount on the Y axis
	 * @param ammount to rotate
	 */
	public void rotateY(float r)
	{
		ry+=r;
	}
	
	
	/**
	 * Moves the view vertically
	 * @param ammount to move
	 */
	public void moveUp(float m)
	{
		y+=m;
	}

	/**
	 * returns if the view can move through objects
	 * @return can move through walls
	 */
	public boolean canMoveThroughWalls()
	{
		return moveThroughWalls;
	}
	
	/**
	 * sets whether or not the view can move through objects
	 * @param can move through walls
	 */
	public void setMoveThroughWalls(boolean toggle)
	{
		moveThroughWalls = toggle;	
	}
	
	/**
	 * gets the name of the view
	 * @return
	 */
	public String getName()
	{
		return name;
	}
}
