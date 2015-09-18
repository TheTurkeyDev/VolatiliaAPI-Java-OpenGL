package main.java.VolatiliaOGL.game;

import main.java.VolatiliaOGL.map.Map;
import main.java.VolatiliaOGL.player.BasePlayer;

public class GameBase
{
	private static GameBase game;

	private Map map;

	private BasePlayer player;

	public GameBase()
	{

	}

	/**
	 * renders game objects
	 */
	public void render()
	{
		map.render();
		if(player != null)
			player.render();
	}

	/**
	 * Updates the game. represents a game tick.
	 */
	public void update()
	{
		map.update();
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
		this.map = null;
	}

	/**
	 * @return the current map
	 */
	public Map getMap()
	{
		return map;
	}

	/**
	 * Set's the current map
	 * 
	 * @param map
	 */
	public void setMap(Map map)
	{
		this.map = map;
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