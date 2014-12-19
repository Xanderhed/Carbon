package gfx;

import game.Game;

public class AnimatedSprite extends Sprite{
	
	private int length;
	private int time = 0;
	private int frame = 0;
	private int rate = 10;

	public AnimatedSprite(int width, int height, int length, SpriteSheet sheet) {
		super(width, height, sheet);
		this.length = length - 1;
		if (length > sheet.getSprites().length) System.err.println("Error! Animation length is longer than sprite array.");
	}
	
	public AnimatedSprite(int width, int height, int length, int rate, SpriteSheet sheet) {
		this(width, height, length, sheet);
		setFrameRate(rate);
	}
	
	public AnimatedSprite(int SIZE, int length, SpriteSheet sheet) {
		this(SIZE, SIZE, length, sheet);
	}
	
	public void update(boolean moving) {
		if(moving) {
			time++;
			if(time >= rate) {
				time = 0;
				if (frame < length) frame++;
				else frame = 1;
			}
		}
		else {
			frame = 0;
			time = 0;
		}
		pixels = sheet.getSprites()[frame].pixels;
	}
	
	public void setFrameRate(int fps) {
		rate = (int)Game.FPS/fps;
	}
}
