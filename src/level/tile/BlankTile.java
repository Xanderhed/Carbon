package level.tile;

import gfx.Screen;
import gfx.Sprite;

public class BlankTile extends Tile {

	public BlankTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}