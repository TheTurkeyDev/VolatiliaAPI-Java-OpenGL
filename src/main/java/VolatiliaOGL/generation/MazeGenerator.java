package main.java.VolatiliaOGL.generation;

import java.util.ArrayList;
import java.util.Random;

import main.java.VolatiliaOGL.util.Location2F;

public class MazeGenerator
{
	private int xSize; private int ySize;
	private int width; private int height;
	private int xWallScale, yWallScale;
	private int[][] map;
	private ArrayList<Location2F> walls = new ArrayList<Location2F>();
	private Random r = new Random();

	private int currentX = 1;
	private int currentY = 1;

	private int nonWall = 0;
	private int wall = 1;

	private boolean generated = false;

	/**
	 * creates a maze with the given parameters
	 * @param multiple solutions
	 * @param x maze Size
	 * @param y maze size
	 * @param x wall size
	 * @param y wall size
	 */
	public void generate(boolean multiple, int x1, int y1, int x2, int y2)
	{
		xSize = x1;
		ySize = y1;
		xWallScale = x2;
		yWallScale = y2;
		map = new int[xSize][ySize];
		for (int y = 0; y < ySize; y++)
		{
			for (int x = 0; x < xSize; x++)
			{
				map[x][y] = wall;
			}
		}

		map[1][1] = nonWall;
		currentX = 1;
		currentY = 1;
		Location2F current = new Location2F(currentX, currentY);
		Location2F north = current.add(0, -1);
		Location2F east = current.add(1, 0);
		Location2F south = current.add(0, 1);
		Location2F west = current.add(-1, 0);

		if ((north.getY() > 0) && (map[(int) north.getX()][(int) north.getY()] == wall))
		{
			if(multiple)
				walls.add(north);
			else if((map[(int) north.getX()][(int) (north.getY() - 1)] == wall))
				walls.add(north);
		}
		if ((east.getX()< xSize) && (map[(int) east.getX()][(int) east.getY()] == wall))
		{
			if(multiple)
				walls.add(east);
			else if((map[(int) (east.getX() + 1)][(int) east.getY()] == wall))
				walls.add(east);
		}
		if ((south.getY()< ySize) && (map[(int) south.getX()][(int) south.getY()] == wall))
		{
			if(multiple)
				walls.add(south);
			else if((map[(int) south.getX()][(int) (south.getY() + 1)] == wall))
				walls.add(south);
		}
		if ((west.getX() > 0) && (map[(int) west.getX()][(int) west.getY()] == wall))
		{
			if(multiple)
				walls.add(west);
			else if((map[(int) (west.getX() - 1)][(int) west.getY()] == wall))
				walls.add(west);
		}

		while (walls.size() > 0)
		{
			int randomLoc = r.nextInt(walls.size());
			currentX = (int) ((Location2F)walls.get(randomLoc)).getX();
			currentY = (int) ((Location2F)walls.get(randomLoc)).getY();
			current = new Location2F(currentX, currentY);
			north = current.add(0, -1);
			east = current.add(1, 0);
			south = current.add(0, 1);
			west = current.add(-1, 0);

			if (!checkwalls(current))
			{
				map[currentX][currentY] = nonWall;
				walls.remove(randomLoc);

				if ((north.getY() - 1 > 0) && (map[(int) north.getX()][(int) north.getY()] == wall))
				{
					if(multiple)
						walls.add(north);
					else if((map[(int) north.getX()][(int) (north.getY() - 1)] == wall))
						walls.add(north);
				}
				if ((east.getX() + 1 < xSize) && (map[(int) east.getX()][(int) east.getY()] == wall))
				{
					if(multiple)
						walls.add(east);
					else if((map[(int) (east.getX() + 1)][(int) east.getY()] == wall))
						walls.add(east);
				}
				if ((south.getY() + 1 < ySize) && (map[(int) south.getX()][(int) south.getY()] == wall))
				{
					if(multiple)
						walls.add(south);
					else if((map[(int) south.getX()][(int) (south.getY() + 1)] == wall))
						walls.add(south);
				}
				if ((west.getX() - 1 > 0) && (map[(int) west.getX()][(int) west.getY()] == wall))
				{
					if(multiple)
						walls.add(west);
					else if((map[(int) (west.getX() - 1)][(int) west.getY()] == wall))
						walls.add(west);
				}
			}
			else
			{
				walls.remove(randomLoc);
			}
		}
		/*map[18][13] = nonWall;
		boolean Inaccessible = true;
		int i = 1;
		while (Inaccessible)
		{
			map[(18 - i)][13] = nonWall;
			map[18][(13 - i)] = nonWall;
			i++;
			if ((map[(18 - i)][13] == nonWall) || (map[18][(13 - i)] == nonWall) || (map[(18 - i)][12] == nonWall) || (map[17][(13 - i)] == nonWall))
			{
				Inaccessible = false;
			}
		}*/
		if(multiple)
		{
			for (int y = 1; y < ySize - 1; y++)
			{
				for (int x = 1; x < xSize - 1; x++)
				{
					if(isWall(x, y) && numWallsAround(new Location2F(x,y)) == 2)
					{
						map[x][y] = nonWall;
					}
				}
			}
		}
		height = ySize*yWallScale;
		width = xSize*xWallScale;
		generated = true;
	}

	private boolean checkwalls(Location2F loc)
	{
		Location2F north = loc.add(0, -1);
		Location2F east = loc.add(1, 0);
		Location2F south = loc.add(0, 1);
		Location2F west = loc.add(-1, 0);

		int yes = 0;
		if ((north.getY() >= 0 && map[(int) north.getX()][(int) north.getY()] == nonWall) || north.getY() > ySize)
			yes++;
		if ((east.getX() < xSize && map[(int) east.getX()][(int) east.getY()] == nonWall)|| east.getX() > xSize)
			yes++;
		if ((south.getY() < ySize && map[(int) south.getX()][(int) south.getY()] == nonWall) || south.getY() < 0)
			yes++;
		if ((west.getX() >= 0 && map[(int) west.getX()][(int) west.getY()] == nonWall) || west.getX() < 0 )
			yes++;
		return yes > 1;
	}

	private int numWallsAround(Location2F loc)
	{
		Location2F north = loc.add(0, -1);
		Location2F east = loc.add(1, 0);
		Location2F south = loc.add(0, 1);
		Location2F west = loc.add(-1, 0);

		int yes = 0;
		if (north.getY() >= 0 && map[(int) north.getX()][(int) north.getY()] == nonWall)
			yes++;
		if (east.getX() < xSize && map[(int) east.getX()][(int) east.getY()] == nonWall)
			yes++;
		if (south.getY() < ySize && map[(int) south.getX()][(int) south.getY()] == nonWall)
			yes++;
		if (west.getX() >= 0 && map[(int) west.getX()][(int) west.getY()] == nonWall)
			yes++;
		return yes;
	}

	/**
	 * returns if the maze has been generated
	 * @return is generated
	 */
	public boolean isGenrated()
	{
		return generated;
	}

	/**
	 * returns whether or not there is a wall in contact with the give location and size
	 * @param x location
	 * @param y lovation
	 * @param size of the object (only square)
	 * @return if there is a wall
	 */
	public boolean isWall(float x, float y, float size)
	{
		int x1 = (int)((x - size) / xWallScale);
		int x2 = (int)((x + size) / xWallScale);
		int y1 = (int)((y - size) / yWallScale);
		int y2 = (int)((y + size) / yWallScale);

		if (map[x1][y1] == wall)
		{
			return true;
		}
		if (map[x1][y2] == wall)
		{
			return true;
		}
		if (map[x2][y1] == wall)
		{
			return true;
		}
		if (map[x2][y2] == wall)
		{
			return true;
		}
		return false;
	}

	/**
	 * returns if there is a wall at the given location
	 * @param x location
	 * @param y location
	 * @return if there is a wall
	 */
	public boolean isWall(int x, int y)
	{
		if (map[x][y] == wall)
		{
			return true;
		}
		return false;
	}

	/**
	 * gets a random location that doesnt have a wall
	 * @return open location
	 */
	public Location2F getFreeLoc()
	{
		int x = r.nextInt(xSize);
		int y = r.nextInt(ySize);
		boolean valid = false;
		while (!valid)
		{
			if (map[x][y] == nonWall)
			{
				valid = true;
			}
			else
			{
				x = r.nextInt(xSize);
				y = r.nextInt(ySize);
			}
		}
		return new Location2F(x, y);
	}

	/**
	 * gets the width of the maze
	 * @return width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * gets the height of the maze
	 * @return height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * returns the size of the walls x direction
	 * @return x Wall size
	 */
	public int getxWallScale()
	{
		return xWallScale;
	}

	/**
	 * returns the size of the walls y direction
	 * @return y Wall size
	 */
	public int getyWallScale()
	{
		return yWallScale;
	}

	/**
	 * gets the full width of the maze
	 * @return x Size
	 */
	public int getXSize()
	{
		return xSize;
	}

	/**
	 * gets the full height of the maze
	 * @return x Size
	 */
	public int getYSize()
	{
		return ySize;
	}

	/**
	 * returns the maze array
	 * @return maze array
	 */
	public int[][] getMazeMap()
	{
		return map;
	}
}