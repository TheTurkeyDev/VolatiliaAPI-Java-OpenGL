package main.java.VolatiliaOGL.game;

import java.util.HashMap;
import java.util.Map;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.map.Tile;

public class GameRegistry
{
	public GameRegistry instance = new GameRegistry();
	
	private static Map<String, Entity> entitiesFromName = new HashMap<String, Entity>();
	private static HashMap<String, Tile> tileFromName = new HashMap<String, Tile>();
	
	
	public static void registerEntity(Entity ent, String name)
	{
		entitiesFromName.put(name, ent);
	}
	
	public static Entity getEntityFromName(String name)
	{
		return entitiesFromName.get(name);
	}
	
	public static void registerTile(Tile tile, String name)
	{
		tileFromName.put(name, tile);
	}
	
	public static Tile getTileFromName(String name)
	{
		return tileFromName.get(name);
	}
}
