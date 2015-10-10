package main.java.VolatiliaOGL.player;

import org.lwjgl.util.vector.Vector3f;

public class DisplayView
{
	private Vector3f position = new Vector3f(0, 0, 0);

	private float pitch = 0;
	private float yaw = 0;
	private float roll = 0;

	/**
	 * Creates a view that can be displayed on the screen
	 */
	public DisplayView()
	{
		
	}
	
	/**
	 * 
	 * @return the position of the camera
	 */
	public Vector3f getPosition()
	{
		return this.position;
	}

	/**
	 * Sets the x cord
	 * 
	 * @param X
	 *            coordinate
	 */
	public void setX(float x)
	{
		this.position.x = x;
	}

	/**
	 * Sets the y cord
	 * 
	 * @param Y
	 *            coordinate
	 */
	public void setY(float y)
	{
		this.position.y = y;
	}

	/**
	 * Sets the z cord
	 * 
	 * @param Z
	 *            coordinate
	 */
	public void setZ(float z)
	{
		this.position.z = z;
	}
	
	public void setPosition(Vector3f pos)
	{
		this.position.set(pos);
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
}
