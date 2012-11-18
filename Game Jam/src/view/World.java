package view;
import javax.swing.*;

import objects.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
// The world in which the game is run
public class World extends JComponent {
	private static final long serialVersionUID = 1L;
	private static final boolean DEBUG = false;
	/*
	 * Initial Constructor for World.
	 */
	@SuppressWarnings("serial")
	World() {
		init();
		currentScreen = currentMap.getScreens().get(currentScreenIndex);
		previousScreen = currentMap.getScreens().get(previousScreenIndex);
		player.setMoving(false);
		/*
		 * Key Bindings created on initialization
		 */
		// Escape Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "quit" );
	    
		actionMap.put("quit", new AbstractAction() {
	          public void actionPerformed(ActionEvent e) {
	              System.exit(0);
	          }
	      });
		
		// Down Key
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0), "downPressed");
        
        actionMap.put("downPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	player.moveDown();
                if (!onScreen()) {
                	if(currentScreen.hasDown()) {
                		previousScreen = currentScreen;
                		currentScreen = currentScreen.getDown();
                		player.setY(0);
                	} else {
                    	player.moveUp();	
                	}
                }
                if (player.collideTile(currentScreen.getTiles())) {
                	player.moveUp();
                }
                
            }
        });
        
        // Up Key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0), "upPressed");
        
        actionMap.put("upPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	player.moveUp();
                if (!onScreen()) {
                	if(currentScreen.hasUp()) {
                		previousScreen = currentScreen;
                		currentScreen = currentScreen.getUp();
                		player.setY(560);
                	} else {
                    	player.moveDown();	
                	}
                }
                if (player.collideTile(currentScreen.getTiles())) {
                	player.moveDown();
                }
                
            }
        });
        
        // Left Key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "leftPressed");
        
        actionMap.put("leftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            	player.moveLeft();
                if (!onScreen()) {
                	if(currentScreen.hasLeft()) {
                		previousScreen = currentScreen;
                		currentScreen = currentScreen.getLeft();
                		player.setX(760);
                	} else {
                    	player.moveRight();	
                	}
                }
                if (player.collideTile(currentScreen.getTiles())) {
                	player.moveRight();
                }
            }
        });
        
        // Right Key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "rightPressed");
        
        actionMap.put("rightPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.moveRight();
                if (!onScreen()) {
                	if(currentScreen.hasRight()) {
                		previousScreen = currentScreen;
                		currentScreen = currentScreen.getRight();
                		player.setX(0);
                	} else {
                    	player.moveLeft();	
                	}
                }
                if (player.collideTile(currentScreen.getTiles())) {
                	player.moveLeft();
                }
            }
        });	
        /*
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                PlaySound.sound = new File("../Ajax/src/sound/Sound.wav");
                soundThread = new Thread(PlaySound.play).start();
            }
            //If you want the sound to stop you can just add another override, but you need to keep track of the sound thread
            @Override
            public void windowClosing(WindowEvent e) {
                soundThread.interrupt();
            }
        });
        */
	}
	
	/*
	 * Makes the initial objects... for now...
	 */
	// Loads background images
	static final Image wood = Toolkit.getDefaultToolkit().createImage("img/bg/wood_tile.png");
	static final Image stuff = Toolkit.getDefaultToolkit().createImage("img/bg/Stuff.png");
	static final Image background3 = Toolkit.getDefaultToolkit().createImage("img/bg/background 3.png");
	static final Image water = Toolkit.getDefaultToolkit().createImage("img/bg/Water tile.png");
	static final Image blue = Toolkit.getDefaultToolkit().createImage("img/bg/Blue tile.png");
	static final Image brown = Toolkit.getDefaultToolkit().createImage("img/bg/Brown tile.png");
	static final Image lime = Toolkit.getDefaultToolkit().createImage("img/bg/Lime tile.png");
	static final Image maroon = Toolkit.getDefaultToolkit().createImage("img/bg/Maroon 5 tile.png");
	static final Image peach = Toolkit.getDefaultToolkit().createImage("img/bg/Peach tile.png");
	// Loads the toad image
	static final Image toadUpStand = Toolkit.getDefaultToolkit().createImage("img/toad/Toad up stand.png");
	static final Image toadUpMove = Toolkit.getDefaultToolkit().createImage("img/toad/Toad up move.png");
	static final Image toadLeftStand = Toolkit.getDefaultToolkit().createImage("img/toad/Toad left stand.png");
	static final Image toadLeftMove = Toolkit.getDefaultToolkit().createImage("img/toad/Toad left move.png");
	static final Image toadRightStand = Toolkit.getDefaultToolkit().createImage("img/toad/Toad right stand.png");
	static final Image toadRightMove = Toolkit.getDefaultToolkit().createImage("img/toad/Toad right move.png");
	static final Image toadDownStand = Toolkit.getDefaultToolkit().createImage("img/toad/Toad down stand.png");
	static final Image toadDownMove = Toolkit.getDefaultToolkit().createImage("img/toad/Toad down move.png");
	// Loads the ghost image
	static final Image ghost = Toolkit.getDefaultToolkit().createImage("img/ghost/Ghost down 1.png");
	// Creates the tiles
	static final Tile woodTile = new Tile(0, wood);
	static final Tile stuffTile = new Tile(0, stuff);
	static final Tile bg3Tile = new Tile(0, background3);
	static final Tile waterTile = new Tile(0, water);
	static final Tile blueTile = new Tile(0, blue);
	static final Tile brownTile = new Tile(0, brown);
	static final Tile limeTile = new Tile(0, lime);
	static final Tile maroonTile = new Tile(0, maroon);
	static final Tile peachTile = new Tile(0, peach);
	// Creates the images in the rows across
	static ArrayList<Tile> Wacross = new ArrayList<Tile>();
	static ArrayList<Tile> Sacross = new ArrayList<Tile>();
	static ArrayList<Tile> bg3across = new ArrayList<Tile>();
	static ArrayList<Tile> waterAcross = new ArrayList<Tile>();
	static ArrayList<Tile> blueAcross = new ArrayList<Tile>();
	static ArrayList<Tile> brownAcross = new ArrayList<Tile>();
	static ArrayList<Tile> limeAcross = new ArrayList<Tile>();
	static ArrayList<Tile> maroonAcross = new ArrayList<Tile>();
	static ArrayList<Tile> peachAcross = new ArrayList<Tile>();
	// ArrayList of the rows
	static ArrayList<ArrayList<Tile>> Wdown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> Sdown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> bg3Down = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> waterDown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> blueDown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> brownDown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> limeDown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> maroonDown = new ArrayList<ArrayList<Tile>>();
	static ArrayList<ArrayList<Tile>> peachDown = new ArrayList<ArrayList<Tile>>();
	
	// Nodes
	static Node n0 = new Node(2,0,40);
	static Node n1 = new Node(0,440,40);
	static Node n2 = new Node(3,320,80);
	static Node n3 = new Node(1,320,440);
	static ArrayList<Node> an = new ArrayList<Node>();
	
	// Monsters
	static Monster m = new Monster(40,40,toadUpStand,toadUpMove,toadLeftStand,toadLeftMove,toadRightStand,toadRightMove,toadDownStand,toadDownMove);
	static Monster m1 = new Monster(320,120,toadUpStand,toadUpMove,toadLeftStand,toadLeftMove,toadRightStand,toadRightMove,toadDownStand,toadDownMove);
	static Monster m2 = new Monster(240,240,toadUpStand,toadUpMove,toadLeftStand,toadLeftMove,toadRightStand,toadRightMove,toadDownStand,toadDownMove);
	static ArrayList<Monster> am = new ArrayList<Monster>();
	static ArrayList<Monster> emptyArrayMonster = new ArrayList<Monster>();

	// Player info
	static Monster player = m2;
	
	// Props
	static ArrayList<Prop> props = new ArrayList<Prop>();
	
	// Screens
	static Screen s0 = new Screen("", am, Wdown, an, props);
	static Screen s1 = new Screen("", emptyArrayMonster, Sdown, an, props);
	static Screen s2 = new Screen("", emptyArrayMonster, bg3Down,an, props);
	static Screen s3 = new Screen("", emptyArrayMonster, waterDown,an, props);
	static Screen s4 = new Screen("", emptyArrayMonster, blueDown,an, props);
	static Screen s5 = new Screen("", emptyArrayMonster, brownDown,an, props);
	static Screen s6 = new Screen("", emptyArrayMonster, limeDown,an, props);
	static Screen s7 = new Screen("", emptyArrayMonster, maroonDown,an, props);
	static Screen s8 = new Screen("", emptyArrayMonster, peachDown,an, props);
	static ArrayList<Screen> as = new ArrayList<Screen>();
	
	// Maps
	static Map nu = new Northeastern(as,player);
	
	// Sets the current Map and Screen
	static Map currentMap = nu;
	static int currentScreenIndex = 0;
	static int previousScreenIndex = 1;
	static Screen currentScreen;
	static Screen previousScreen;
	
	// Map Listening
	static boolean onScreen() {
		return (0 <= player.getX()) && (760 >= player.getX()) && (0 <= player.getY()) && (560 >= player.getY());
	}
	
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
		m2.makePlayer();
		m.setDirection(0);
		m1.setDirection(3);
		// Adding wood images
		for (int i = 0; i < 20; i++) {
			Wacross.add(woodTile);
		}
		for (int i0 = 0; i0 < 15; i0++) {
			Wdown.add(Wacross);
		}
		
		// Adding stuff images
		for (int i8 = 0; i8 < 20; i8++) {
			Sacross.add(stuffTile);
		}
		for (int i9 = 0; i9 < 15; i9++) {
			Sdown.add(Sacross);
		}
		
		// Adding bg3 images
		for (int i8 = 0; i8 < 20; i8++) {
			bg3across.add(bg3Tile);
		}
		for (int i10 = 0; i10 < 15; i10++) {
			bg3Down.add(bg3across);
		}
		
		// Adding water images
		for (int i8 = 0; i8 < 20; i8++) {
			waterAcross.add(waterTile);
		}
		for (int i11 = 0; i11 < 15; i11++) {
			waterDown.add(waterAcross);
		}
		
		// Adding blue images
		for (int i8 = 0; i8 < 20; i8++) {
			blueAcross.add(blueTile);
		}
		for (int i12 = 0; i12 < 15; i12++) {
			blueDown.add(blueAcross);
		}
		// Adding brown images
		for (int tooMany = 0; tooMany < 20; tooMany++) {
			brownAcross.add(brownTile);
		}
		for (int whyy = 0; whyy < 15; whyy++) {
			brownDown.add(brownAcross);
		}
		// Adding lime images
		for (int i8 = 0; i8 < 20; i8++) {
			limeAcross.add(limeTile);
		}
		for (int i13 = 0; i13 < 15; i13++) {
			limeDown.add(limeAcross);
		}
		
		// Adding maroon images
		for (int i8 = 0; i8 < 20; i8++) {
			maroonAcross.add(maroonTile);
		}
		for (int i14 = 0; i14 < 15; i14++) {
			maroonDown.add(maroonAcross);
		}
		
		// Adding peach images
		for (int i8 = 0; i8 < 20; i8++) {
			peachAcross.add(peachTile);
		}
		for (int i15 = 0; i15 < 15; i15++) {
			peachDown.add(peachAcross);
		}
		// Adding Monsters
		am.add(m);
		am.add(m1);
		am.add(m2);
		emptyArrayMonster.add(m2);
		// Adding Screens
		as.add(s0);
		as.add(s1);
		// Adding Nodes
		an.add(n0);
		an.add(n1);
		an.add(n2);
		an.add(n3);
		
		// Making screen pairs across
		Screen.makeLeftPair(s0, s1);
		Screen.makeLeftPair(s1, s2);
		Screen.makeLeftPair(s3, s4);
		Screen.makeLeftPair(s4, s5);
		Screen.makeLeftPair(s6, s7);
		Screen.makeLeftPair(s7, s8);
		// Making screen pairs down
		Screen.makeUpPair(s0, s3);
		Screen.makeUpPair(s3, s6);
		Screen.makeUpPair(s1, s4);
		Screen.makeUpPair(s4, s7);
		Screen.makeUpPair(s2, s5);
		Screen.makeUpPair(s5, s8);
		
	}
	
	// Places the graphics on the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Screen screen = currentScreen;
		ArrayList<ArrayList<Tile>> tiles = screen.getTiles();
		ArrayList<Monster> monsters = screen.getMonsters();
		Monster player = currentMap.player;
		
		// Paints the backgrounds
		for (int i1 = 0; i1 < tiles.size(); i1++) {
			ArrayList<Tile> row = tiles.get(i1);
			for (int i2 = 0; i2 < row.size(); i2++) {
				Image img2draw = row.get(i2).getImage();
				g.drawImage(img2draw, i2*40, i1*40, this);
			}
		}

		// Places the monsters
		if (tick > 100000 || tick == -1) {
	    	screen.moveMonsters();
			tick = 0;
		} 
		for (int q = 0; q < monsters.size(); q++) {
			Monster m = monsters.get(q);
			Image img2draw = m.getImage();
			g.drawImage(img2draw, m.getX(), m.getY(), this);
		}
		
		// Places the player
		// g.drawImage(player.getImage(), player.getX(), player.getY(), this);
	}
	
    // Dimension setting
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

    public Dimension getMinimumSize() {
    	return getPreferredSize();
    } 
    
    
    
    
    /*
     * THIS IS WHAT MAKES THE GAME RUN
     * 
     * YAY
     */
	public static void main(String[] args) {
		init();
	    JFrame mainFrame = new JFrame("Game Quest 7");
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.getContentPane().add(new World());
	    mainFrame.pack();
	    mainFrame.setVisible(true);
	    while(true) {
	    	mainFrame.repaint();
	    	tick++;
	    }
	}
}