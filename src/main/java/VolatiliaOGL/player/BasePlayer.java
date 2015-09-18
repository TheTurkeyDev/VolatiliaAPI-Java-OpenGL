package main.java.VolatiliaOGL.player;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.map.Map;

public class BasePlayer extends Entity
{
	private DisplayView camera;

	/**
	 * Represents the player that moves thourgh the game
	 * 
	 * @param view
	 *            that the player sees
	 */
	public BasePlayer(Map map, DisplayView view)
	{
		super(map);
		camera = view;
	}

	/**
	 * renders the player to the screen
	 */
	public void render()
	{
		camera.useView();
	}

	/**
	 * updates the player
	 */
	public void update()
	{

	}

	/**
	 * gets the current view of the player
	 * 
	 * @return current player view
	 */
	public DisplayView getView()
	{
		return camera;
	}

	/**
	 * sets the current players view
	 * 
	 * @param new players view
	 */
	public void setView(DisplayView view)
	{
		camera = view;
	}
}
