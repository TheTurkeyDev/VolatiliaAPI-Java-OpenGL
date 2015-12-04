package main.java.VolatiliaOGL.screen;

import java.util.ArrayList;

public class ScreenManager
{
	private Screen currentScreen;
	private static ScreenManager sm = new ScreenManager();

	private ArrayList<Screen> screens = new ArrayList<Screen>();

	public ScreenManager()
	{
		sm = this;
		
	}

	/**
	 * gets the current screen being displayed
	 * @return the current screen
	 */
	public Screen getCurrentScreen()
	{
		return currentScreen;
	}

	/**
	 * sets the current screen to the given screen
	 * @param screen to be set to
	 */
	public void setCurrentScreen(Screen newScreen)
	{
		if(currentScreen!=null)
			currentScreen.cleanUp();
		currentScreen = newScreen;
		currentScreen.setUp();
	}

	/**
	 * sets the current screen that is being displayed to the given screen
	 * @param screen to be displayed
	 * @return if the screen was changed or not
	 */
	public boolean setCurrentScreen(String newScreen)
	{
		for(Screen s: screens)
		{
			if(s.getName().equalsIgnoreCase(newScreen))
			{
				if(currentScreen!=null)
					currentScreen.cleanUp();
				currentScreen = s;
				currentScreen.setUp();
				return true;
			}
		}
		return false;
	}

	/**
	 * gets the ScreenManager class instance
	 * @return
	 */
	public static ScreenManager getInstance()
	{
		return sm;
	}

	/**
	 * adds the given screen to possible screens
	 * @param screen
	 */
	public void addScreen(Screen screen)
	{
		screens.add(screen);
	}

	/**
	 * gets the screen with the given name
	 * @param name of the screen to get
	 * @return Screen
	 */
	public Screen getScreen(String name)
	{
		for(Screen s: screens)
		{
			if(s.getName().equalsIgnoreCase(name))
			{
				return s;
			}
		}
		return null;
	}
	
	public void finalCleanUpAllScreens()
	{
		for(Screen s: screens)
		{
			s.finalCleanUp();
		}
	}
}