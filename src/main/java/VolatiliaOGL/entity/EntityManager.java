package main.java.VolatiliaOGL.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.VolatiliaOGL.graphics.models.TexturedModel;
import main.java.VolatiliaOGL.graphics.renderers.RenderManager;

public class EntityManager
{
	private static Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

	/**
	 * 
	 * @param ent
	 *            The entity to spawn into the map
	 */
	public void spawnEntity(Entity ent)
	{
		List<Entity> entType = entities.get(ent.getTexturedModel());
		if(entType != null)
		{
			entType.add(ent);
		}
		else
		{
			entType = new ArrayList<Entity>();
			entType.add(ent);
			entities.put(ent.getTexturedModel(), entType);
		}
	}

	public void renderEntities()
	{
		RenderManager.entityRenderer.renderEntities(entities);
	}

	public void updateEntities()
	{
		for(TexturedModel model : entities.keySet())
		{
			List<Entity> entType = entities.get(model);
			for(int i = entType.size() - 1; i >= 0; i--)
			{
				Entity ent = entType.get(i);
				if(!ent.isAlive())
					entType.remove(i);
				else
					ent.update();
			}
		}
	}
}
