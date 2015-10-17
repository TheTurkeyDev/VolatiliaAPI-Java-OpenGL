package main.java.VolatiliaOGL.entity;

import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Entity
{
	private float moveSpeed = 20;
	private float turnSpeed = 160;
	private float jumpSpeed = 30;
	private float gravity = -50;
	
	protected TexturedModel model;
	protected World map;

	protected Vector3f position = new Vector3f(0, 0, 0);

	private float width;
	private float height;
	private float depth;

	private Vector3f velocity = new Vector3f(0, 0, 0);

	protected float pitch;
	protected float yaw;
	protected float roll;

	private boolean isAlive = true;
	private boolean isAirBorne = false;

	/**
	 * Creates a new Entity at the given location
	 */
	public Entity(World map)
	{
		this.map = map;
	}

	/**
	 * Updates the entity
	 */
	public void update()
	{
		this.position.x -= this.velocity.x;
		this.position.y -= this.velocity.y;
		this.position.z -= this.velocity.z;
	}

	/**
	 * Renders the entity at its current location
	 */
	public void render()
	{
		
	}

	/**
	 * @return if the entity is still alive
	 */
	public boolean isAlive()
	{
		return isAlive;
	}

	/**
	 * kills the entity and slots it to be removed
	 */
	public void kill()
	{
		this.isAlive = false;
	}

	/**
	 * 
	 * @param loc
	 *            Location to set the player to
	 */
	public void setLocation(Vector3f loc)
	{
		this.position.set(loc);
	}

	/**
	 * gets the players location
	 * 
	 * @return
	 */
	public Vector3f getLocation()
	{
		return this.position;
	}

	/**
	 * 
	 * @return the width of the entities hit-box
	 */
	public float getWidth()
	{
		return this.width;
	}

	/**
	 * @return the height of the entities hit-box
	 */
	public float getHeight()
	{
		return this.height;
	}

	/**
	 * @return the depth of the entities hit-box
	 */
	public float getDepth()
	{
		return this.depth;
	}

	public float getPitch()
	{
		return this.pitch;
	}

	public float getYaw()
	{
		return this.yaw;
	}

	public float getRoll()
	{
		return this.roll;
	}
	
	public float getMoveSpeed()
	{
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed)
	{
		this.moveSpeed = moveSpeed;
	}

	public float getTurnSpeed()
	{
		return turnSpeed;
	}

	public void setTurnSpeed(float turnSpeed)
	{
		this.turnSpeed = turnSpeed;
	}

	public float getJumpSpeed()
	{
		return jumpSpeed;
	}

	public void setJumpSpeed(float jumpSpeed)
	{
		this.jumpSpeed = jumpSpeed;
	}

	public float getGravity()
	{
		return gravity;
	}

	public void setGravity(float gravity)
	{
		this.gravity = gravity;
	}

	public TexturedModel getTexturedModel()
	{
		return this.model;
	}

	public void setTextureModel(TexturedModel model)
	{
		this.model = model;
	}

	public boolean isAirBorne()
	{
		return isAirBorne;
	}

	public void setAirBorne(boolean isAirBorne)
	{
		this.isAirBorne = isAirBorne;
	}
}