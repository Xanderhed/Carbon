package game;

import entity.visibleEntity.mob.Player;
import gfx.Screen;
import input.Keyboard;
import input.Mouse;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;
import level.TileCoordinate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final int SCALE = 4;
	public static final double FPS = 60.0;
	public static final String NAME = "Carbon";

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	// creates image that can be drawn onto
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	// here the buffered image is being converted into an array of integers, which signals which pixel gets which color. pretty much allows the image to be painted on, pixel by pixel
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;

	public Game() {

		// setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		// setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void init() {

		requestFocus();
		screen = new Screen(WIDTH, HEIGHT);
		key = new Keyboard();
		level = Level.One;
		TileCoordinate playerSpawn = new TileCoordinate(33, 30);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		level.add(player);
		addKeyListener(key);
		Mouse mouse= new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	// synchronization helps avoid thread inconsistencies, as any thread that tries to access a synchronized method must wait its turn to access the method
	public synchronized void start() {
		// causes the game to be run in a thread, which necessitates the use of a runnable. When the thread is started, it accesses the run method of a runnable
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// contains the main game loop
		long lastTime = System.nanoTime();
		long now;
		// this calculates how many nanoseconds occur per tick
		final double nsPerTick = 1000000000.0 / FPS;
		int ticks = 0;
		int frames = 0;
		// this variable will be used to find out when a second has occured, so it can be used to calculate FPS and ticks per second
		long lastTimer = System.currentTimeMillis();
		// delta is used to time out ticks, when delta has reached an even 1, it means enough time has gone by to allow the program to advance another tick
		double delta = 0;
		boolean shouldRender;

		init();

		// this loop actually advances frames and ticks, so it should only happen when the thread is running
		while (running) {
			now = System.nanoTime();
			// this calculates delta, which advances the program a tick when delta is greater than or equal to 1
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			// tells the program if it should render or frame or not. Brings the number of rendered frames in line with ticks. Is set to true to see how quickly the computer is processing, set to false to limit fps
			shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				// System.out.println("fps " + frames + ", tps " + ticks);
				frame.setTitle(NAME + "  |  " + "fps " + frames + ", tps " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		// updates logic of the game
		key.update();
		level.update();
	}

	public void render() {
		// prints out what the logic should tell the game to print out this object organizes the data on the canvas
		BufferStrategy bufferStrategy = getBufferStrategy();
		if (bufferStrategy == null) {
			// sets the level of buffering, in this case triple buffering
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		double xScroll = player.getX() - screen.width / 2 + player.sprite.WIDTH / 2;
		double yScroll = player.getY() - screen.height / 2 + player.sprite.HEIGHT / 2;
		level.render((int) xScroll, (int) yScroll, screen);
		//Sprite.spiral.update(true);
		//screen.renderSprite(5, 5, Sprite.spiral, true);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		// frees up any resources that the graphics object is using
		graphics.dispose();
		bufferStrategy.show();
	}

	public static void main(String[] args) {

		new Game().start();
	}

}
