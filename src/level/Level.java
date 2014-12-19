package level;

import java.util.ArrayList;
import java.util.List;

import level.tile.Tile;
import entity.Entity;
import entity.visibleEntity.mob.Mob;
import entity.visibleEntity.mob.Player;
import entity.visibleEntity.particle.Particle;
import entity.visibleEntity.projectile.Projectile;
import gfx.Screen;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public List<Mob> mobs = new ArrayList<Mob>();
	public List<Player> players = new ArrayList<Player>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	
	public static Level One = new LevelOne("/Level 1.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
		populateLevel();
	}
	
	protected void generateLevel() {
		
	}
	
	protected void populateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		clear();
	}
	
	public boolean tileCollision(int x, int y, int width, int height, int xOffset, int yOffset) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			int xt = (x + c % 2 * width + xOffset) >> 4;
			int yt = (y + c / 2 * height + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Player) {
			players.add((Player) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Particle) {
			particles.add((Particle) e);
		} else {
			mobs.add((Mob) e);
		}
	}
	
	public List<Player> getClientPlayers() {
		return players;
	}
	
	public List<Player> getPlayersInRange (Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int xa = (int) e.getX();
		int ya = (int) e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int xb = (int) player.getX();
			int yb = (int) player.getY();
			
			int dx = Math.abs(xa - xb);
			int dy = Math.abs(ya - yb);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.blank;
		if(tiles[x + y * width] == Tile.grass_color) return Tile.grass;
		if(tiles[x + y * width] == Tile.dirt_color) return Tile.dirt;
		if(tiles[x + y * width] == Tile.stone_color) return Tile.stone;
		if(tiles[x + y * width] == Tile.water_color) return Tile.water;
		if(tiles[x + y * width] == Tile.tree_color) return Tile.tree;
		if(tiles[x + y * width] == Tile.flower_color) return Tile.flower;
		if(tiles[x + y * width] == Tile.brick_color) return Tile.brick;
		if(tiles[x + y * width] == Tile.lillypad_color) return Tile.lillypad;
		return Tile.blank;
	}
	
	protected void clear() {
		for (int i = 0; i < players.size(); i ++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).isRemoved()) mobs.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {;
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}
	
}
