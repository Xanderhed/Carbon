package entity.visibleEntity.mob;

import entity.visibleEntity.VisibleEntity;
import entity.visibleEntity.projectile.Projectile;
import entity.visibleEntity.projectile.WizardProjectile;
import gfx.AnimatedSprite;


public abstract class Mob extends VisibleEntity {
	
	protected Direction direction = Direction.EAST;
	protected boolean moving = false;
	protected static enum Direction {
		NORTH, EAST, SOUTH, WEST
	}
	
	public AnimatedSprite sprite;
	
	public void move(double xa, double ya) {	
		if(xa > 0) direction = Direction.EAST;
		if(xa < 0) direction = Direction.WEST;
		if(ya > 0) direction = Direction.SOUTH;
		if(ya < 0) direction = Direction.NORTH;
		
		while (xa != 0) {
			if (Math.abs(xa) >= 1) {
				if (!collision(sign(xa), ya)) {
					this.x += sign(xa);
				}
				xa -= sign(xa);
			} else {
				if (!collision(sign(xa), ya)) {
					this.x += xa;
				}
			}
		}
		
		while (ya != 0) {
			if (Math.abs(ya) >= 1) {
				if (!collision(xa, sign(ya))) {
					this.y += sign(ya);
				}
				ya -= sign(ya);
			} else {
				if (!collision(xa, sign(ya))) {
					this.y += ya;
				}
			}
		}
	}
	
	private int sign(double value) {
		if (value < 0) return -1;
		return 1;
	}
	
	public abstract void update();

	protected void shoot (double x, double y, double dir) {
		Projectile p = new WizardProjectile((int) x, (int) y, dir);
		level.add(p);
	}
}