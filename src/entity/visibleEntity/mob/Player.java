package entity.visibleEntity.mob;

import game.Game;
import gfx.AnimatedSprite;
import gfx.Screen;
import gfx.SpriteSheet;
import input.Keyboard;
import input.Mouse;

public class Player extends Mob{

	private Keyboard input;
	
	public double atkspd = 6.0;
	
	private double atkTimer = Game.FPS / atkspd;
	private double mvspd = 1.5;
	
	private AnimatedSprite up = new AnimatedSprite(16, 9, SpriteSheet.player_up);
	private AnimatedSprite down = new AnimatedSprite(16, 9, SpriteSheet.player_down);
	private AnimatedSprite left = new AnimatedSprite(16, 9, SpriteSheet.player_left);
	private AnimatedSprite right = new AnimatedSprite(16, 9, SpriteSheet.player_right);
	
	public Player(Keyboard input) {
		this.input = input;
		this.sprite = right;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		this.sprite = right;
	}
	
	public void update() {
		updateMovement();
		updateShooting();
		updateSprite();	
	}
	
	private void updateMovement() {
		double xa = 0, ya = 0;
		if(input.up) ya -= mvspd;
		if(input.down) ya += mvspd;
		if(input.left) xa -= mvspd;
		if(input.right) xa += mvspd;
		if(xa != 0 || ya != 0) {
			moving = true;
		} else {
			moving = false;
		}
		move(xa, ya);
	}
	
	private void updateShooting() {
		atkTimer= atkTimer - 1.0;
		if (Mouse.getButton() == 1 && atkTimer <= 0.0) {
			double dx = Mouse.getX() - (Game.WIDTH * Game.SCALE / 2);
			double dy = Mouse.getY() - (Game.HEIGHT * Game.SCALE / 2);
			double direction = Math.atan2(dy, dx);
			atkTimer = Game.FPS / atkspd;
			shoot(x, y, direction);
		}
	}
	
	private void updateSprite() {
		switch (direction) {
			case NORTH:
				sprite = up;
				break;
			case SOUTH:
				sprite = down;
				break;
			case EAST:
				sprite = right;
				break;
			case WEST:
				sprite = left;
				break;
			default:
				break;
		}
		sprite.update(moving);
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, this.sprite, false);
	}
}
