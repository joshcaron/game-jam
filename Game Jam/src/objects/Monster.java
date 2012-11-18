package objects;
import java.awt.Image;
import java.util.ArrayList;

import view.*;

//Represents any Monster in the game 
public class Monster extends Entity {
	int hitPoints;
	private int direction;
	Image img;
	Image upStand;
	Image upMove;
	Image leftStand;
	Image leftMove;
	Image rightStand;
	Image rightMove;
	Image downStand;
	Image downMove;
	boolean hasMask;
	boolean moving;
	public Monster(int x, int y, Image upStand, Image upMove, Image leftStand, Image leftMove, Image rightStand, Image rightMove, Image downStand, Image downMove) {
		this.posX = x;
		this.posY = y;
		this.setDirection(-1);
		this.hasMask = false;
		this.hitPoints = 3;
		
		this.upStand = upStand;
		this.upMove = upMove;
		this.leftStand = leftStand;
		this.leftMove = leftMove;
		this.rightStand = rightStand;
		this.rightMove = rightMove;
		this.downMove = downMove;
		this.downStand = downStand;
		this.img = downStand;
		this.moving = true;
	}
	
	public void move() {
		if (this.getDirection() == 0) {
			this.moveLeft();
		}
		if (this.getDirection() == 1) {
			this.moveUp();
		}
		if (this.getDirection() == 2) {
			this.moveRight();
		}
		if (this.getDirection() == 3) {
			this.moveDown();
		}
	}

	public void moveLeft() {
		if (this.hasMask) {
			this.posX = this.posX - 40;
		} else {
			this.posX = this.posX + 1;
		}
	}
	
	public void setMoving(boolean b) {
		this.moving = b;
	}
	
	public void moveRight() {
		if (this.hasMask) {
			this.posX = this.posX + 40;
		} else {
			this.posX = this.posX + 1;
		}
	}
	public void moveUp() {
		if (this.hasMask) {
			this.posY = this.posY - 40;
		} else {
			this.posY = this.posY - 1;
		}
	}
	public void moveDown() {
		if (this.hasMask) {
			this.posY = this.posY + 40;
		} else {
			this.posY = this.posY + 1;
		}
	}
	
	public void makePlayer() {
		this.hasMask = true;
	}
	
	public boolean collide(Entity e) {
		if (e.getX() == this.posX && e.getY() == this.posY) {
			if (e instanceof Node) {
				this.replaceNode((Node) e);
			}
			return true;
		} else {
			return false;
		}
	}
	
	void replaceNode(Node n) {
		if (!this.hasMask) {
			this.setDirection(n.direction());
		}
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
	
	public boolean collideTile(ArrayList<ArrayList<Tile>> at) {
		int bottomx = (int) Math.floor(this.posX/40);
		int bottomy = (int) Math.floor(this.posY/40);
		Tile bt = at.get(bottomy).get(bottomx);
		if (bt.getType() == 9) {
			return true;
		} else if (!this.hasMask) {
			int topx = (int) Math.ceil(this.posX/40) + 1;
			int topy = (int) Math.ceil(this.posY/40) + 1;
			Tile tt = at.get(topy).get(topx);
			if (tt.getType() == 9) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void reverseDirection() {
		if (this.getDirection() == 0) {
			this.setDirection(2);
		} else if (this.getDirection() == 1) {
			this.setDirection(3);
		} else if (this.getDirection() == 2) {
			this.setDirection(0);
		} else if (this.getDirection() == 3) {
			this.setDirection(1);
		} else {
			
		}
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}