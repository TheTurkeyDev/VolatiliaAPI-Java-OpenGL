package main.java.VolatiliaOGL.player;

import main.java.VolatiliaOGL.util.Location3F;

public class DisplayView
{
	private float x;
	private float y;
	private float z;

	private float pitch;
	private float yaw;
	private float roll;

	/**
	 * Creates a view that can be displayed on the screen
	 * 
	 * @param feild
	 *            of view
	 * @param aspect
	 * @param distance
	 *            to start rendering panes
	 * @param far
	 * @param nname
	 *            of view
	 */
	public DisplayView()
	{
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Gets the current x Cord of the View
	 * 
	 * @return X
	 */
	public float getX()
	{
		return x * -1;
	}

	/**
	 * gets the current y cord of the view
	 * 
	 * @return Y
	 */
	public float getY()
	{
		return y * -1;
	}

	/**
	 * gets the current z cord of the view
	 * 
	 * @return Z
	 */
	public float getZ()
	{
		return z * -1;
	}

	/**
	 * 
	 * @return the location of the camera
	 */
	public Location3F getLocation()
	{
		return new Location3F(x, y, z);
	}

	/**
	 * Sets the x cord
	 * 
	 * @param X
	 *            coordinate
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * Sets the y cord
	 * 
	 * @param Y
	 *            coordinate
	 */
	public void setY(float y)
	{
		this.y = y;
	}

	/**
	 * Sets the z cord
	 * 
	 * @param Z
	 *            coordinate
	 */
	public void setZ(float z)
	{
		this.z = z;
	}

	/**
	 * gets the X axis rotation (Pitch)
	 * 
	 * @return X axis rotation (Pitch)
	 */
	public float getPitch()
	{
		return pitch;
	}

	/**
	 * gets the Y axis rotation (Yaw)
	 * 
	 * @return Y axis rotation (Yaw)
	 */
	public float getYaw()
	{
		return yaw;
	}

	/**
	 * gets the Y axis rotation (Roll)
	 * 
	 * @return Y axis rotation (Roll)
	 */
	public float getRoll()
	{
		return roll;
	}

	/**
	 * sets the X axis rotation (Pitch)
	 * 
	 * @param X
	 *            axis rotation (Pitch)
	 */
	public void setPitch(float pitch)
	{
		this.pitch = pitch;
	}

	/**
	 * sets the Y axis rotation (Yaw)
	 * 
	 * @param Y
	 *            axis rotation (Yaw)
	 */
	public void setRY(float yaw)
	{
		this.yaw = yaw;
	}

	/**
	 * sets the Z axis rotation (Roll)
	 * 
	 * @param Z
	 *            axis rotation (Roll)
	 */
	public void setRZ(float roll)
	{
		this.roll = roll;
	}

	/**
	 * Rotates the view a given amount on the X axis
	 * 
	 * @param ammount
	 *            to rotate
	 */
	public void rotatePitch(float r)
	{
		pitch += r;
	}

	/**
	 * Rotates the view a given amount on the Y axis
	 * 
	 * @param ammount
	 *            to rotate
	 */
	public void rotateYaw(float r)
	{
		yaw += r;
	}

	/**
	 * Moves the view vertically
	 * 
	 * @param ammount
	 *            to move
	 */
	public void moveUp(float m)
	{
		y += m;
	}
}
