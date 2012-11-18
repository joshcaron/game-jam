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
	/*
	 * Initial Constructor for World.
	 */
	@SuppressWarnings("serial")
	World() {
		init();
		currentScreen = currentMap.getScreens().get(currentScreenIndex);
		previousScreen = currentMap.getScreens().get(previousScreenIndex);
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
                
            }
        });
        
        // Left Key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "leftPressed");
        
        actionMap.put("leftPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                player.moveLeft();
            	if (!onScreen()) {
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
	// Loads the wood image
	static final Image wood = Toolkit.getDefaultToolkit().createImage("/home/josh/Dropbox/GameJam 2012/wood_tile.png");
	// Loads the stuff image
	static final Image stuff = Toolkit.getDefaultToolkit().createImage("/home/josh/Dropbox/GameJam 2012/Stuff.png");
	// Loads the toad image
	static final Image toad = Toolkit.getDefaultToolkit().createImage("/home/josh/Dropbox/GameJam 2012/Toad 1 down.png");
	static final Image toadDown = Toolkit.getDefaultToolkit().createImage("/home/josh/Dropbox/GameJam 2012/Toad 2 down.png");
	// Creates the wooden tile
	static Tile woodTile = new Tile(0, wood);
	// Creates the stuff tile
	static Tile stuffTile = new Tile(0, stuff);
	
	// Creates the images in the rows across
	static ArrayList<Tile> Wacross = new ArrayList<Tile>();
	// ArrayList of the rows
	static ArrayList<ArrayList<Tile>> Wdown = new ArrayList<ArrayList<Tile>>();// Creates the images in the rows across
	static ArrayList<Tile> Sacross = new ArrayList<Tile>();
	// ArrayList of the rows
	static ArrayList<ArrayList<Tile>> Sdown = new ArrayList<ArrayList<Tile>>();
	
	// Nodes
	static Node n0 = new Node(2,40,40);
	static Node n1 = new Node(0,440,40);
	static ArrayList<Node> an = new ArrayList<Node>();
	
	
	// Monsters
	static Monster m = new Monster(40,40,toad);
	static ArrayList<Monster> am = new ArrayList<Monster>();
	static ArrayList<Monster> emptyArrayMonster = new ArrayList<Monster>();

	// Player info
	static Monster player = new Monster(240,240, toad);
	
	// Props
	static ArrayList<Prop> props = new ArrayList<Prop>();
	
	// Screens
	static Screen s0 = new Screen("", am, Wdown, an, props);
	static Screen s1 = new Screen("", emptyArrayMonster, Sdown, an, props);
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
		player.makePlayer();
		for (int i = 0; i < 20; i++) {
			Wacross.add(woodTile);
		}
		for (int i0 = 0; i0 < 15; i0++) {
			Wdown.add(Wacross);
		}
		
		for (int i8 = 0; i8 < 20; i8++) {
			Sacross.add(stuffTile);
		}
		for (int i9 = 0; i9 < 15; i9++) {
			Sdown.add(Sacross);
		}
		// Adding Maps
		am.add(m);
		// Adding Screens
		as.add(s0);
		as.add(s1);
		// Adding Nodes
		an.add(n0);
		an.add(n1);
		
		Screen.makeDownPair(s0, s1);
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
		if (tick > 10000 || tick == -1) {
	    	moveMonsters();
			tick = 0;
		} else {
		}
		for (int q = 0; q < monsters.size(); q++) {
			Monster m = monsters.get(q);
			Image img2draw = m.getImage();
			g.drawImage(img2draw, m.getX(), m.getY(), this);
		}
		
		// Places the player
		g.drawImage(toad, player.getX(), player.getY(), this);
	}
	
	// Moves the monsters if applicable on tick
	static void moveMonsters() {
		Screen screen = currentScreen;
		ArrayList<Monster> monsters = screen.getMonsters();
		ArrayList<Node> nodes = screen.getNodes();
		
		// Node collision detection
		for (int m = 0; m < monsters.size(); m++) {
			Monster monster = monsters.get(m);
			for (int n = 0; n < nodes.size(); n++) {
				Node node = nodes.get(n);
				monster.collide(node);
			}
		}
		
		for(int z = 0; z < monsters.size(); z++) {
			Monster m = monsters.get(z);
			m.move();
		}
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