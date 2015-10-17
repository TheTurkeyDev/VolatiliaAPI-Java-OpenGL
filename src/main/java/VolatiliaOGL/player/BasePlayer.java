package main.java.VolatiliaOGL.player;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.game.World;

public class BasePlayer extends Entity
{
	private DisplayView camera;

	/**
	 * Represents the player that moves thourgh the game
	 * 
	 * @param view
	 *            that the player sees
	 */
	public BasePlayer(World world)
	{
		super(world);
	}

	/**
	 * renders the player to the screen
	 */
	public void render()
	{

	}

	/**
	 * updates the player
	 */
	public void update()
	{
		if(this.camera != null)
			this.camera.updateView();
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
