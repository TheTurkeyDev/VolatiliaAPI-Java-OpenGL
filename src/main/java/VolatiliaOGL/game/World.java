package main.java.VolatiliaOGL.game;

import java.util.ArrayList;
import java.util.List;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.entity.EntityManager;
import main.java.VolatiliaOGL.graphics.renderers.RenderManager;

public class World
{
	private EntityManager entManager;

	private int width;
	private int height;
	private int depth;
	
	private List<Terrain> terrain = new ArrayList<Terrain>();

	public World(int width, int height, int depth)
	{
		entManager = new EntityManager();
	}

	/**
	 * renders the map objects
	 */
	public void render()
	{
		RenderManager.terrainRenderer.render(terrain);
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
	
	/**
	 * 
	 * @param ent to spawn into the world
	 */
	public void spawnEntity(Entity ent)
	{
		this.entManager.spawnEntity(ent);
	}
	
	/**
	 * Adds terrain to the game
	 * @param terrain to add
	 */
	public void addTerrain(Terrain t)
	{
		this.terrain.add(t);
	}
	
	/**
	 * 
	 * @return List of the terrain in this world
	 */
	public List<Terrain> getWorldTerrain()
	{
		return this.terrain;
	}
}