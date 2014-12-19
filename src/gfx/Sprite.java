package gfx;

public class Sprite {

	public final int WIDTH, HEIGHT;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite blank = new Sprite(16, 16, 0);
	
	// Level 1 sprites here
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite tree = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite brick = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite lillypad = new Sprite(16, 7, 0, SpriteSheet.tiles);
	
	// Projectile sprites
	public static Sprite fireball = new Sprite(16, 0, 0, SpriteSheet.fireball);
	
	// Particles
	public static Sprite particle = new Sprite(2, 2, 0xffffff88);
	
	public Sprite(int width, int height, SpriteSheet sheet) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.sheet = sheet;
	}
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this(size, size, x, y, sheet);
	}
	
	public Sprite(int width, int height, int color) {
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	private void setColor(int color) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				pixels[x + y * WIDTH] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
	
	public void update() {
		return;
	}
}
