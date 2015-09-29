package main.java.VolatiliaOGL.graphics.shaders;

public class StaticShader extends BaseShader
{
	public static final StaticShader INSTANCE = new StaticShader();
	private static final String VERTEX_FILE = "src/main/java/VolitiliaOGL/graphics/shaders/vertextShader.txt";
	private static final String FRAGMENT_FILE = "src/main/java/VolitiliaOGL/graphics/shaders/fragmentShader.txt";

	public StaticShader()
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes()
	{
		super.bindAttribute(0, "position");
	}

}
