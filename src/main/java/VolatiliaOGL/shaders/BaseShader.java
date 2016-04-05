package main.java.VolatiliaOGL.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public abstract class BaseShader
{
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public BaseShader(String vertexFile, String fragmentFile)
	{
		this.vertexShaderID = BaseShader.loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = BaseShader.loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		this.programID = GL20.glCreateProgram();
		GL20.glAttachShader(this.programID, this.vertexShaderID);
		GL20.glAttachShader(this.programID, this.fragmentShaderID);
		this.bindAttributes();
		GL20.glLinkProgram(this.programID);
		GL20.glValidateProgram(this.programID);
		getAllUniformLocations();
	}

	protected int getUniformLocation(String uniformName)
	{
		return GL20.glGetUniformLocation(this.programID, uniformName);
	}

	protected abstract void getAllUniformLocations();

	public void start()
	{
		GL20.glUseProgram(this.programID);
	}

	public void stop()
	{
		GL20.glUseProgram(0);
	}

	public void cleanUp()
	{
		this.stop();
		GL20.glDetachShader(this.programID, this.vertexShaderID);
		GL20.glDetachShader(this.programID, this.fragmentShaderID);
		GL20.glDeleteShader(this.vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		GL20.glDeleteProgram(this.programID);
	}

	protected abstract void bindAttributes();

	protected void bindAttribute(int attribute, String name)
	{
		GL20.glBindAttribLocation(this.programID, attribute, name);
	}

	protected void loadFloat(int location, float value)
	{
		GL20.glUniform1f(location, value);
	}
	
	protected void loadInt(int location, int value)
	{
		GL20.glUniform1i(location, value);
	}

	protected void loadVector(int location, Vector4f value)
	{
		GL20.glUniform4f(location, value.x, value.y, value.z, value.w);
	}
	
	protected void loadVector(int location, Vector3f value)
	{
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	protected void loadVector(int location, Vector2f value)
	{
		GL20.glUniform2f(location, value.x, value.y);
	}

	protected void loadBoolean(int location, boolean value)
	{
		GL20.glUniform1f(location, value ? 1 : 0);
	}

	protected void loadMatrix(int location, Matrix4f value)
	{
		value.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}

	private static int loadShader(String file, int type)
	{
		StringBuilder builder = new StringBuilder();

		try
		{
			InputStream in = Class.class.getResourceAsStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null)
			{
				builder.append(line).append("\n");
			}

			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, builder);
		GL20.glCompileShader(shaderID);

		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.exit(-1);
		}
		return shaderID;
	}
}
