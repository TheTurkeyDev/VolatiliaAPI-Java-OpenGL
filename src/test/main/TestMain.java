package test.main;

import test.main.screens.TestScreen;
import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.screen.ScreenManager;

public class TestMain
{

	public TestMain()
	{
		VolatiliaAPI api = new VolatiliaAPI("Test", 800, 600);
		api.load();
		
		ScreenManager.getInstance().addScreen(new TestScreen());
		ScreenManager.getInstance().setCurrentScreen("Test_Screen");
		
		api.start();
	}
	
	public static void main(String[] args)
	{
		new TestMain();
	}
}
