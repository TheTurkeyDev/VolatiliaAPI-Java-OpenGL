package main.java.VolatiliaOGL.game;

import main.java.VolatiliaOGL.player.BasePlayer;

public class GameBase
{
	private static GameBase game;

	protected World world;

	protected BasePlayer player;

	public GameBase()
	{

	}

	/**
	 * renders game objects
	 */
	public void render()
	{
		world.render();
		if(player != null)
			player.render();
	}

	/**
	 * Updates the game. represents a game tick.
	 */
	public void update()
	{
		world.update();
		if(player != null)
			player.update();
	}

	/**
	 * Polls the out side input i.e. Keyboard and Mouse
	 */
	public void pollInput()
	{

	}

	public void clearGame()
	{
		this.world = null;
	}

	/**
	 * @return the current map
	 */
	public World getWorld()
	{
		return world;
	}

	/**
	 * Set's the current map
	 * 
	 * @param map
	 */
	public void setWorld(World world)
	{
		this.world = world;
	}

	/**
	 * Gets the instance of the game
	 * 
	 * @return
	 */
	public static GameBase getGame()
	{
		return game;
	}
}