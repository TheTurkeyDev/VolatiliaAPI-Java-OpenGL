package main.java.VolatiliaOGL.graphics.models;

import main.java.VolatiliaOGL.graphics.textures.Texture;

public class TexturedModel
{
	private ModelData modelData;
	private Texture texture;

	public TexturedModel(ModelData modelData, Texture texture)
	{
		this.modelData = modelData;
		this.texture = texture;
	}
	
	public ModelData getModelData()
	{
		return modelData;
	}

	public Texture getTexture()
	{
		return texture;
	}
}
