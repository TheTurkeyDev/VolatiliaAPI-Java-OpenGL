package main.java.VolatiliaOGL.renderEngine;

import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.entities.Entity;
import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.models.TexturedModel;
import main.java.VolatiliaOGL.shaders.basic.StaticShader;
import main.java.VolatiliaOGL.textures.ModelTexture;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

public class EntityRenderer
{
	private StaticShader shader;

	public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix)
	{
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render(Map<TexturedModel, List<Entity>> entities)
	{
		for(TexturedModel model: entities.keySet())
		{
			this.prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity ent: batch)
			{
				this.prepareInstance(ent);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			this.unbindTexturedModel();
		}
	}
	
	private void prepareTexturedModel(TexturedModel model)
	{
		RawModel rmodel = model.getRawModel();
		GL30.glBindVertexArray(rmodel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		ModelTexture texture = model.getTexture();
		shader.loadNumberOfTextureAtlasRows(texture.getNumberOfRows());
		if(texture.hasTransparency())
			MasterRenderer.disableCulling();
		shader.loadUsesFakeLighting(texture.usesFakeLighting());
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
	}
	
	private void unbindTexturedModel()
	{
		MasterRenderer.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(Entity entity)
	{
		Matrix4f transformationMatrix = MathUtil.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		shader.loadTextureOffset(entity.getTextureXOffset(), entity.getTextureYOffset());
	}
}