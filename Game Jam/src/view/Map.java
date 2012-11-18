package view;

import java.util.ArrayList;

import objects.Monster;


//Each level of the game
public abstract class Map {
	int id;
	ArrayList<Screen> screens;
	Monster player;
	abstract Monster getPlayer();
	abstract ArrayList<Screen> getScreens();
}

//Northeastern level
class Northeastern extends Map {
	Northeastern(ArrayList<Screen> screens, Monster p) {
		this.screens = screens;
		this.player = p;
		this.id = 0;
	}
	
	Monster getPlayer() {
		return this.player;
	}
	
	public ArrayList<Screen> getScreens() {
		return this.screens;
	}
}









