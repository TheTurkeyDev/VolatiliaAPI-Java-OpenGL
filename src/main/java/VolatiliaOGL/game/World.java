package main.java.VolatiliaOGL.game;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.entity.EntityManager;

public class World
{
	private EntityManager entManager;

	private int width;
	private int height;
	private int depth;

	public World(int width, int height, int depth)
	{
		entManager = new EntityManager();
	}

	/**
	 * renders the map objects
	 */
	public void render()
	{
		entManager.renderEntities();
	}

	/**
	 * Updates the map. represents a game tick.
	 */
	public void update()
	{
		entManager.updateEntities();
	}

	/**
	 * Gets the width of the map
	 * 
	 * @return Width
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * Gets the height of the map
	 * 
	 * @return Height
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * Gets the depth of the map
	 * 
	 * @return Depth
	 */
	public int getDepth()
	{
		return this.depth;
	}
	
	public void spawnEntity(Entity ent)
	{
		this.entManager.spawnEntity(ent);
	}
}