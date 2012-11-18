package objects;

import java.awt.Image;

public class Toad extends Monster {

	public Toad(String s, int x, int y) {
		super(s, x, y);
	}
	
	public Toad(Image i, int x, int y) {
		super(i, x, y);
	}

	public void moveLeft() {
		if (this.hasMask) {
			this.posX = this.posX - 40;
		} else {
			this.posX = this.posX - 1.25;
		}
	}
	
	public void moveRight() {
		if (this.hasMask) {
			this.posX = this.posX + 40;
		} else {
			this.posX = this.posX + 1.25;
		}
	}
	public void moveUp() {
		if (this.hasMask) {
			this.posY = this.posY - 40;
		} else {
			this.posY = this.posY - 1.25;
		}
	}
	public void moveDown() {
		if (this.hasMask) {
			this.posY = this.posY + 40;
		} else {
			this.posY = this.posY + 1.25;
		}
	}
	
}
