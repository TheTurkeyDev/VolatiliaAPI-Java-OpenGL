package main.java.VolatiliaOGL.map;

import java.util.ArrayList;
import java.util.List;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.util.Location3F;

public class Map
{
	private static int tileSize = 1;

	private List<Entity> entities = new ArrayList<Entity>();

	private Tile[] mapTiles;

	private int width;
	private int height;
	private int depth;

	public Map(int width, int height, int depth)
	{
		mapTiles = new Tile[width * height * depth];
	}

	/**
	 * renders the map objects
	 */
	public void render()
	{
		for(Entity ent : entities)
		{
			ent.render();
		}
	}

	/**
	 * Updates the map. represents a game tick.
	 */
	public void update()
	{
		for(int i = entities.size() - 1; i >= 0; i--)
		{
			Entity ent = entities.get(i);
			if(!ent.isAlive())
				entities.remove(i);
			else
				ent.update();
		}
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
	 * @return The size of tiles (3D default: 1, 2D recommended Default: 16 or multiples)
	 */
	public static int getTileSize()
	{
		return tileSize;
	}

	/**
	 * 
	 * @param tileSize
	 *            The default size of tiles
	 */
	public static void setTileSize(int tileSize)
	{
		Map.tileSize = tileSize;
	}

	/**
	 * Sets the given Tile at the given location
	 * 
	 * @param tile
	 * @param x
	 * @param y
	 * @param z
	 * @return If the tile was successfully set
	 */
	public boolean setTileAt(Tile t, int x, int y, int z)
	{
		this.mapTiles[((this.width * this.height) * z) + ((this.height * y) + x)] = t;
		return true;
	}

	/**
	 * Gets the tile at the given location
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return The tile at the given location
	 */
	public Tile getTileAt(int x, int y, int z)
	{
		return this.mapTiles[((this.width * this.height) * z) + ((this.height * y) + x)];
	}
	
	public List<Entity> getEntities()
	{
		return this.entities;
	}

	public boolean canMoveTo(Location3F loc, float size)
	{
		if(this.getTileAt((int) (loc.getX() / Map.tileSize), (int) (loc.getY() / Map.tileSize), 0).isSolid())
		{
			return false;
		}
		else if(this.getTileAt((int) ((loc.getX() + size) / Map.tileSize), (int) (loc.getY() / Map.tileSize), 0).isSolid())
		{
			return false;
		}
		else if(this.getTileAt((int) (loc.getX() / Map.tileSize), (int) ((loc.getY() + size) / Map.tileSize), 0).isSolid())
		{
			return false;
		}
		else if(this.getTileAt((int) ((loc.getX() + size) / Map.tileSize), (int) ((loc.getY() + size) / Map.tileSize), 0).isSolid())
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param ent
	 *            The entity to spawn into the map
	 */
	public void spawnEntity(Entity ent)
	{
		this.entities.add(ent);
	}
}
