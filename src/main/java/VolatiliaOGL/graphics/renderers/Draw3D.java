package main.java.VolatiliaOGL.graphics.renderers;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.shaders.StaticShader;
import main.java.VolatiliaOGL.util.MatrixMath;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public class Draw3D
{
	public static void draw3D(Entity ent)
	{
		TexturedModel textureModel = ent.getTexturedModel();
		GL30.glBindVertexArray(textureModel.getModelData().getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		Matrix4f transformations = MatrixMath.createTransformationMatrix(new Vector3f(ent.getLocation().toVector3f()), ent.getrotationX(), ent.getrotationY(), ent.getrotationZ(), 1);
		StaticShader.INSTANCE.loadTransformationMatrix(transformations);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureModel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, textureModel.getModelData().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
