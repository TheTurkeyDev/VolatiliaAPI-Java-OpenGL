package main.java.VolatiliaOGL.graphics.renderers;

import main.java.VolatiliaOGL.entity.LightEntity;
import main.java.VolatiliaOGL.graphics.shaders.StaticShader;
import main.java.VolatiliaOGL.player.DisplayView;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

public class RenderManager
{
	private static StaticShader shader = new StaticShader();
	
	private static float fov = 70;
	private static float nearPlane = 0.1f;
	private static float farPlane = 1000;

	private static Matrix4f projectionMatrix;
	
	private static LightEntity light;
	private static DisplayView view;
	
	public static EntityRenderer entityRenderer;
	
	public static void initRendering()
	{
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		
		entityRenderer = new EntityRenderer(shader);
	}

	public static void createProjectionMatrix()
	{
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
		float xScale = yScale / aspectRatio;
		float frustumLength = farPlane - nearPlane;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((farPlane + nearPlane) / frustumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * nearPlane * farPlane) / frustumLength);
		projectionMatrix.m33 = 0;
	}
	
	public static Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}
	
	public static void cleanUp()
	{
		shader.cleanUp();
	}
	
	public static void prepareRenderers()
	{
		shader.start();
		shader.loadLight(light);
		shader.loadViewMatrix(view);
	}
	
	public static void endRenderers()
	{
		shader.stop();
	}

	public static LightEntity getLight()
	{
		return light;
	}

	public static void setLight(LightEntity light)
	{
		RenderManager.light = light;
	}

	public static DisplayView getDisplayView()
	{
		return view;
	}

	public static void setDisplayView(DisplayView view)
	{
		RenderManager.view = view;
	}
}