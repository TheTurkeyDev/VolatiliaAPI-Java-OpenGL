package main.java.VolatiliaOGL.renderEngine;

import java.util.List;

import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.shaders.terrain.TerrainShader;
import main.java.VolatiliaOGL.terrains.Terrain;
import main.java.VolatiliaOGL.textures.TerrainTexturePack;
import main.java.VolatiliaOGL.util.MathUtil;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class TerrainRenderer
{
	private TerrainShader shader;

	public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix)
	{
		this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.connectTextureUnits();
		shader.stop();
	}

	public void render(List<Terrain> terrains)
	{
		for(Terrain terrain : terrains)
		{
			this.prepareTerrain(terrain);
			this.loadModelMatrix(terrain);
			GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			this.unbindTexturedModel();
		}
	}

	private void prepareTerrain(Terrain terrain)
	{
		RawModel rmodel = terrain.getModel();
		GL30.glBindVertexArray(rmodel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		this.bindTextures(terrain);
		shader.loadShineVariables(1, 0);
	}
	
	private void bindTextures(Terrain terrain)
	{
		TerrainTexturePack pack = terrain.getTexturePack();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getBackgroundTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getrTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getgTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getbTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrain.getBlendMap().getTextureID());
	}

	private void unbindTexturedModel()
	{
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void loadModelMatrix(Terrain terrain)
	{
		Matrix4f transformationMatrix = MathUtil.createTransformationMatrix(new Vector3f(terrain.getX(), 0, terrain.getZ()), 0, 0, 0, 1);
		shader.loadTransformationMatrix(transformationMatrix);
	}
}
