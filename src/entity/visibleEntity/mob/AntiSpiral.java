package entity.visibleEntity.mob;

import java.util.List;

import entity.Entity;
import gfx.AnimatedSprite;
import gfx.Screen;
import gfx.SpriteSheet;

public class AntiSpiral extends Mob{

	//private int time = 0;
	private Entity target;
	private int targetRange = 100;
	private int chaseRange = 100;
	
	public AntiSpiral() {
		this.sprite = new AnimatedSprite(16, 16, 8, 30, SpriteSheet.antiSpiral);
		this.moving = true;
	}
	
	public AntiSpiral(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public boolean hasTarget() {
		if(target == null) {
			return false;
		}
		return true;
	}
	
	public void update() {
		//time++;
		updateTarget();
		updateMovement();
		updateSprite();
	}
	
	private void updateTarget() {
		if(this.hasTarget()) {
			double dx = Math.abs(this.x - target.getX());
			double dy = Math.abs(this.y - target.getY());
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance > chaseRange) this.target = null;
		}
		else {
			List<Player> entities = level.getPlayersInRange(this, targetRange);
			if(!entities.isEmpty()) {
				this.target = (Entity)entities.get(0);
			}
		}
	}
	
	private void updateMovement() {
		if(this.hasTarget()) {
			int xa = 0;
			int ya = 0;
			if (this.x < target.getX()) xa++;
			if (this.x > target.getX()) xa--;
			if (this.y < target.getY()) ya++;
			if (this.y > target.getY()) ya--;
			move(xa, ya);
		}
	}
	
	private void updateSprite() {
		sprite.update(moving);
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, this.sprite, false);
	}
}
