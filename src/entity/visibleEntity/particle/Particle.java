package entity.visibleEntity.particle;

import entity.visibleEntity.VisibleEntity;
import gfx.Screen;
import gfx.Sprite;

public class Particle extends VisibleEntity{

	protected int life;
	
	protected double x, y, z;
	protected double xa, ya, za;
	
	public Particle(double x, double y, int life) {
		this.x = x;
		this.y = y;
		this.life = life + random.nextInt(30);
		sprite = Sprite.particle;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.z = random.nextFloat() + 1.8;
	}
	
	public void update() {
		life--;
		if(life < 1) remove();
		za -= 0.1;
		
		if(z <= 0) {
			z = 0;
			za *= -0.5;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move();
		}
	
	protected void move() {
		if(level.tileCollision((int) x, (int) y, sprite.WIDTH, sprite.HEIGHT, 0, 0)) {
			xa *= -0.5;
			ya *= -0.5;
			za *= -0.5;
		}
		this.x += xa;
		this.y += ya;
		this.z += za;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, this.sprite, false);
	}
}
