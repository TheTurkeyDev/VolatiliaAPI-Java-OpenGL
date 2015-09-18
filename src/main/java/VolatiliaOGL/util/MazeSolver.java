package main.java.VolatiliaOGL.util;

import java.util.ArrayList;
import java.util.Random;

import main.java.VolatiliaOGL.generation.MazeGenerator;
import main.java.VolatiliaOGL.util.Location3F.Direction;

public class MazeSolver
{
	private MazeGenerator mazeGenerator;
	private int x = 1,y = 1;

	private ArrayList<Location2F> nogo = new ArrayList<Location2F>();
	private ArrayList<Location2F> visited = new ArrayList<Location2F>();

	private Direction last = null;

	public MazeSolver(MazeGenerator m)
	{
		mazeGenerator = m;
	}

	public boolean solve(Location2F start, Location2F end)
	{
		nogo.clear();
		visited.clear();
		x = (int) start.getX();
		y = (int) start.getY();
		int endX = (int) end.getX();
		int endY = (int) end.getY();
		visited.add(new Location2F(x, y));
		long startTime = System.currentTimeMillis();
		while(startTime + 10000 > System.currentTimeMillis())
		{
			move(choseDir());
			if(x == endX && y == endY)
				return true;
		}
		return false;
	}

	public Direction choseDir()
	{
		ArrayList<Direction> primary = new ArrayList<Direction>();
		ArrayList<Direction> secondary = new ArrayList<Direction>();
		Location2F above = new Location2F(x, y - 1);
		Location2F right = new Location2F(x + 1, y);
		Location2F down = new Location2F(x, y + 1);
		Location2F left = new Location2F(x - 1, y);
		if(!mazeGenerator.isWall((int) above.getX(), (int) above.getY()))
		{
			if(!isNoGo(above))
			{
				if(last == Direction.South || hasVisited(above))
					secondary.add(Direction.North);
				else
					primary.add(Direction.North);
			}
		}
		if(!mazeGenerator.isWall((int) right.getX(), (int) right.getY()))
		{
			if(!isNoGo(right))
			{
				if(last == Direction.West || hasVisited(right))
					secondary.add(Direction.East);
				else
					primary.add(Direction.East);
			}
		}
		if(!mazeGenerator.isWall((int) down.getX(), (int) down.getY()))
		{
			if(!isNoGo(down))
			{
				if(last == Direction.North || hasVisited(down))
					secondary.add(Direction.South);
				else
					primary.add(Direction.South);
			}
		}
		if(!mazeGenerator.isWall((int) left.getX(), (int) left.getY()))
		{
			if(!isNoGo(left))
			{
				if(last == Direction.East || hasVisited(left))
					secondary.add(Direction.West);
				else
					primary.add(Direction.West);
			}
		}

		if(primary.size() == 0)
		{
			primary.addAll(secondary);
			secondary.clear();
		}
		if(primary.size() == 1 && secondary.size() == 0)
		{
			nogo.add(new Location2F(x,y));
			return primary.get(0);
		}
		if(primary.size() == 2 && primary.contains(last))
		{
			primary.remove(last);
		}

		try{
		Random r = new Random();
		return primary.get(r.nextInt(primary.size()));
		}catch(IllegalArgumentException e){
		}
		return null;
	}

	public void move(Direction dir)
	{
		if(dir == null)
			return;
		if(dir.equals(Direction.North))
		{
			y--;
			last = Direction.North;
			visited.add(new Location2F(x , y));
		}
		else if(dir.equals(Direction.East))
		{
			x++;
			last = Direction.East;
			visited.add(new Location2F(x , y));
		}
		else if(dir.equals(Direction.South))
		{
			y++;
			last = Direction.South;
			visited.add(new Location2F(x , y));
		}
		else if(dir.equals(Direction.West))
		{
			x--;
			last = Direction.West;
			visited.add(new Location2F(x , y));
		}
	}

	public boolean hasVisited(int xloc, int yloc)
	{
		for(Location2F loc: visited)
			if(loc.equals(new Location2F(xloc, yloc)))
				return true;
		return false;
	}
	public boolean hasVisited(Location2F loc)
	{
		for(Location2F loc2: visited)
			if(loc2.equals(new Location2F(loc.getX(), loc.getY())))
				return true;
		return false;
	}
	public boolean isNoGo(Location2F loc)
	{
		for(Location2F loc2: nogo)
			if(loc2.equals(new Location2F(loc.getX(), loc.getY())))
				return true;
		return false;
	}
}
