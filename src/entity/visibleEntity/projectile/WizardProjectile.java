package entity.visibleEntity.projectile;

import entity.visibleEntity.particle.Particle;
import gfx.Sprite;

public class WizardProjectile extends Projectile{
	
	
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 150;
		speed = 3;
		damage = 10;
		rateOfFire = 10;
		sprite = Sprite.fireball;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		if (level.tileCollision((int) (x +nx), (int) (y + ny), 6, 6, 6, 5)) {
			Particle p;
			for(int i = 0; i < 15; i++) {
				p = new Particle(x + 6, y + 8, 60);
				level.add(p);
			}
			remove();
		}
		move();
	}
}
