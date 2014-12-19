package gfx;

import java.util.Random;

import level.tile.Tile;
import entity.visibleEntity.VisibleEntity;


public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();
	
	public Screen (int width, int height) {
		
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.HEIGHT; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.WIDTH; x++) {
				int xa = x + xp;
				if(xa < -tile.sprite.WIDTH || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.WIDTH];
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if(!fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for(int y = 0; y < sprite.HEIGHT; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.HEIGHT; x++) {
				int xa = x + xp;
				if(xa < -sprite.WIDTH || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.WIDTH];
				if(col != 0xffff00ff && col != 0xff7f007f) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if(!fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for(int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for(int x = 0; x < sheet.HEIGHT; x++) {
				int xa = x + xp;
				if(xa < -sheet.WIDTH || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sheet.pixels[x + y * sheet.WIDTH];
				pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderEntity(int xp, int yp, VisibleEntity entity) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < entity.sprite.HEIGHT; y++) {
			int ya = y + yp;
			for(int x = 0; x < entity.sprite.WIDTH; x++) {
				int xa = x + xp;
				if(xa < -entity.sprite.WIDTH || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = entity.sprite.pixels[x + y * entity.sprite.HEIGHT];
				if(col != 0xffff00ff && col != 0xff7f007f) {
					pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
