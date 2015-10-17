package test.main.screens;

import main.java.VolatiliaOGL.screen.Screen;
import test.main.game.TestGame;

public class TestScreen extends Screen
{
	private TestGame game;

	public TestScreen()
	{
		super("Test_Screen");
		game = new TestGame();
	}

	public void render()
	{
		super.render();
		game.render();
	}
	
	public void update()
	{
		super.update();
		game.update();
	}

}
