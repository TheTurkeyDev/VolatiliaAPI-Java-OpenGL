package main.java.VolatiliaOGL.particles;

import main.java.VolatiliaOGL.VolatiliaAPI;
import main.java.VolatiliaOGL.entities.Camera;
import main.java.VolatiliaOGL.entities.Player;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Particle
{
	private Vector3f position;
	private Vector3f velocity;
	private float gravityEffect;
	private float lifeLength;
	private float rotation;
	private float scale;
	
	private ParticleTexture texture;
	
	private Vector2f texOffset1 = new Vector2f();
	private Vector2f texOffset2 = new Vector2f();
	private float blend;

	private float elapsedTime = 0;
	private float distance;
	
	private boolean isAlive = true;
	
	Vector3f reusableVector = new Vector3f();

	public Particle(ParticleTexture texture, Vector3f position, Vector3f velocity, float gravityEffect, float lifeLength, float rotation, float scale)
	{
		this.texture = texture;
		this.position = position;
		this.velocity = velocity;
		this.gravityEffect = gravityEffect;
		this.lifeLength = lifeLength;
		this.rotation = rotation;
		this.scale = scale;
		ParticleMaster.addParticle(this);
	}

	public ParticleTexture getTexture()
	{
		return texture;
	}

	public Vector3f getPosition()
	{
		return position;
	}

	public float getRotation()
	{
		return rotation;
	}

	public float getScale()
	{
		return scale;
	}
	
	public void update(Camera camera)
	{
		this.velocity.y += Player.GRAVITY * this.gravityEffect * VolatiliaAPI.getFrameTimeSeconds();
		reusableVector.set(velocity);
		reusableVector.scale(VolatiliaAPI.getFrameTimeSeconds());
		Vector3f.add(reusableVector, position, position);
		distance = Vector3f.sub(camera.getPosition(), position, null).lengthSquared();
		updateTextureInfo();
		this.elapsedTime += VolatiliaAPI.getFrameTimeSeconds();
		this.isAlive = this.elapsedTime <= this.lifeLength;
	}

	public boolean isAlive()
	{
		return isAlive;
	}

	public Vector2f getTexOffset1()
	{
		return texOffset1;
	}

	public Vector2f getTexOffset2()
	{
		return texOffset2;
	}

	public float getBlend()
	{
		return blend;
	}
	
	private void updateTextureInfo()
	{
		float lifeFactor = this.elapsedTime / this.lifeLength;
		int stageCount = texture.getNumberOfRows() * texture.getNumberOfRows();
		float atlasProgression = lifeFactor * stageCount;
		int index1 = (int) Math.floor(atlasProgression);
		int index2 = index1 < stageCount - 1 ? index1 + 1: index1;
		this.blend = atlasProgression % 1;
		this.setTextureOffset(this.texOffset1, index1);
		this.setTextureOffset(this.texOffset2, index2);
	}
	
	private void setTextureOffset(Vector2f offset, int index)
	{
		int colum = index % texture.getNumberOfRows();
		int row = index / texture.getNumberOfRows();
		offset.x = (float)colum / texture.getNumberOfRows();
		offset.y = (float)row / texture.getNumberOfRows();
	}

	public float getDistance()
	{
		return distance;
	}
}