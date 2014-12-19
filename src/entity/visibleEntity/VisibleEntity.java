package entity.visibleEntity;

import entity.Entity;
import gfx.Sprite;

public class VisibleEntity extends Entity {

	public Sprite sprite;
	
	public boolean obstructed() {
		return false;
	}
	
	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 * 16) / 16;
			double yt = ((y + ya) + c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile( ix, iy).solid()) solid = true;
		}
		return solid;
	}
}
