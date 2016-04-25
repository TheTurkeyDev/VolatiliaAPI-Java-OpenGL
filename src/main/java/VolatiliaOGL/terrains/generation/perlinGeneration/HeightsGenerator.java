package main.java.VolatiliaOGL.terrains.generation.perlinGeneration;

import java.util.Random;

public class HeightsGenerator
{
	private static final float AMPLITUDE = 50f;
	private static final int OCTAVES = 5;
	private static final float ROUGHNESS = 0.3f;

	private Random random = new Random();
	private int seed;

	public HeightsGenerator()
	{
		this.seed = random.nextInt(1000000000);
	}

	public float generateHeight(int x, int z)
	{
		float total = 0;
		float d = (float) Math.pow(2, OCTAVES - 1);
		for(int i = 0; i < OCTAVES; i++)
		{
			float freq = (float) (Math.pow(2, i) / d);
			float amp = (float) Math.pow(ROUGHNESS, i) * AMPLITUDE;
			total += getInterpolatedNoise(x * freq, z * freq) * amp;
		}
		return total;
	}

	private float getInterpolatedNoise(float x, float z)
	{
		int intX = (int) x;
		int intZ = (int) z;
		float fracX = x - intX;
		float fracZ = z - intZ;

		float v1 = this.getSmoothNoise(intX, intZ);
		float v2 = this.getSmoothNoise(intX + 1, intZ);
		float v3 = this.getSmoothNoise(intX, intZ + 1);
		float v4 = this.getSmoothNoise(intX + 1, intZ + 1);
		float i1 = this.interpolate(v1, v2, fracX);
		float i2 = this.interpolate(v3, v4, fracX);
		return this.interpolate(i1, i2, fracZ);
	}

	private float interpolate(float a, float b, float blend)
	{
		double theta = blend * Math.PI;
		float f = (float) (1f - Math.cos(theta)) * 0.5f;
		return a * (1f - f) + b * f;
	}

	private float getSmoothNoise(int x, int z)
	{
		float corners = (this.getNoise(x - 1, z - 1) + this.getNoise(x + 1, z - 1) + this.getNoise(x + 1, z - 1) + this.getNoise(x + 1, z + 1)) / 16;
		float sides = (this.getNoise(x - 1, z) + this.getNoise(x + 1, z) + this.getNoise(x, z - 1) + this.getNoise(x, z + 1)) / 8;
		float center = this.getNoise(x, z) / 4;
		return corners + sides + center;
	}

	private float getNoise(int x, int z)
	{
		random.setSeed(x * 49632 + z * 325176 + seed);
		return random.nextFloat() * 2f - 1f;
	}
}
