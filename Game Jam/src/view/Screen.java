package view;

import java.util.ArrayList;

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
	
	
}