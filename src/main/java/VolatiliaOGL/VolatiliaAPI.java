package main.java.VolatiliaOGL;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import main.java.VolatiliaOGL.graphics.models.ModelLoader;
import main.java.VolatiliaOGL.graphics.renderers.RenderManager;
import main.java.VolatiliaOGL.graphics.shaders.StaticShader;
import main.java.VolatiliaOGL.screen.ScreenManager;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class VolatiliaAPI
{

	public static VolatiliaAPI instance;
	private int fps_cap = 120;

	private int width, height;
	private String name;

	public VolatiliaAPI(String name, int width, int height)
	{
		instance = this;
		this.name = name;
		this.height = height;
		this.width = width;
	}

	public void load()
	{
		createScreen();
		apiInit();
	}

	/**
	 * Starts the Game
	 */
	public void start()
	{
		startOpenGL();
		mainGameLoop();
	}

	/**
	 * Ends the Game
	 */
	public void endGame()
	{
		Display.destroy();
		StaticShader.INSTANCE.cleanUp();
		ModelLoader.INSTANCE.RemoveAllStoredModels();
	}

	/**
	 * Sets up the screen for the game
	 */
	private void createScreen()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(name);
			// Display.setResizable(true);
			ContextAttribs attributes = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
			Display.create(new PixelFormat(), attributes);
		} catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, this.width, this.height);
	}

	/**
	 * Starts all game objects that need to be pre initialized
	 */
	private void apiInit()
	{
		new ScreenManager();
		//new FontManager();
	}

	/**
	 * Initializes OpenGL
	 */
	private void startOpenGL()
	{
		RenderManager.updateProjectionMatrix();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	private void mainGameLoop()
	{
		while(!Display.isCloseRequested())
		{
			Display.sync(this.fps_cap);
			this.render();
			Display.update();
		}
		this.endGame();
	}

	/**
	 * Polls outside input (Keyboard, Mouse, ect..)
	 */
	public void pollInput()
	{
		try
		{
			ScreenManager.getInstance().getCurrentScreen().pollInput();
		} catch(NullPointerException e)
		{
			System.out.println("No Screen Set!");
		}
	}

	/**
	 * Updates the game
	 */
	public void update()
	{
		try
		{
			ScreenManager.getInstance().getCurrentScreen().update();
		} catch(NullPointerException e)
		{
			System.out.println("No Screen Set!");
		}
	}

	/**
	 * Renders the game screen
	 */
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		try
		{
			ScreenManager.getInstance().getCurrentScreen().render();
		} catch(NullPointerException e)
		{
			System.out.println("No Screen Set!");
		}
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
}