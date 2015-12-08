package test.main.screens;

import main.java.VolatiliaOGL.screen.Screen;
import test.main.game.Game;

public class TestGameScreen extends Screen
{
	private Game game = new Game(this);

	public TestGameScreen()
	{
		super("Test Game Screen");
	}

	public void setUp()
	{
		game.loadGame();
		super.setUp();
	}

	public void render()
	{
		game.render();
		super.render();
	}

	public void cleanUp()
	{
		game.cleanUp();
		super.cleanUp();
	}

}
