package main.java.VolatiliaOGL.util;

import main.java.VolatiliaOGL.entities.Camera;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class MousePicker
{
	private Vector3f currentRay;

	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix;
	private Camera camera;

	public MousePicker(Camera camera, Matrix4f projectionMatrix)
	{
		this.camera = camera;
		this.projectionMatrix = projectionMatrix;
		this.viewMatrix = MathUtil.createViewMatrix(camera);
	}

	public void update()
	{
		this.viewMatrix = MathUtil.createViewMatrix(camera);
		this.currentRay = this.calculateMouseRay();
	}

	public Vector3f getCurrentRay()
	{
		return this.currentRay;
	}

	public Vector3f calculateMouseRay()
	{
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		Vector2f normalizedCoords = this.getNormalizedDeviceCoords(mouseX, mouseY);
		Vector4f ClipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
		Vector4f eyeCoords = this.toEyeCoords(ClipCoords);
		Vector3f worldRay = this.toWorldCoords(eyeCoords);
		return worldRay;
	}

	private Vector2f getNormalizedDeviceCoords(float mouseX, float mouseY)
	{
		float x = (2f * mouseX) / Display.getWidth() - 1;
		float y = (2f * mouseY) / Display.getHeight() - 1;
		return new Vector2f(x, y);
	}

	private Vector4f toEyeCoords(Vector4f clip)
	{
		Matrix4f invertedProjection = Matrix4f.invert(projectionMatrix, null);
		Vector4f eyeCoords = Matrix4f.transform(invertedProjection, clip, null);
		return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
	}
	
	private Vector3f toWorldCoords(Vector4f eyeCoords)
	{
		Matrix4f invertedView = Matrix4f.invert(viewMatrix, null);
		Vector4f rayWorld = Matrix4f.transform(invertedView, eyeCoords, null);
		Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
		mouseRay.normalise();
		return mouseRay;
	}

}
