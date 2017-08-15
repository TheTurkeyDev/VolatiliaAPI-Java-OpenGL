package main.java.VolatiliaOGL.postProcessing;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import main.java.VolatiliaOGL.models.RawModel;
import main.java.VolatiliaOGL.shaders.postProcessing.BaseEffect;
import main.java.VolatiliaOGL.util.Loader;

public class PostProcessing
{

	private static final BaseEffect baseShader = new BaseEffect();
	private static final float[] POSITIONS = { -1, 1, -1, -1, 1, 1, 1, -1 };
	private static RawModel quad = Loader.INSTANCE.loadToVAO(POSITIONS, 2);

	public static ContrastChanger contrastChanger = new ContrastChanger(Display.getWidth(), Display.getHeight());
	public static ColorShift colorshift = new ColorShift(Display.getWidth(), Display.getHeight());
	public static GrayScale grayScale = new GrayScale(Display.getWidth(), Display.getHeight());
	public static HorizontalBlur hblur = new HorizontalBlur(Display.getWidth(), Display.getHeight());
	public static VerticalBlur vblur = new VerticalBlur(Display.getWidth(), Display.getHeight());
	public static WaterEffect waterEffect = new WaterEffect(Display.getWidth(), Display.getHeight());

	public static boolean doGausBlur = false;
	public static boolean dowaterEffectt = false;
	public static boolean doContrastChange = false;
	public static boolean doColorshift = false;
	public static boolean dograyScale = false;

	public static void doPostProcessing(int texture)
	{
		start();
		int currentTexture = texture;

		if(doGausBlur)
		{
			hblur.render(currentTexture);
			vblur.render(hblur.getOutputTexture());
			currentTexture = vblur.getOutputTexture();
		}

		if(dowaterEffectt)
		{
			waterEffect.render(currentTexture);
			currentTexture = waterEffect.getOutputTexture();
		}
		if(doContrastChange)
		{
			contrastChanger.render(currentTexture);
			currentTexture = contrastChanger.getOutputTexture();
		}
		if(doColorshift)
		{
			colorshift.render(currentTexture);
			currentTexture = colorshift.getOutputTexture();
		}
		if(dograyScale)
		{
			grayScale.render(currentTexture);
			currentTexture = grayScale.getOutputTexture();
		}

		baseShader.render(currentTexture);

		end();
	}

	public static void cleanUp()
	{
		contrastChanger.cleanUp();
		colorshift.cleanUp();
		grayScale.cleanUp();
		hblur.cleanUp();
		vblur.cleanUp();
		waterEffect.cleanUp();
	}

	private static void start()
	{
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	private static void end()
	{
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}