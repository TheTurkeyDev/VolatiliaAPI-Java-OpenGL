package test.main.screens;

import main.java.VolatiliaOGL.gui.GuiButton;
import main.java.VolatiliaOGL.screen.Screen;

public class TestScreen extends Screen
{

	public TestScreen()
	{
		super("Test_Screen");
		
		super.addGuiComponent(new GuiButton(this, "test_button", "", 100, 100, 256, 256));
	}
	
	public void render()
	{
		super.render();
	}

}
