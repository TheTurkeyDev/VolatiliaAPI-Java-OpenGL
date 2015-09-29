package main.java.VolatiliaOGL.graphics.renderers;

import main.java.VolatiliaOGL.graphics.models.ModelData;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Draw2D
{
	public static void draw2D(ModelData data)
	{
		GL30.glBindVertexArray(data.getId());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, data.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public static void draw2D(TexturedModel textureModel)
	{
		ModelData data = textureModel.getModelData();
		Draw2D.draw2D(data);
	}
}
