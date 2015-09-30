package main.java.VolatiliaOGL.entity;

import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.renderers.Draw3D;
import main.java.VolatiliaOGL.graphics.textures.TextureManager;
import main.java.VolatiliaOGL.map.Map;
import main.java.VolatiliaOGL.util.Location3F;

public class Entity
{
	protected TexturedModel model;
	protected Map map;

	protected float x;
	protected float y;
	protected float z;

	private float width;
	private float height;
	private float depth;

	private float xVel = 0;
	private float yVel = 0;
	private float zVel = 0;

	protected float rotationX;
	protected float rotationY;
	protected float rotationZ;

	private boolean isAlive = true;

	/**
	 * Creates a new Entity at the given location
	 */
	public Entity(Map map)
	{
		this.map = map;
	}

	/**
	 * 
	 * @param Texture
	 *            Manager to load textures
	 */
	public void loadTextures(TextureManager manager)
	{

	}

	/**
	 * starts the entity on a path of the given angles relative to each axis
	 * 
	 * @param X
	 *            -Axis speed
	 * @param Y
	 *            -Axis speed
	 * @param Z
	 *            -Axis speed
	 */
	public void launch(float rX, float rY, float rZ, float velocity)
	{
		xVel = (float) (velocity * Math.cos(Math.toRadians(rY + 90)));
		yVel = (float) (velocity * Math.sin(Math.toRadians(rX)));
		zVel = (float) (velocity * Math.sin(Math.toRadians(rY + 90)));
	}

	/**
	 * Updates the entity
	 */
	public void update()
	{
		x -= xVel;
		y -= yVel;
		z -= zVel;
	}

	/**
	 * Renders the entity at its current location
	 */
	public void render()
	{
		Draw3D.draw3D(this);
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
	public void setLocation(Location3F loc)
	{
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
	}

	/**
	 * gets the players location
	 * 
	 * @return
	 */
	public Location3F getLocation()
	{
		return new Location3F(x, y, z);
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

	public float getrotationX()
	{
		return this.rotationX;
	}

	public float getrotationY()
	{
		return this.rotationY;
	}

	public float getrotationZ()
	{
		return this.rotationZ;
	}

	public TexturedModel getTexturedModel()
	{
		return this.model;
	}
}