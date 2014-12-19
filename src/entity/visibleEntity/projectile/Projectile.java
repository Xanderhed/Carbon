package entity.visibleEntity.projectile;

import entity.visibleEntity.VisibleEntity;
import gfx.Screen;

public class Projectile extends VisibleEntity{
	
	protected final int xOrigin, yOrigin;
	protected double x, y;
	protected double angle;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	
	public Projectile(int x, int y, double dir) {
		this.x = x;
		this.y = y;
		xOrigin = x;
		yOrigin = y;
		angle = dir;
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}
	
	protected double distance() {
		double xDist = x - xOrigin;
		double yDist = y - yOrigin;
		double distance = Math.sqrt(xDist * xDist + yDist * yDist);
		return distance;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) x, (int)  y, this.sprite, false);
	}
}
