package objects;
import java.awt.Image;

//Represents any Monster in the game 
public class Monster extends Entity {
	int hitPoints;
	int direction;
	Image img;
	boolean hasMask;
	public Monster(int x, int y, Image img) {
		this.posX = x;
		this.posY = y;
		this.img = img;
		this.direction = -1;
		this.hasMask = false;
		this.hitPoints = 3;
	}
	
	public void move() {
		if (this.direction == 0) {
			this.moveLeft();
		}
		if (this.direction == 1) {
			this.moveUp();
		}
		if (this.direction == 2) {
			this.moveRight();
		}
		if (this.direction == 3) {
			this.moveDown();
		}
	}
	

	public void moveLeft() {
		if (this.hasMask) {
			this.posX = this.posX - 40;
		} else {
			this.posX = this.posX - 1;
		}
	}
	public void moveRight() {
		if (this.hasMask) {
			this.posX = this.posX + 40;
		} else {
			this.posX = this.posX + 1;
		}
	}
	public void moveUp() {
		this.posY = this.posY - 40;
	}
	public void moveDown() {
		this.posY = this.posY + 40;
	}
	
	public void makePlayer() {
		this.hasMask = true;
	}
	
	public boolean collide(Entity e) {
		if (e.getX() == this.posX && e.getY() == this.posY) {
			if (e instanceof Node) {
				replaceNode((Node) e);
			}
			return true;
		} else {
			return false;
		}
	}
	
	void replaceNode(Node n) {
		this.direction = n.direction();
	}

	public Image getImage() {
		return this.img;
	}

	public boolean hasMask() {
		return this.hasMask;
	}
	
	public void setX(int x) {
		this.posX = x;
	}
	
	public void setY(int y) {
		this.posY = y;
	}
}