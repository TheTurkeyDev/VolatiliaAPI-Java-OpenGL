package main.java.VolatiliaOGL.map;


public abstract class Tile
{
	private String name;
	
	public Tile(String name)
	{
		this.name = name;
	}
	
	/**
	 * returns whether or not an object is able to move
	 */
	public boolean isSolid()
	{
		return false;
	}
	
	/**
	 * Updates the object
	 */
	public void update()
	{
		
	}
	
	public String getTileName()
	{
		return this.name;
	}
}