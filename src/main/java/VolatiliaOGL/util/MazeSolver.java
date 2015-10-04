package main.java.VolatiliaOGL.util;

import java.util.ArrayList;
import java.util.Random;

import main.java.VolatiliaOGL.generation.MazeGenerator;

import org.lwjgl.util.vector.Vector2f;

public class MazeSolver
{
	private MazeGenerator mazeGenerator;
	private int x = 1,y = 1;

	private ArrayList<Vector2f> nogo = new ArrayList<Vector2f>();
	private ArrayList<Vector2f> visited = new ArrayList<Vector2f>();

	private Direction last = null;

	public MazeSolver(MazeGenerator m)
	{
		mazeGenerator = m;
	}

	public boolean solve(Vector2f start, Vector2f end)
	{
		nogo.clear();
		visited.clear();
		x = (int) start.getX();
		y = (int) start.getY();
		int endX = (int) end.getX();
		int endY = (int) end.getY();
		visited.add(new Vector2f(x, y));
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
		Vector2f above = new Vector2f(x, y - 1);
		Vector2f right = new Vector2f(x + 1, y);
		Vector2f down = new Vector2f(x, y + 1);
		Vector2f left = new Vector2f(x - 1, y);
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
			nogo.add(new Vector2f(x,y));
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
			visited.add(new Vector2f(x , y));
		}
		else if(dir.equals(Direction.East))
		{
			x++;
			last = Direction.East;
			visited.add(new Vector2f(x , y));
		}
		else if(dir.equals(Direction.South))
		{
			y++;
			last = Direction.South;
			visited.add(new Vector2f(x , y));
		}
		else if(dir.equals(Direction.West))
		{
			x--;
			last = Direction.West;
			visited.add(new Vector2f(x , y));
		}
	}

	public boolean hasVisited(int xloc, int yloc)
	{
		for(Vector2f loc: visited)
			if(loc.equals(new Vector2f(xloc, yloc)))
				return true;
		return false;
	}
	public boolean hasVisited(Vector2f loc)
	{
		for(Vector2f loc2: visited)
			if(loc2.equals(new Vector2f(loc.getX(), loc.getY())))
				return true;
		return false;
	}
	public boolean isNoGo(Vector2f loc)
	{
		for(Vector2f loc2: nogo)
			if(loc2.equals(new Vector2f(loc.getX(), loc.getY())))
				return true;
		return false;
	}
}
