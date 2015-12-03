package main.java.VolatiliaOGL.entities;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.models.TexturedModel;
import main.java.VolatiliaOGL.terrains.Terrain;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Entity
{

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;

	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;

	private boolean isInAir = false;

	public Player(TexturedModel model, Vector3f positon, float rotX, float rotY, float rotZ, float scale)
	{
		super(model, positon, rotX, rotY, rotZ, scale);
	}

	public void move(Terrain terrain)
	{
		checkInputs();
		super.increaseRotation(0, this.currentTurnSpeed * VolatiliaAPI.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * VolatiliaAPI.getFrameTimeSeconds();
		float xinc = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float zinc = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(xinc, 0, zinc);
		this.upwardsSpeed += GRAVITY * VolatiliaAPI.getFrameTimeSeconds();
		super.increasePosition(0, this.upwardsSpeed * VolatiliaAPI.getFrameTimeSeconds(), 0);
		float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
		if(super.getPosition().y < terrainHeight)
		{	
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = terrainHeight;
		}
	}

	private void jump()
	{
		if(!isInAir)
		{
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}

	private void checkInputs()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			this.currentSpeed = RUN_SPEED;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			this.currentSpeed = -RUN_SPEED;
		}
		else
		{
			this.currentSpeed = 0;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			this.currentTurnSpeed = -TURN_SPEED;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			this.currentTurnSpeed = TURN_SPEED;
		}
		else
		{
			this.currentTurnSpeed = 0;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			this.jump();
		}
	}

}
