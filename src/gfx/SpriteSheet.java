package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int WIDTH, HEIGHT;

	public int[] pixels;
	private Sprite[] sprites;
	
	//Sprite Sheets
	public static SpriteSheet tiles = new SpriteSheet("/spriteSheet.png", 512, 512);
	public static SpriteSheet fireball = new SpriteSheet("/blueFireball.png", 64);
	//Sub-Sprite Sheets
	public static SpriteSheet player_up = new SpriteSheet("/player.png", 1, 0, 1, 9, 16, 16);
	public static SpriteSheet player_down = new SpriteSheet("/player.png", 3, 0, 1, 9, 16, 16);
	public static SpriteSheet player_left = new SpriteSheet("/player.png", 2, 0, 1, 9, 16, 16);
	public static SpriteSheet player_right = new SpriteSheet("/player.png", 0, 0, 1, 9, 16, 16);
	public static SpriteSheet spiral = new SpriteSheet("/spiral.png", 0, 0, 1, 8, 16, 16);
	public static SpriteSheet antiSpiral = new SpriteSheet("/anti-spiral.png", 0, 0, 1, 8, 16, 16);
	
	public SpriteSheet(String path, int size) {
		this(path, size, size);
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[width * height];
		load(0, 0);
	}

	//x,y = position of top left sprite   width,height = size of sub_sprite sheet
	public SpriteSheet (String path, int x, int y, int width, int height, int spriteWidth, int spriteHeight) {
		//pixel precision = sprite precision * spritesize;
		int xp = x * spriteWidth;
		int yp = y * spriteHeight;
		this.WIDTH = width * spriteWidth;
		this.HEIGHT = height * spriteHeight;
		this.path = path;
		
		pixels = new int[WIDTH * HEIGHT];
		sprites = new Sprite[width * height];
		
		load(xp, yp);
		
		int frame = 0;
		for (int ya = 0; ya < height; ya ++) {
			for (int xa = 0; xa < width; xa ++) {
				Sprite sprite = new Sprite (spriteWidth, spriteHeight, xa, ya, this);
				sprites[frame] = sprite;
				frame++;
			}
		}
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	private void load(int x, int y) {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			for (int xa = 0; xa < WIDTH; xa++) {
				for (int ya = 0; ya < HEIGHT; ya ++)	 {
					pixels[xa + ya * WIDTH] = image.getRGB(x + xa, y + ya);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
