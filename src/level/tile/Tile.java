package level.tile;

import gfx.Screen;
import gfx.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile blank = new BlankTile(Sprite.blank);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile dirt = new DirtTile(Sprite.dirt);
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile tree = new TreeTile(Sprite.tree);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile brick = new BrickTile(Sprite.brick);
	public static Tile lillypad = new LillypadTile(Sprite.lillypad);
	
	public static final int grass_color = 0xff1eca49;
	public static final int water_color = 0xff0087c1;
	public static final int dirt_color = 0xff5f4636;
	public static final int stone_color = 0xffc3c3c3;
	public static final int tree_color = 0xff005c0e;
	public static final int flower_color = 0xffffff00;
	public static final int brick_color = 0xffba0000;
	public static final int lillypad_color = 0xff13b8ff;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		// Do render stuff here, always overridden by subclasses
	}
	
	public boolean solid() {
		return false;
	}
}
