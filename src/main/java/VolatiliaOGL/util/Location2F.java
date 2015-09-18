package main.java.VolatiliaOGL.util;

public class Location2F extends Location3F
{

	public Location2F(float xloc, float yloc)
	{
		super(xloc, yloc, 0);
	}

	public Location2F add(float xa, float ya)
	{
		return new Location2F(x + xa, y + ya);
	}

	public Location2F subtract(float xa, float ya)
	{
		return new Location2F(x - xa, y - ya);
	}

	public Location2F multiply(float xa, float ya)
	{
		return new Location2F(x * xa, y * ya);
	}

	public Location2F divide(float xa, float ya)
	{
		return new Location2F(x / xa, y / ya);
	}

	public Location2F add(Location2F loc)
	{
		return new Location2F(x + loc.getX(), y + loc.getY());
	}

	public Location2F subtract(Location2F loc)
	{
		return new Location2F(x - loc.getX(), y - loc.getY());
	}

	public Location2F multiply(Location2F loc)
	{
		return new Location2F(x * loc.getX(), y * loc.getY());
	}

	public Location2F divide(Location2F loc)
	{
		return new Location2F(x / loc.getX(), y / loc.getY());
	}

	public boolean equals(Location2F loc)
	{
		return loc.getX() == x && loc.getY() == y;
	}

	public double distenceTo(Location2F loc)
	{
		return Math.sqrt(Math.pow(loc.getX() - x, 2) + Math.pow(loc.getY() + y, 2));
	}
}
