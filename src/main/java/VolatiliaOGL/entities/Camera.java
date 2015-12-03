package main.java.VolatiliaOGL.entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;

	private Vector3f position = new Vector3f(100, 25, 50);
	private float pitch = 15;
	private float yaw;
	private float roll;

	private Player player;

	public Camera(Player player)
	{
		this.player = player;
	}

	public void move()
	{
		this.calculateZoom();
		this.calculatePitch();
		this.calculateAngleAroundPlayer();
		float horizontalDistance = this.calculateHorizontalDistance();
		float verticleDistance = this.calculateVerticleDistance();
		this.calculateCameraPosition(horizontalDistance, verticleDistance);
		this.yaw = 180 - (player.getRotY() + this.angleAroundPlayer);
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public float getPitch()
	{
		return pitch;
	}

	public float getYaw()
	{
		return yaw;
	}

	public float getRoll()
	{
		return roll;
	}

	private float calculateHorizontalDistance()
	{
		return (float) (this.distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	private float calculateVerticleDistance()
	{
		return (float) (this.distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	private void calculateCameraPosition(float hd, float vd)
	{
		float theta = player.getRotY() + this.angleAroundPlayer;
		float offsetX = (float) (hd * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (hd * Math.cos(Math.toRadians(theta)));
		this.position.x = player.getPosition().x - offsetX;
		this.position.z = player.getPosition().z - offsetZ;
		this.position.y = this.player.getPosition().y + vd;
	}

	private void calculateZoom()
	{
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		this.distanceFromPlayer -= zoomLevel;
	}

	private void calculatePitch()
	{
		if(Mouse.isButtonDown(0))
		{
			float pitchChange = Mouse.getDY() * 0.1f;
			this.pitch -= pitchChange;
		}
	}

	private void calculateAngleAroundPlayer()
	{
		if(Mouse.isButtonDown(0))
		{
			float angleChange = Mouse.getDX() * 0.3f;
			this.angleAroundPlayer -= angleChange;
		}
	}

	public void invertPitch()
	{
		this.pitch = -this.pitch;
	}
}
