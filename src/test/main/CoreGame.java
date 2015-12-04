package test.main;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.screen.ScreenManager;
import test.main.screens.TestGameScreen;
import test.main.screens.TestScreen;

public class CoreGame
{

	public static void main(String[] args)
	{
		VolatiliaAPI.createDisplay();
		
		ScreenManager.getInstance().addScreen(new TestScreen());
		ScreenManager.getInstance().addScreen(new TestGameScreen());
		
		ScreenManager.getInstance().setCurrentScreen("Test Game Screen");
		
		VolatiliaAPI.startAPIRun();

	}
}
