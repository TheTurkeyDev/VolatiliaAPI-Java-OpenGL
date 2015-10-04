package main.java.VolatiliaOGL.util;

import main.java.VolatiliaOGL.player.DisplayView;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class MatrixMath
{

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale)
	{
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(1, 1, 1), matrix, matrix);
		return matrix;
	}

	public static Matrix4f createViewMatrix(DisplayView view)
	{
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(view.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(view.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(view.getRoll()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
		Vector3f negativePos = new Vector3f(-view.getX(), -view.getY(), -view.getZ());
		Matrix4f.translate(negativePos, viewMatrix, viewMatrix);
		return viewMatrix;
	}
}
