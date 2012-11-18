package view;

import java.util.ArrayList;
import java.util.Iterator;

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
	Screen left;
	Screen right;
	Screen up;
	Screen down;
	
	
	Screen(String s) {
		this.id = s;
		
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
			if (monster.collideTile(this.tiles)) {
				System.out.println("Collide!");
				monster.reverseDirection();
			}
			
			for (int n = 0; n < nodes.size(); n++) {
				Node node = nodes.get(n);
				monster.collide(node);
			}
			
		}
	}
	
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
}







