package test.main;

import main.java.VolatiliaOGL.VolatiliaAPI;

public class TestMain
{

	public TestMain()
	{
		VolatiliaAPI api = new VolatiliaAPI("Test", 800, 600);
		api.load();
		
		api.start();
	}
	
	public static void main(String[] args)
	{
		new TestMain();
	}
}
