package level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.visibleEntity.mob.AntiSpiral;
import entity.visibleEntity.mob.Spiral;

public class LevelOne extends Level {

	public LevelOne(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(LevelOne.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}

	protected void generateLevel() {
	}
	
	protected void populateLevel() {
		TileCoordinate spiralSpawn = new TileCoordinate(30, 30);
		TileCoordinate antiSpiralSpawn = new TileCoordinate(25, 31);
		this.add(new AntiSpiral(antiSpiralSpawn.x(), antiSpiralSpawn.y()));
		for (int i = 0; i < 10; i++) {
			this.add(new Spiral(spiralSpawn.x(), spiralSpawn.y()));
		}
	}
}
