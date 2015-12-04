package test.main;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.gui.text.TextMaster;
import main.java.VolatiliaOGL.renderEngine.MasterRenderer;
import main.java.VolatiliaOGL.screen.ScreenManager;
import main.java.VolatiliaOGL.util.Loader;

import org.lwjgl.opengl.Display;

import test.main.screens.TestGameScreen;
import test.main.screens.TestScreen;

public class CoreGame
{

	public static void main(String[] args)
	{
		VolatiliaAPI.createDisplay();
		VolatiliaAPI.initAPI();
		
		ScreenManager.getInstance().addScreen(new TestScreen());
		ScreenManager.getInstance().addScreen(new TestGameScreen());
		
		ScreenManager.getInstance().setCurrentScreen("Test Game Screen");

		while(!Display.isCloseRequested())
		{
			ScreenManager.getInstance().getCurrentScreen().render();
			
			VolatiliaAPI.updateDisplay();
		}
		
		MasterRenderer.INSTANCE.cleanUp();

		TextMaster.cleanUp();

		Loader.INSTANCE.CleanUp();

		VolatiliaAPI.closeDisplay();
	}
}
