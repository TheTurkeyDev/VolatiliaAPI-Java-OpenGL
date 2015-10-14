package main.java.VolatiliaOGL.graphics.models;

public class ModelData
{
	private int id;
	private int vertexCount;

	private float shineDampen = 1;
	private float refelction = 0;

	public ModelData(int id, int vertexCount)
	{
		this.id = id;
		this.vertexCount = vertexCount;
	}

	public int getId()
	{
		return id;
	}

	public int getVertexCount()
	{
		return vertexCount;
	}

	public float getShineDampen()
	{
		return shineDampen;
	}

	public void setShineDampen(float shineDampen)
	{
		this.shineDampen = shineDampen;
	}

	public float getRefelction()
	{
		return refelction;
	}

	public void setRefelction(float refelction)
	{
		this.refelction = refelction;
	}
}
