package main.java.VolatiliaOGL.graphics.renderers;

import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.entity.Entity;
import main.java.VolatiliaOGL.graphics.models.ModelData;
import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.shaders.StaticShader;
import main.java.VolatiliaOGL.graphics.textures.Texture;
import main.java.VolatiliaOGL.util.MatrixMath;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class EntityRenderer
{
	private StaticShader shader;

	public EntityRenderer(StaticShader shader)
	{
		this.shader = shader;
	}

	public void renderEntities(Map<TexturedModel, List<Entity>> entities)
	{
		for(TexturedModel model : entities.keySet())
		{
			this.prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity ent : batch)
			{
				this.prepareInstance(ent);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModelData().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			this.unbindTexturedModel();
		}
	}

	private void prepareTexturedModel(TexturedModel model)
	{
		Texture texture = model.getTexture();
		ModelData modelData = model.getModelData();
		GL30.glBindVertexArray(modelData.getId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		if(texture.hasTransparency())
			RenderManager.disableCulling();
		shader.loadFakeLighting(texture.hasFakeLighting());
		shader.loadShineValues(modelData.getShineDampen(), modelData.getRefelction());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
	}

	private void unbindTexturedModel()
	{
		RenderManager.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity)
	{
		Matrix4f transformations = MatrixMath.createTransformationMatrix(new Vector3f(entity.getLocation()), entity.getPitch(), entity.getYaw(), entity.getRoll(), 1);
		shader.loadTransformationMatrix(transformations);
		entity.render();
	}
}
