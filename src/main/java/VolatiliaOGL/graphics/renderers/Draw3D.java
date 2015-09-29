package main.java.VolatiliaOGL.graphics.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import main.java.VolatiliaOGL.graphics.models.ModelData;


public class Draw3D
{
	
	public static void draw3D(ModelData data)
	{
		GL30.glBindVertexArray(data.getId());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, data.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
	}
}
