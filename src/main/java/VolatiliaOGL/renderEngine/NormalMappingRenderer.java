package main.java.VolatiliaOGL.renderEngine;

import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Entity;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.game.World;
import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.models.TexturedModel;
import main.java.VolatiliaOGL.shaders.normalMap.NormalMappingShader;
import main.java.VolatiliaOGL.textures.ModelTexture;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

public class NormalMappingRenderer
{

	private NormalMappingShader shader;

	public NormalMappingRenderer()
	{
		this.shader = new NormalMappingShader();
		shader.start();
		shader.connectTextureUnits();
		shader.loadFogData(World.fogDensity, World.fogGradient);
		shader.stop();
	}

	public void render(Map<TexturedModel, List<Entity>> entities, Vector4f clipPlane, List<Light> lights, Camera camera)
	{
		shader.start();
		prepare(clipPlane, lights, camera);
		for(TexturedModel model : entities.keySet())
		{
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity entity : batch)
			{
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
		shader.stop();
	}

	public void cleanUp()
	{
		shader.cleanUp();
	}

	private void prepareTexturedModel(TexturedModel model)
	{
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL20.glEnableVertexAttribArray(3);
		ModelTexture texture = model.getTexture();
		shader.loadNumberOfRows(texture.getNumberOfRows());
		if(texture.hasTransparency())
		{
			MasterRenderer.disableCulling();
		}
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getNormalMapID());
	}

	private void unbindTexturedModel()
	{
		MasterRenderer.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(3);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity)
	{
		Matrix4f transformationMatrix = MathUtil.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		shader.loadOffset(entity.getTextureXOffset(), entity.getTextureYOffset());
	}

	private void prepare(Vector4f clipPlane, List<Light> lights, Camera camera)
	{
		shader.loadClipPlane(clipPlane);
		// need to be public variables in MasterRenderer
		shader.loadSkyColour(MasterRenderer.SKYRED, MasterRenderer.SKYGREEN, MasterRenderer.SKYBLUE);
		Matrix4f viewMatrix = MathUtil.createViewMatrix(camera);

		shader.loadLights(lights, viewMatrix);
		shader.loadViewMatrix(viewMatrix);
	}
	
	public void updateProjectionMatrix(Matrix4f projectionMatrix)
	{
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
}
