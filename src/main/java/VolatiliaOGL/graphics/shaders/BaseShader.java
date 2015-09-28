package main.java.VolatiliaOGL.graphics.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class BaseShader
{
	private int shaderID;
	private int vertexShaderID;
	private int fragmentShaderID;

	public BaseShader(String vertexFile, String fragmentShader)
	{
		this.vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = loadShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
		shaderID = GL20.glCreateProgram();
		GL20.glAttachShader(this.shaderID, this.vertexShaderID);
		GL20.glAttachShader(this.shaderID, this.fragmentShaderID);
		GL20.glLinkProgram(this.shaderID);
		GL20.glValidateProgram(this.shaderID);
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
		if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Failed to compile a shader file!");
		}
		return shaderID;
	}
}
