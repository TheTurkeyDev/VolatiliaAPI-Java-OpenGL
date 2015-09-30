package main.java.VolatiliaOGL.graphics.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class BaseShader
{
	private int shaderID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

	public BaseShader(String vertexFile, String fragmentShader)
	{
		this.vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = loadShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
		shaderID = GL20.glCreateProgram();
		GL20.glAttachShader(this.shaderID, this.vertexShaderID);
		GL20.glAttachShader(this.shaderID, this.fragmentShaderID);
		this.bindAttributes();
		GL20.glLinkProgram(this.shaderID);
		GL20.glValidateProgram(this.shaderID);
		getAllUniformLocations();
	}

	protected abstract void getAllUniformLocations();

	protected int getUniformLocation(String name)
	{
		return GL20.glGetUniformLocation(this.shaderID, name);
	}

	public void start()
	{
		GL20.glUseProgram(this.shaderID);
	}

	public void stop()
	{
		GL20.glUseProgram(0);
	}

	public void cleanUp()
	{
		stop();
		GL20.glDetachShader(this.shaderID, this.vertexShaderID);
		GL20.glDetachShader(this.shaderID, this.fragmentShaderID);
		GL20.glDeleteShader(this.vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		GL20.glDeleteProgram(this.shaderID);
	}

	protected abstract void bindAttributes();

	protected void bindAttribute(int attribute, String varriableName)
	{
		GL20.glBindAttribLocation(this.shaderID, attribute, varriableName);
	}

	protected void loadFloat(int location, float value)
	{
		GL20.glUniform1f(location, value);
	}

	protected void loadVector(int location, Vector3f vector)
	{
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}

	protected void loadBoolean(int location, boolean value)
	{
		float toLoad = value ? 0 : 1;
		GL20.glUniform1f(location, toLoad);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix)
	{
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}

	private static int loadShader(String file, int type)
	{
		StringBuilder shaderSource = new StringBuilder();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null)
			{
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} catch(IOException e)
		{
			System.err.println("Failed to load a shader file!");
			e.printStackTrace();
		}

		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Failed to compile a shader file!");
		}
		return shaderID;
	}
}
