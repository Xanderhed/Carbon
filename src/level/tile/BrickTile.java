package level.tile;

import gfx.Screen;
import gfx.Sprite;

public class BrickTile extends Tile {

	public BrickTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
}
