package main.java.VolatiliaOGL.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.java.VolatiliaOGL.graphics.models.ModelData;
import main.java.VolatiliaOGL.graphics.models.ModelLoader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.svg.Loader;

public class OBJFileLoader
{

	public static ModelData loadOBJFile(Class<?> clazz, String file, Loader loader)
	{
		InputStreamReader reader = null;
		try
		{
			reader = new InputStreamReader(clazz.getResourceAsStream(file));
		} catch(Exception e)
		{
			System.err.println("Unable to find to find the given file to load!");
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(reader);

		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] verticesArray = null;
		float[] normalsArray = null;
		float[] texturesArray = null;
		int[] indicesArray = null;

		String line;
		try
		{
			while(true)
			{
				line = br.readLine();
				String[] currentLine = line.split(" ");

				if(line.startsWith("v "))
				{
					Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					vertices.add(vertex);
				}
				else if(line.startsWith("vt "))
				{
					Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
					textures.add(texture);
				}
				else if(line.startsWith("vn "))
				{
					Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					normals.add(normal);
				}
				else if(line.startsWith("f "))
				{
					texturesArray = new float[vertices.size() * 2];
					normalsArray = new float[vertices.size() * 3];
					break;
				}
			}

			while(line != null)
			{
				if(line.startsWith("f "))
				{
					line = br.readLine();
					continue;
				}

				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");

				processVertex(vertex1, indices, textures, normals, texturesArray, normalsArray);
				processVertex(vertex2, indices, textures, normals, texturesArray, normalsArray);
				processVertex(vertex3, indices, textures, normals, texturesArray, normalsArray);
				line = br.readLine();
			}
			br.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}

		verticesArray = new float[vertices.size() * 3];
		indicesArray = new int[indices.size()];
		
		int vertex = 0;
		for(Vector3f v: vertices)
		{
			verticesArray[vertex++] = v.x;
			verticesArray[vertex++] = v.y;
			verticesArray[vertex++] = v.z;
		}
		
		for(int i = 0; i < indices.size(); i++)
			indicesArray[i] = indices.get(i);
		
		return ModelLoader.INSTANCE.loadToModelData(verticesArray, texturesArray, indicesArray);
	}

	private static void processVertex(String[] vertextData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray)
	{
		int currentVertext = Integer.parseInt(vertextData[0]) - 1;
		indices.add(currentVertext);
		Vector2f currentTexture = textures.get(Integer.parseInt(vertextData[1]) - 1);
		textureArray[currentVertext * 2] = currentTexture.x;
		textureArray[currentVertext * 2 + 1] = 1 - currentTexture.y;
		Vector3f currentNormal = normals.get(Integer.parseInt(vertextData[2]) - 1);
		normalsArray[currentVertext * 3] = currentNormal.x;
		normalsArray[currentVertext * 3 + 1] = currentNormal.y;
		normalsArray[currentVertext * 3 + 2] = currentNormal.z;
	}
}
