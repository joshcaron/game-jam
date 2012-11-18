package view;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import objects.Monster;
import objects.Node;
import objects.Prop;



//Represents the current screen that is being drawn
public class Screen {
	String id;
	ArrayList<Monster> monsters;
	ArrayList<ArrayList<Tile>> tiles;
	ArrayList<Node> nodes;
	ArrayList<Prop> props;
	ArrayList<Image> images;
	Screen left;
	Screen right;
	Screen up;
	Screen down;
	final static int GRIDWIDTH = 20;
	final static int GRIDHEIGHT = 15;
	final static boolean DEBUG = false;
	
	
	
	Screen(String s, ArrayList<Image> images) {
		this.images = images;
		this.id = s;
		File xmlfile = new File(s);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		
		this.tiles = new ArrayList<ArrayList<Tile>>();
		this.monsters = new ArrayList<Monster>();
		this.nodes = new ArrayList<Node>();
		this.props = new ArrayList<Prop>();
		
		try{
			// Document builder...? Parser for XML
			dBuilder = dbFactory.newDocumentBuilder();
			// This is the XML doc we need
			doc = dBuilder.parse(xmlfile);
			//`doc.getDocumentElement().normalize();
			if (DEBUG) {
				System.out.println(s + "XML loaded! \n");
			}
			// Loads the list containing Grid
			final NodeList GRID_NODE_LIST = doc.getElementsByTagName("Grid");
			// Loads Grid
			final org.w3c.dom.Node GRID_NODE = GRID_NODE_LIST.item(0);
			if (DEBUG) {
				System.out.println(GRID_NODE.getNodeName() + " loaded!");
			}
			// Loads the list containing Nodelist
			final NodeList NODELIST_NODE_LIST = doc.getElementsByTagName("Nodelist");
			// Loads Nodelist
			final org.w3c.dom.Node NODELIST_NODE = NODELIST_NODE_LIST.item(0);
			if (DEBUG) {
				System.out.println(NODELIST_NODE.getNodeName() + " loaded!");
			}
			// Loads the list containg Monsterlist
			final NodeList MONSTER_NODE_LIST = doc.getElementsByTagName("Monsterlist");
			// Loads Monsterlist
			final org.w3c.dom.Node MONSTERLIST_NODE = MONSTER_NODE_LIST.item(0);
			if (DEBUG) {
				System.out.println(MONSTERLIST_NODE.getNodeName() + " loaded!");
			}
			if (DEBUG) {
				// Finding the children of GRID
				System.out.println("\nFinding children of GRID");
				System.out.println("Children found? - " + GRID_NODE.hasChildNodes());
				
				// Finding the children of NODELIST
				System.out.println("\nFinding children of NODELIST");
				System.out.println("Children found? - " + NODELIST_NODE.hasChildNodes());
				
				// Finding the children of MONSTERLIST
				System.out.println("\nFinding children of MONSTERLIST");
				System.out.println("Children found? - " + MONSTERLIST_NODE.hasChildNodes());
			}
			
			
			// Creating the Grid from GRID_NODE
			final NodeList GRID_CHILDREN = GRID_NODE.getChildNodes();
			if (DEBUG) {
				System.out.println("\nGetting Grid children...");
				System.out.println("Children initated! Length: " + GRID_CHILDREN.getLength());
			}
			ArrayList<Tile> tmpList = new ArrayList<Tile>();
			for (int i = 0; i < GRID_CHILDREN.getLength(); i++) {
				org.w3c.dom.Node A_GRID_NODE = GRID_CHILDREN.item(i);
				String NODE_NAME = A_GRID_NODE.getNodeName();
				if (!(NODE_NAME.equals("#text"))) {
					final NamedNodeMap NODE_ATTRIBUTES = A_GRID_NODE.getAttributes();
					String value = NODE_ATTRIBUTES.getNamedItem("value").getNodeValue();
					if (DEBUG) {
						System.out.println("\nThis node name: " + A_GRID_NODE.getNodeName());
						System.out.println(value);
					}
					int valueVal = Integer.parseInt(value);
					tmpList.add(new Tile(valueVal));
				}
			}
			if (DEBUG) {
				System.out.println("Added all tiles temporarily!");
			}
			
			// Puts the tiles in a 2-dimensional array
			for (int y = 0; y < GRIDHEIGHT; y++) {
				ArrayList<Tile> row = new ArrayList<Tile>();
				for (int x = 0; x < GRIDWIDTH; x++) {
					if (DEBUG) {
						System.out.println("Current row: " + y);
						System.out.println("Current column: " + x);
						System.out.println(x + y*GRIDWIDTH);
					}
					row.add(tmpList.get(x + y*GRIDWIDTH));
				}
				this.tiles.add(row);
			}
			
			// Creating the Nodes from NODELIST_NODE
			final NodeList NODELIST_CHILDREN = NODELIST_NODE.getChildNodes();
			if (DEBUG) {
				System.out.println("\nGetting Nodelist children...");
				System.out.println("Children initated! Length: " + NODELIST_CHILDREN.getLength());
			}
			for (int i = 0; i < NODELIST_CHILDREN.getLength(); i++) {
				org.w3c.dom.Node NODE_NODE = NODELIST_CHILDREN.item(i);
				String NODE_NAME = NODE_NODE.getNodeName();
				if (!(NODE_NAME.equals("#text"))) {
					final NamedNodeMap NODE_ATTRIBUTES = NODE_NODE.getAttributes();
					String type = NODE_ATTRIBUTES.getNamedItem("type").getNodeValue();
					String x = NODE_ATTRIBUTES.getNamedItem("x").getNodeValue();
					String y = NODE_ATTRIBUTES.getNamedItem("y").getNodeValue();
					if (DEBUG) {
						System.out.println("\nThis node name: " + NODE_NODE.getNodeName());
						System.out.println(x);
						System.out.println(y);
						System.out.println(type);
					}
					int typeVal = Integer.parseInt(type);
					int xVal = Integer.parseInt(x);
					int yVal = Integer.parseInt(y);
					this.nodes.add(new Node(typeVal, xVal*40, yVal*40));
				}
			}
			
			
			// Creating the Monsters from MONSTERLIST_NODE
			final NodeList MONSTERLIST_CHILDREN = MONSTERLIST_NODE.getChildNodes();
			if (DEBUG) {
				System.out.println("\nGetting Monster children...");
				System.out.println("Children initated! Length: " + MONSTERLIST_CHILDREN.getLength());
			}
			
			for (int i = 0; i < MONSTERLIST_CHILDREN.getLength(); i++) {
				org.w3c.dom.Node MONSTER_NODE = MONSTERLIST_CHILDREN.item(i);
				if (!(MONSTER_NODE.getNodeName().equals("#text"))) {
					final NamedNodeMap MONSTER_ATTRIBUTES = MONSTER_NODE.getAttributes();
					String type = MONSTER_ATTRIBUTES.getNamedItem("type").getNodeValue();
					String x = MONSTER_ATTRIBUTES.getNamedItem("x").getNodeValue();
					String y = MONSTER_ATTRIBUTES.getNamedItem("y").getNodeValue();
					if (DEBUG) {
						System.out.println("\nThis node name: " + MONSTER_NODE.getNodeName());
						System.out.println(x);
						System.out.println(y);
						System.out.println(type);
					}
					String typeVal = type;
					int xVal = Integer.parseInt(x);
					int yVal = Integer.parseInt(y);
					this.monsters.add(Monster.makeMonster(typeVal, xVal*40, yVal*40));
				}
			}
		} catch (Exception e){
			System.out.println("Error loading screen xml");
		}
	}
	
	Screen(String id, ArrayList<Monster> monsters, ArrayList<ArrayList<Tile>> tiles, ArrayList<Node> nodes, ArrayList<Prop> props) {
		this.id = id;
		this.monsters = monsters;
		this.tiles = tiles;
		this.nodes = nodes;
		this.props = props;
	}
	
	ArrayList<ArrayList<Tile>> getTiles() {
		return this.tiles;
	}
	
	ArrayList<Monster> getMonsters() {
		return this.monsters;
	}
	
	ArrayList<Node> getNodes() {
		return this.nodes;
	}
	
	// Sets it so that s1 is to the LEFT of s2
	public static void makeLeftPair(Screen s1, Screen s2) {
		s1.setRight(s2);
		s2.setLeft(s1);
	}
	
	// Sets is so that s1 is to the RIGHT of s2
	public static void makeRightPair(Screen s1, Screen s2) {
		s1.setLeft(s2);
		s2.setRight(s1);
	}
	
	// Sets it so that s1 is ABOVE of s2
	public static void makeUpPair(Screen s1, Screen s2) {
		s1.setDown(s2);
		s2.setUp(s1);
	}
	
	public static void makeDownPair(Screen s1, Screen s2) {
		s1.setUp(s2);
		s2.setDown(s1);
	}
	
	void setLeft(Screen s) {
		this.left = s;
	}
	
	void setRight(Screen s) {
		this.right = s;
	}
	
	void setUp(Screen s) {
		this.up = s;
	}
	
	void setDown(Screen s) {
		this.down = s;
	}
	
	boolean hasUp() {
		return !(this.up == null);
	}
	
	Screen getUp() {
		return this.up;
	}
	
	boolean hasDown() {
		return !(this.down == null);
	}
	
	Screen getDown() {
		return this.down;
	}
	
	boolean hasLeft() {
		return !(this.left == null);
	}
	
	Screen getLeft() {
		return this.left;
	}
	
	boolean hasRight() {
		return !(this.right == null);
	}
	
	Screen getRight() {
		return this.right;
	}
	
	void moveMonsters() {
		// Node collision detection
		for (int m = 0; m < monsters.size(); m++) {
			Monster monster = monsters.get(m);
			monster.move();
			/*
			if (monster.collideTile(this.tiles)) {
				System.out.println("Collide!");
				monster.reverseDirection();
			}
			*/
			for (int n = 0; n < nodes.size(); n++) {
				Node node = nodes.get(n);
				monster.collide(node);
			}
			
		}
	}
	@SuppressWarnings("unused")
	Monster findPlayer() {
		Monster player = null;
		Iterator<Monster> it = this.monsters.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Monster m = it.next();
			if (m.hasMask()) {
				player = m;
			}
		}
		if (player == null) {
			throw new RuntimeException("Player not found");
		} else return player; 
	}
	
	@SuppressWarnings("unused")
	void setTileImages() {
		Iterator<ArrayList<Tile>> it = this.tiles.iterator();
		
		for (int i = 0; it.hasNext(); i++) {
			ArrayList<Tile> row = it.next();
			Iterator<Tile> rowIt = row.iterator();
			for (int i1 = 0; rowIt.hasNext(); i1++) {
				Tile t = rowIt.next();
				t.setImage(this.images.get(t.getType()));
			}
		}
	}
}







