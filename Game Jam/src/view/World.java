package view;

import javax.swing.*;

import objects.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.sound.*;
import javax.sound.sampled.*;
import sun.audio.*;
import java.io.*;
import javax.swing.*;




// The world in which the game is run
public class World extends JComponent {
	
	/*
	public static void music(){
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try{
			BGM = new AudioStream(new FileInputStream("music/First_L"));
			MD = BGM.getData();
			loop = new ContinuousAudioDataStream(MD);
		}catch(IOException error){
			System.out.print("file not found");
		}
		
		MGP.start(loop);
	}
	*/
	
	
	
	private static final long serialVersionUID = 1L;

	/*
	 * Initial Constructor for World.
	 */
	@SuppressWarnings("serial")
	World() {
		
		currentScreen = currentMap.getScreens().get(currentScreenIndex);
		previousScreen = currentScreen;
		player = currentScreen.findPlayer();

		// Map Listening

		class Methods {
			Methods() {
			}

			boolean onScreen(Monster m) {
				return (0 <= m.getX()) && (760 >= m.getX()) && (0 <= m.getY())
						&& (560 >= m.getY());
			}
		}

		final Methods methods = new Methods();

		/*
		 * Key Bindings created on initialization
		 */
		// Escape Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quit");

		actionMap.put("quit", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Down Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "downPressed");

		actionMap.put("downPressed", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				previousScreen = currentScreen;
				player.setDirection(3);
				player.move();
				final boolean onScreen = methods.onScreen(player);
				if (!onScreen) {
					if (currentScreen.hasDown()) {
						currentScreen = currentScreen.getDown();
						ArrayList<Monster> monsters = currentScreen
								.getMonsters();
						monsters.add(player);
						player.setY(0);
					} else {
						player.moveUp();
					}
				}
				if (player.collideTile(currentScreen.getTiles())) {
					player.moveUp();
				}
				player.setDirection(-1);
			}
		});

		// Up Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upPressed");

		actionMap.put("upPressed", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				previousScreen = currentScreen;
				player.setDirection(1);
				player.move();
				final boolean onScreen = methods.onScreen(player);
				if (!onScreen) {
					if (currentScreen.hasUp()) {
						currentScreen = currentScreen.getUp();
						ArrayList<Monster> monsters = currentScreen
								.getMonsters();
						monsters.add(player);
						player.setY(560);
					} else {
						player.moveDown();
					}
				}
				if (player.collideTile(currentScreen.getTiles())) {
					player.moveDown();
				}
				player.setDirection(-1);

			}
		});

		// Left Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "leftPressed");

		actionMap.put("leftPressed", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				previousScreen = currentScreen;
				player.setDirection(0);
				player.move();
				final boolean onScreen = methods.onScreen(player);
				if (!onScreen) {
					if (currentScreen.hasLeft()) {
						currentScreen = currentScreen.getLeft();
						ArrayList<Monster> monsters = currentScreen
								.getMonsters();
						monsters.add(player);
						player.setX(760);
					} else {
						player.moveRight();
					}
				}
				if (player.collideTile(currentScreen.getTiles())) {
					player.moveRight();
				}
				player.setDirection(-1);
			}
		});

		// Right Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				"rightPressed");

		actionMap.put("rightPressed", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				previousScreen = currentScreen;
				player.setDirection(2);
				player.move();
				final boolean onScreen = methods.onScreen(player);
				if (!onScreen) {
					if (currentScreen.hasRight()) {
						currentScreen = currentScreen.getRight();
						ArrayList<Monster> monsters = currentScreen
								.getMonsters();
						monsters.add(player);
						player.setX(0);
					} else {
						player.moveLeft();
					}
				}
				if (player.collideTile(currentScreen.getTiles())) {
					player.moveLeft();
				}
				player.setDirection(-1);
			}
		});
/*
		// Space Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
				"spacePressed");

		actionMap.put("spacePressed", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				player.throwMask();
			}
		});
		*/
	}

	/*
	 * Makes the initial objects
	 */
	// NEU background images
	static final Image BlueTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/BlueTile.png");
	static final Image BrickTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/BrickTile.png");
	static final Image BrownTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/BrownTile.png");
	static final Image GrassTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/GrassTile.png");
	static final Image IndoorTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/IndoorTile.png");
	static final Image IndoorTileBeige = Toolkit.getDefaultToolkit()
			.createImage("img/tiles/IndoorTileBeige.png");
	static final Image LimeTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/LimeTile.png");
	static final Image MaroonTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/MaroonTile.png");
	static final Image PeachTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/PeachTile.png");
	static final Image RoadBottomTile = Toolkit.getDefaultToolkit()
			.createImage("img/tiles/RoadBottomTile.png");
	static final Image RoadTileExtra = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoadTileExtra.png");
	static final Image RoadTopTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoadTopTile.png");
	static final Image RockTextureTile = Toolkit.getDefaultToolkit()
			.createImage("img/tiles/RockTextureTile.png");
	static final Image RoofTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoofTile.png");
	static final Image VioletTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/VioletTile.png");
	static final Image WaterTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/WaterTile.png");
	static final Image WindowTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/WindowTile.png");
	static final Image WoodTile = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/WoodTile.png");
	static final Image CautionTape = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/CautionTape.png");
	static final Image HoleTrap = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/HoleTrap.png");
	static final Image RoadMiddle = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoadMiddle.png");
	static final Image TrainTrackBottom = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/TrainTrackBottom.png");
	static final Image TrainTileMiddle = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/TrainTileMiddle.png");
	static final Image RoadNoLines = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoadNoLines.png");
	static final Image RoadLeft = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoadLeft.png");
	static final Image RoadRight = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/RoadRight.png");
	static final Image TrainTileTop = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/TrainTileTop.png");
	
	// NEU Character Images
	static final Image toad = Toolkit.getDefaultToolkit().createImage(
			"img/monsters/toad.png");
	static final Image maskedToad = Toolkit.getDefaultToolkit().createImage(
			"img/monsters/maskedToad.png");
	static final Image NUPD = Toolkit.getDefaultToolkit().createImage(
			"img/monsters/NUPD.png");
	static final Image maskedNUPD = Toolkit.getDefaultToolkit().createImage(
			"img/monsters/maskedNUPD.png");
	static final Image slime = Toolkit.getDefaultToolkit().createImage(
			"img/monsters/slime.png");
	static final Image maskedSlime = Toolkit.getDefaultToolkit().createImage(
			"img/monsters/maskedSlime.png");
	

	// Mask
	static final Image plainMask = Toolkit.getDefaultToolkit().createImage(
			"img/tiles/GrassTile.png");
	static Mask aMask = new Mask(600, 280, plainMask);

	// Player info
	static Monster player = Monster.makeMonster(maskedToad, 600, 280);

	// Screens
	static ArrayList<Screen> NEUScreens = new ArrayList<Screen>();

	// NEU Image Palette
	static final ArrayList<Image> NEUPalette = new ArrayList<Image>();
	static final Screen NEU0 = new Screen("screens/NEU/screen0.xml", NEUPalette);
	static final Screen NEU1 = new Screen("screens/NEU/screen1.xml", NEUPalette);
	static final Screen NEU2 = new Screen("screens/NEU/screen2.xml", NEUPalette);
	static final Screen NEU3 = new Screen("screens/NEU/screen3.xml", NEUPalette);
	static final Screen NEU4 = new Screen("screens/NEU/screen4.xml", NEUPalette);
	static final Screen NEU5 = new Screen("screens/NEU/screen5.xml", NEUPalette);
	static final Screen NEU6 = new Screen("screens/NEU/screen6.xml", NEUPalette);
	static final Screen NEU7 = new Screen("screens/NEU/screen7.xml", NEUPalette);

	// Maps
	static Map NEU = new Northeastern(NEUScreens, player);

	// Sets the current Map and Screen
	static Map currentMap;
	static int currentScreenIndex = 0;
	static Screen currentScreen;
	static Screen previousScreen;

	// Key Listening
	InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = getActionMap();

	// Current tick for the world
	static int tick = -1;

	/*
	 * Required Methods for the World
	 */
	// Populates the ArrayLists with images
	static void init() {
		// Make NEU Image Palette
		NEUPalette.add(GrassTile); 				// 0
		NEUPalette.add(RoadNoLines);			// 1
		NEUPalette.add(CautionTape); 			// 2
		NEUPalette.add(TrainTileMiddle);		// 3
		NEUPalette.add(HoleTrap);				// 4
		NEUPalette.add(TrainTrackBottom);		// 5
		NEUPalette.add(RoadMiddle);				// 6
		NEUPalette.add(RoadBottomTile);			// 7
		NEUPalette.add(RoadTopTile);			// 8
		NEUPalette.add(BrickTile);				// 9
		NEUPalette.add(RoadLeft);				// 10
		NEUPalette.add(RoadRight);				// 11
		NEUPalette.add(TrainTileTop);			// 12
		

		// Initialize Tile Images in each screen
		NEU0.setTileImages();
		NEU1.setTileImages();
		NEU2.setTileImages();
		NEU3.setTileImages();
		NEU4.setTileImages();
		NEU5.setTileImages();
		NEU6.setTileImages();
		NEU7.setTileImages();

		// Adds the screens from XML
		NEUScreens.add(NEU0);
		NEUScreens.add(NEU1);
		NEUScreens.add(NEU2);
		NEUScreens.add(NEU3);
		NEUScreens.add(NEU4);
		NEUScreens.add(NEU5);
		NEUScreens.add(NEU6);
		NEUScreens.add(NEU7);

		currentMap = NEU;
		currentScreenIndex = 0;

		// Makes connections between screens for NEU
		Screen.makeLeftPair(NEU0, NEU1);
		Screen.makeLeftPair(NEU2, NEU3);
		Screen.makeLeftPair(NEU4, NEU5);
		Screen.makeLeftPair(NEU6, NEU7);
		Screen.makeUpPair(NEU0, NEU2);
		Screen.makeUpPair(NEU1, NEU3);
		Screen.makeUpPair(NEU2, NEU4);
		Screen.makeUpPair(NEU3, NEU5);
		Screen.makeUpPair(NEU4, NEU6);
		Screen.makeUpPair(NEU5, NEU7);

		Screen theScreen = currentMap.getScreens().get(currentScreenIndex);
		ArrayList<Monster> monsters = theScreen.getMonsters();
		monsters.add(player);
		player.makePlayer();
	}
	
	void paintBackground(Graphics g) {
		Screen screen = currentScreen;
		ArrayList<ArrayList<Tile>> tiles = screen.getTiles();
		// Paints the backgrounds
		for (int y = 0; y < tiles.size(); y++) {
			ArrayList<Tile> row = tiles.get(y);
			for (int x = 0; x < row.size(); x++) {
				Image img2draw = row.get(x).getImage();
				g.drawImage(img2draw, x * 40, y * 40, this);
			}
		}
	}

	// Places the graphics on the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Screen screen = currentScreen;
		ArrayList<Monster> monsters = screen.getMonsters();
		Monster thePlayer = currentScreen.findPlayer();
		Mask theMask = thePlayer.getMask();
		paintBackground(g);
		// Places the monsters
		if (tick > 100000 || tick == -1) {
			screen.moveMonsters();
			if (theMask.isMoving()) {
				theMask.boomerang(theMask.getDirection());
				previousScreen = currentScreen;
			} else {
				theMask.setX(player.getX());
				theMask.setY(player.getY());
			}
			tick = 0;
		}
		for (int q = 0; q < monsters.size(); q++) {
			Monster m = monsters.get(q);
			Image img2draw = m.getImage();
			g.drawImage(img2draw, (int) m.getX(), (int) m.getY(), this);
		}

		// Places the player
		// g.drawImage(player.getImage(), (int) player.getX(), (int)
		// player.getY(), this);
		// Places the theMask
		/*
		g.drawImage(theMask.getImage(), (int) theMask.getX(),
				(int) theMask.getY(), this);
				*/
	}

	// Dimension setting
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	static void debug() {
		System.out.println("\n\n\nPlayer");
		System.out.println("-------------------");
		System.out.println("X: " + player.getX());
		System.out.println("Y: " + player.getY());

		System.out.println("\nMonsters");
		System.out.println("-------------------");
		ArrayList<Monster> m = currentScreen.getMonsters();
		for (int i = 0; i < m.size(); i++) {
			Monster monster = m.get(i);
			System.out.println("\nX: " + monster.getX());
			System.out.println("Y: " + monster.getY());
		}

		System.out.println("\nNodes");
		System.out.println("-------------------");
		ArrayList<Node> nodes = currentScreen.getNodes();
		for (int i1 = 0; i1 < nodes.size(); i1++) {
			Node node = nodes.get(i1);
			System.out.println("\nX: " + node.getX());
			System.out.println("Y: " + node.getY());
		}
	}

	public static void main(String[] args) {
		final boolean DEBUG = false;
		init();
		JFrame mainFrame = new JFrame("Game Quest 7");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(new World());
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		
		if (DEBUG) {
			debug();
		}
		
		(new MusicPlayer()).start();
		
		while(true) {
			mainFrame.repaint();
			tick++;
		}
		
		
	}
	
	
	
}