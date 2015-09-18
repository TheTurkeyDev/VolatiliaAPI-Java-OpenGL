package main.java.VolatiliaOGL;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import main.java.VolatiliaOGL.graphics.FontManager;
import main.java.VolatiliaOGL.screen.ScreenManager;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class VolatiliaAPI
{

	public static VolatiliaAPI instance;
	private int gFrames = 0;
	private int gUpdates = 0;
	
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
	public void end()
	{
		cleanUp();
	}

	/**
	 * Sets up the screen for the game
	 */
	private void createScreen()
	{
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle(name);
			//Display.setResizable(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts all game objects that need to be pre initialized
	 */
	private void apiInit()
	{
		new ScreenManager();
		new FontManager();
	}

	/**
	 * Initializes OpenGL
	 */
	private void startOpenGL()
	{
		glDisable(GL_DEPTH_TEST);
		glClearColor(0,0,0,1);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		glClear(GL_COLOR_BUFFER_BIT);

		// Enable blending so the green background can be seen through the
		// texture
	}

	private void mainGameLoop()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while(!Display.isCloseRequested())
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1)
			{
				update();
				pollInput();
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis() - timer > 1000)
			{
				gFrames = frames;
				gUpdates = updates;
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
		end();
	}

	/**
	 * Polls outside input (Keyboard, Mouse, ect..)
	 */
	public void pollInput()
	{
		try{
			ScreenManager.getInstance().getCurrentScreen().pollInput();
		}catch(NullPointerException e){System.out.println("No Screen Set!");}
	}

	/**
	 * Updates the game
	 */
	public void update()
	{
		try{
			ScreenManager.getInstance().getCurrentScreen().update();
		}catch(NullPointerException e){System.out.println("No Screen Set!");}
	}

	/**
	 * Renders the game screen
	 */
	public void render()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		try{
			ScreenManager.getInstance().getCurrentScreen().render();
		}catch(NullPointerException e){System.out.println("No Screen Set!");}
		Display.update();
	}

	/**
	 * Called before the game fully closes
	 */
	private void cleanUp()
	{
		Display.destroy();
	}

	/**
	 * returns the current FPS of the game
	 * @return FPS
	 */
	public int getFPS()
	{
		return gFrames;
	}

	/**
	 * returns the current rate of updates per second
	 * @return UPS
	 */
	public int getUPS()
	{
		return gUpdates;
	}
}