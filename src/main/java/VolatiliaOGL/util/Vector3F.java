package main.java.VolatiliaOGL.util;

public class Vector3F
{
	float x;
	float y;
	float z;
	
	public Vector3F()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3F(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length()
	{
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float dotProduct(Vector3F v)
	{
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	public Vector3F normalize()
	{
		float l = length();
		
		x/=l;
		y/=l;
		z/=l;
		
		return this;
	}
	
	public Vector3F add(Vector3F v)
	{
		return new Vector3F(x+v.getX(),y+v.getY(), z+v.getZ());
	}

	public Vector3F subtract(Vector3F v)
	{
		return new Vector3F(x-v.getX(), y-v.getY(), z+v.getZ());
	}

	public Vector3F multiply(Vector3F v)
	{
		return new Vector3F(x*v.getX(), y*v.getY(), z+v.getZ());
	}

	public Vector3F divide(Vector3F v)
	{
		return new Vector3F(x/v.getX(), y/v.getY(), z+v.getZ());
	}
	
	public float getX()
	{
		return x;
	}
	public float getZ()
	{
		return y;
	}
	public float getY()
	{
		return z;
	}
	
	public String toString()
	{
		return "" + this + "  (" + x + ", " + y + ")";
	}
}
