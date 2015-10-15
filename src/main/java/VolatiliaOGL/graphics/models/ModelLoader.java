package main.java.VolatiliaOGL.graphics.models;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ModelLoader
{
	public static final ModelLoader INSTANCE = new ModelLoader();

	private List<Integer> loadedModels = new ArrayList<Integer>();
	private List<Integer> loadedAttributes = new ArrayList<Integer>();

	public ModelData loadToModelData(float[] positions, float[] textureCords, float[] normals, int[] indices, float furthest)
	{
		int id = createVAO();
		this.bindIndeciesBuffer(indices);
		storeDatainAttribList(0, 3, positions);
		storeDatainAttribList(1, 2, textureCords);
		storeDatainAttribList(2, 3, normals);
		unbindID();
		return new ModelData(id, indices.length, furthest);
	}

	public void RemoveAllStoredModels()
	{
		for(int id : this.loadedModels)
			GL30.glDeleteVertexArrays(id);
		for(int id : this.loadedAttributes)
			GL15.glDeleteBuffers(id);
	}

	public int createVAO()
	{
		int id = GL30.glGenVertexArrays();
		this.loadedModels.add(id);
		GL30.glBindVertexArray(id);
		return id;
	}

	private void storeDatainAttribList(int attributeNumber, int cordSize, float[] data)
	{
		int id = GL15.glGenBuffers();
		this.loadedAttributes.add(id);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, cordSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	private void unbindID()
	{
		GL30.glBindVertexArray(0);
	}

	private void bindIndeciesBuffer(int[] indices)
	{
		int id = GL15.glGenBuffers();
		this.loadedModels.add(id);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
		IntBuffer buffer = this.storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	private IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
