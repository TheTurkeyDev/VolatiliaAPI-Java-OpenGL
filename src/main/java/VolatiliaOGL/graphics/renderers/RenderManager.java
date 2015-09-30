package main.java.VolatiliaOGL.graphics.renderers;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

public class RenderManager
{
	private static float fov = 70;
	private static float nearPlane = 0.1f;
	private static float farPlane = 1000;

	private static Matrix4f projectionMatrix;

	public static void updateProjectionMatrix()
	{
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) ((1f / Math.tan(Math.toRadians(fov / 2))) * aspectRatio);
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
}
