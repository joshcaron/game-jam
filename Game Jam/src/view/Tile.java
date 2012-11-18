package view;

import java.awt.Image;


//Represents a tile in the game
public class Tile {
	int type;
	Image img;
	
	Tile(int type, Image img){
		this.type = type;
		this.img = img;
	}
	
	Image getImage() {
		return this.img;
	}
	
	public int getType() {
		return this.type;
	}
}
