package main.java.VolatiliaOGL.textures;

import main.java.VolatiliaOGL.util.Loader;

public class TerrainTexturePack
{
	private TerrainTexture backgroundTexture;
	private TerrainTexture rTexture;
	private TerrainTexture gTexture;
	private TerrainTexture bTexture;
	
	public TerrainTexturePack(String backgroundPath, String rPath, String gPath, String bPath)
	{
		this.backgroundTexture = new TerrainTexture(Loader.INSTANCE.loadTexture(backgroundPath));
		this.rTexture = new TerrainTexture(Loader.INSTANCE.loadTexture(rPath));
		this.gTexture =  new TerrainTexture(Loader.INSTANCE.loadTexture(gPath));
		this.bTexture =  new TerrainTexture(Loader.INSTANCE.loadTexture(bPath));
	}

	public TerrainTexture getBackgroundTexture()
	{
		return backgroundTexture;
	}

	public TerrainTexture getrTexture()
	{
		return rTexture;
	}

	public TerrainTexture getgTexture()
	{
		return gTexture;
	}

	public TerrainTexture getbTexture()
	{
		return bTexture;
	}
}