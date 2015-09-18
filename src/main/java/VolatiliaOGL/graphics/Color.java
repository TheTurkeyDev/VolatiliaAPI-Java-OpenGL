package main.java.VolatiliaOGL.graphics;

public enum Color
{
	White,
	Gray,
	Pink,
	Red,
	Brown,
	Orange,
	Yellow,
	Green,
	Dark_Green,
	Cyan,
	Blue,
	Violet,
	Black;
	
	/**
	 * gets the float index of a given color
	 * 
	 * Array Positions:
	 * 0 = Red
	 * 1 = Green
	 * 2 = Blue
	 * 
	 * @param Color to get the values of
	 * @return float array with the values
	 */
	public float[] getFloats(Color color)
	{
		float[] floats = new float[3];
		switch(color)
		{
			case White:
				floats[0] = 1f;
				floats[1] = 1f;
				floats[2] = 1f;
				return floats;
			case Gray:
				floats[0] = .5f;
				floats[1] = .5f;
				floats[2] = .5f;
				return floats;
			case Pink:
				floats[0] = 1f;
				floats[1] = .75f;
				floats[2] = .8f;
				return floats;
			case Red:
				floats[0] = 1f;
				floats[1] = 0f;
				floats[2] = 0f;
				return floats;
			case Brown:
				floats[0] = .59f;
				floats[1] = .29f;
				floats[2] = 0f;
				return floats;
			case Orange:
				floats[0] = 1f;
				floats[1] = .65f;
				floats[2] = 0f;
				return floats;
			case Yellow:
				floats[0] = 1f;
				floats[1] = 1f;
				floats[2] = 0f;
				return floats;
			case Green:
				floats[0] = 0f;
				floats[1] = 1f;
				floats[2] = 0f;
				return floats;
			case Dark_Green:
				floats[0] = .02f;
				floats[1] = .4f;
				floats[2] = .03f;
				return floats;
			case Cyan:
				floats[0] = 0f;
				floats[1] = 1f;
				floats[2] = 1f;
				return floats;
			case Blue:
				floats[0] = 0f;
				floats[1] = 0f;
				floats[2] = 1f;
				return floats;
			case Violet:
				floats[0] = .56f;
				floats[1] = 0f;
				floats[2] = 1f;
				return floats;
			case Black:
				floats[0] = 0f;
				floats[1] = 0f;
				floats[2] = 0f;
				return floats;
			default:
				floats[0] = 0f;
				floats[1] = 0f;
				floats[2] = 0f;
				return floats;
				
		}
	}
	
}
