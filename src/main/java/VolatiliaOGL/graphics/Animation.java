package main.java.VolatiliaOGL.graphics;

public class Animation
{
	Texture[] images;
	int currentState = 0;

	boolean loops;
	boolean up = true;

	public Animation(Texture[] imgs, boolean loop)
	{
		images = imgs;
		loops = loop;
	}

	public Texture getCurrentTexture()
	{
		return images[currentState];
	}

	public void nextTexture()
	{
		if(loops)
			currentState++;
		else
			currentState--;

		if(currentState == images.length)
		{
			if(loops)
				currentState = 0;
			else
			{
				up = false;
				currentState--;
			}
		}
	}

	public void reset()
	{
		currentState = 0;
	}

	public void setState(int state)
	{
		currentState = state;
	}

	public int getState()
	{
		return currentState;
	}

}
