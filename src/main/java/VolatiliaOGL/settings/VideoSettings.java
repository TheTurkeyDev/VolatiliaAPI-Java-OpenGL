package main.java.VolatiliaOGL.settings;

import main.java.VolatiliaOGL.renderEngine.MasterRenderer;

public class VideoSettings
{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static int fpsCap = 60;
	private static float FOV = 70;
	private static float NEAR_PLANE = 0.1f;
	private static float FAR_PLANE = 1000;

	public static void setFOV(float fov)
	{
		FOV = fov;
		MasterRenderer.INSTANCE.createProjectionMatrix();
	}
	public static float getFOV()
	{
		return FOV;
	}

	public static void setFarPlane(float farPlane)
	{
		FAR_PLANE = farPlane;
		MasterRenderer.INSTANCE.createProjectionMatrix();
	}
	public static float getFarPlane()
	{
		return FAR_PLANE;
	}

	public static void setNearPlane(float nearPlane)
	{
		NEAR_PLANE = nearPlane;
		MasterRenderer.INSTANCE.createProjectionMatrix();
	}
	public static float getNearPlane()
	{
		return NEAR_PLANE;
	}
}
