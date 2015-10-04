package main.java.VolatiliaOGL.entity;

import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.renderers.Draw3D;
import main.java.VolatiliaOGL.graphics.textures.TextureManager;
import main.java.VolatiliaOGL.map.Map;

import org.lwjgl.util.vector.Vector3f;

public class Entity
{
	protected TexturedModel model;
	protected Map map;

	protected Vector3f position = new Vector3f(0, 0, 0);

	private float width;
	private float height;
	private float depth;

	private Vector3f velocity = new Vector3f(0, 0, 0);

	protected float pitch;
	protected float yaw;
	protected float roll;

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
		//this.yaw+=0.5f;
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

	public TexturedModel getTexturedModel()
	{
		return this.model;
	}

	public void setTextureModel(TexturedModel model)
	{
		this.model = model;
	}
}