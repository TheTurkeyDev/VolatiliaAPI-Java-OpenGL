package main.java.VolatiliaOGL.util;

public class Location3F
{
	protected float x;
	protected float y;
	private float z;

	public Location3F(float xloc, float yloc, float zloc)
	{
		x = xloc;
		y = yloc;
		z = zloc;
	}

	public enum Direction
	{
		North, South, East, West;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getZ()
	{
		return z;
	}

	public Location3F add(float xa, float ya, float za)
	{
		return new Location3F(x + xa, y + ya, z = za);
	}

	public Location3F subtract(float xa, float ya, float za)
	{
		return new Location3F(x - xa, y - ya, z - za);
	}

	public Location3F multiply(float xa, float ya, float za)
	{
		return new Location3F(x * xa, y * ya, z + za);
	}

	public Location3F divide(float xa, float ya, float za)
	{
		return new Location3F(x / xa, y / ya, z / za);
	}

	public boolean equals(Location3F loc)
	{
		return loc.getX() == x && loc.getY() == y && loc.getZ() == z;
	}

	public Location2F to2DLocation()
	{
		return new Location2F(x, z);
	}
}
