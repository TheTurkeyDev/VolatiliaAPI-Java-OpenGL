package main.java.VolatiliaOGL.graphics.renderers;

import main.java.VolatiliaOGL.graphics.models.TexturedModel;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Draw3D
{
	
	public static void draw3D(TexturedModel textureModel)
	{
		GL30.glBindVertexArray(textureModel.getModelData().getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureModel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, textureModel.getModelData().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
