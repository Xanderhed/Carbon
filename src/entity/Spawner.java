package entity;

import entity.visibleEntity.particle.Particle;

public class Spawner extends Entity{
	
	public enum Type {
		PARTICLE, MOB;
	}
	
	public Spawner(int x, int y, Type type, int amount) {
		this.x = x;
		this.y = y;
		for (int i = 0; i < amount; i++) {
			if(type == Type.PARTICLE) {
				level.add(new Particle(x, y, 60));
			}
		}
	}
}
