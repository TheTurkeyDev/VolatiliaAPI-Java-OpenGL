package main.java.VolatiliaOGL.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Entity;
import main.java.VolatiliaOGL.entities.Light;
import main.java.VolatiliaOGL.postProcessing.Fbo;
import main.java.VolatiliaOGL.postProcessing.PostProcessing;
import main.java.VolatiliaOGL.renderEngine.MasterRenderer;
import main.java.VolatiliaOGL.renderEngine.WaterRenderer;
import main.java.VolatiliaOGL.shaders.water.WaterShader;
import main.java.VolatiliaOGL.terrains.Terrain;
import main.java.VolatiliaOGL.terrains.WaterFrameBuffers;
import main.java.VolatiliaOGL.terrains.WaterTile;
import test.main.game.Game;

public class World
{
	public static float fogDensity = 0.0035f;
	public static float fogGradient = 5;

	private int worldID;

	private Light sun = null;
	private List<Light> lights = new ArrayList<Light>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	private List<WaterTile> waters = new ArrayList<WaterTile>();
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> normalEntities = new ArrayList<Entity>();

	WaterFrameBuffers fbos = new WaterFrameBuffers();
	WaterShader waterShader = new WaterShader();
	WaterRenderer waterRenderer = new WaterRenderer(waterShader, fbos);

	Fbo postProcess = new Fbo(Display.getWidth(), Display.getHeight(), Fbo.DEPTH_RENDER_BUFFER);

	public World(int id)
	{
		this.worldID = id;
		waterShader.loadProjectionMatrix(MasterRenderer.INSTANCE.getProjectionMatrix());
	}

	public int getWorldD()
	{
		return this.worldID;
	}

	public void renderWorld(Camera camera)
	{
		GL11.glEnable(GL30.GL_CLIP_DISTANCE0);

		fbos.bindReflectionFrameBuffer();
		float distance = 2 * (camera.getPosition().y - waters.get(0).getHeight());
		camera.getPosition().y -= distance;
		camera.invertPitch();
		MasterRenderer.INSTANCE.renderScene(entities, normalEntities, terrains, this.getNearestNLights(camera.getPosition(), 4, true), camera, new Vector4f(0, 1, 0, -waters.get(0).getHeight() + 1f));
		camera.getPosition().y += distance;
		camera.invertPitch();

		fbos.bindRefractionFrameBuffer();
		MasterRenderer.INSTANCE.renderScene(entities, normalEntities, terrains, this.getNearestNLights(camera.getPosition(), 4, true), camera, new Vector4f(0, -1, 0, waters.get(0).getHeight()));

		GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
		fbos.unbindCurrentFrameBuffer();

		postProcess.bindFrameBuffer();

		MasterRenderer.INSTANCE.renderScene(entities, normalEntities, terrains, this.getNearestNLights(camera.getPosition(), 4, true), camera, new Vector4f(0, -1, 0, 100000));
		waterRenderer.render(waters, camera, sun);
		postProcess.unbindFrameBuffer();

		boolean under = false;
		for(WaterTile water : waters)
		{
			if(water.getHeight() > camera.getPosition().y)
			{
				// Note: if either dimension is zero, tests below must return false...
				float x = water.getX() - 60;
				float z = water.getZ() - 60;
				float cx = camera.getPosition().x;
				float cz = camera.getPosition().z;
				if(cx < x || cz < z || under)
					continue;

				// overflow || intersect
				under = ((x + 120 < x || x + 120 > cx) && (z + 120 < z || z + 120 > cz));
			}
		}
		PostProcessing.changeColor(under || Game.player.useChangeColor);
		PostProcessing.useWaterEffect(under || Game.player.usewaterEffect);
		PostProcessing.doPostProcessing(postProcess.getColourTexture());
	}

	public void addNormalEntityToWorld(Entity ent)
	{
		this.normalEntities.add(ent);
	}

	public void removeNormalEntityFromWorld(Entity ent)
	{
		this.normalEntities.remove(ent);
	}

	public void addEntityToWorld(Entity ent)
	{
		this.entities.add(ent);
	}

	public void removeEntityFromWorld(Entity ent)
	{
		this.entities.remove(ent);
	}

	public void addLightSource(Light light)
	{
		lights.add(light);
	}

	public void removeLightSource(Light light)
	{
		lights.remove(light);
	}

	public List<Light> getNearestNLights(Vector3f position, int numberOfLights, boolean sun)
	{
		return this.getNearestNLights(position.x, position.y, position.z, numberOfLights, sun);
	}

	public List<Light> getNearestNLights(float x, float y, float z, int numberOfLights, boolean sun)
	{
		Vector3f source = new Vector3f(x, y, z);
		List<Light> lightsToReturn = new ArrayList<Light>();
		Light furthest = null;
		for(Light l : lights)
		{
			if(lightsToReturn.size() < numberOfLights)
			{
				lightsToReturn.add(l);

				if(furthest == null)
					furthest = l;
				else if(this.distanceBetween(furthest.getPosition(), source) < this.distanceBetween(l.getPosition(), source))
					furthest = l;
			}
			else
			{
				if(this.distanceBetween(furthest.getPosition(), source) > this.distanceBetween(l.getPosition(), source))
				{
					lightsToReturn.remove(furthest);
					lightsToReturn.add(l);
					furthest = l;
					for(Light light : lightsToReturn)
						if(this.distanceBetween(furthest.getPosition(), source) < this.distanceBetween(light.getPosition(), source))
							furthest = light;
				}
			}
		}

		if(sun)
		{
			lightsToReturn.remove(furthest);
			lightsToReturn.add(this.sun);
		}

		return lightsToReturn;
	}

	private float distanceBetween(Vector3f p1, Vector3f p2)
	{
		return Vector3f.sub(p1, p2, null).length();
	}

	public Light getSun()
	{
		return this.sun;
	}

	public void setSun(Light sun)
	{
		this.sun = sun;
	}

	public boolean addTerrain(Terrain t)
	{
		for(Terrain terrain : terrains)
			if(terrain.getGridX() == t.getGridX() && terrain.getGridZ() == t.getGridZ())
				return false;
		terrains.add(t);
		return true;
	}

	public void removeTerrain(Terrain t)
	{
		terrains.remove(t);
	}

	public List<Terrain> getTerrains()
	{
		return this.terrains;
	}

	public Terrain getTerrainAt(float x, float z)
	{
		int gridX = (int) (x / Terrain.SIZE);
		int gridZ = (int) (z / Terrain.SIZE);

		if(x < 0)
			gridX--;
		if(z < 0)
			gridZ--;

		for(Terrain terrain : terrains)
			if(terrain.getGridX() == gridX && terrain.getGridZ() == gridZ)
				return terrain;
		return null;
	}

	public void addWater(WaterTile water)
	{
		this.waters.add(water);
	}

	public void removeWater(WaterTile water)
	{
		this.waters.remove(water);
	}

	public void cleanUp()
	{
		waterShader.cleanUp();
		fbos.cleanUp();
		postProcess.cleanUp();
		PostProcessing.cleanUp();
	}
}