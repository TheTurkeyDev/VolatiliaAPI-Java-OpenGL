package main.java.VolatiliaOGL.models;

public class RawModel
{
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int id, int vertecies)
	{
		this.vaoID = id;
		this.vertexCount = vertecies;
	}

	public int getVaoID()
	{
		return vaoID;
	}

	public int getVertexCount()
	{
		return vertexCount;
	}
}
