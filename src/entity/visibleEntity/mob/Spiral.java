package entity.visibleEntity.mob;

import gfx.AnimatedSprite;
import gfx.Screen;
import gfx.SpriteSheet;

public class Spiral extends Mob{
	
	private int time = 0;
	private double xa = 0;
	private double ya = 0;
	
	public Spiral() {
		this.sprite = new AnimatedSprite(16, 16, 8, 30, SpriteSheet.spiral);
		this.moving = true;
	}
	
	public Spiral(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		time++;
		updateMovement();
		updateSprite();
	}
	
	private void updateMovement() {
		if (time % (random.nextInt(80) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if(random.nextInt(3) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		move(xa, ya);
	}
	
	private void updateSprite() {
		sprite.update(moving);
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, this.sprite, false);
	}
}
