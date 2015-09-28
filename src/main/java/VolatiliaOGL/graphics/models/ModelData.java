package main.java.VolatiliaOGL.graphics.models;

public class ModelData
{
	private int id;
	private int vertexCount;
	
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
}
