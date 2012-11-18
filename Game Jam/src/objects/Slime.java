package objects;

import java.awt.Image;

public class Slime extends Monster {
	Slime(String s, int x, int y) {
		super(s, x, y);
	}
	
	Slime(Image i, int x, int y) {
		super(i, x, y);
	}
	
	public void moveLeft() {
		if (this.hasMask) {
			this.posX = this.posX - 40;
		} else {
			this.posX = this.posX - .5;
		}
	}
	
	public void moveRight() {
		if (this.hasMask) {
			this.posX = this.posX + 40;
		} else {
			this.posX = this.posX + .5;
		}
	}
	public void moveUp() {
		if (this.hasMask) {
			this.posY = this.posY - 40;
		} else {
			this.posY = this.posY - .5;
		}
	}
	public void moveDown() {
		if (this.hasMask) {
			this.posY = this.posY + 40;
		} else {
			this.posY = this.posY + .5;
		}
	}
}
