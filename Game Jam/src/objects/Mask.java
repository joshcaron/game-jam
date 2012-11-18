package objects;

import java.awt.Image;
import java.awt.Toolkit;


//Represents the mask as a projectile
public class Mask extends Projectile {
	Monster m;
	boolean moving;
	int direction;
	
	public Mask(double x, double y, Image img) {
		this.posX = x;
		this.posY = y;
		this.img = img;
		this.moving = false;
	}
	
	public Mask(double x, double y, String imgPath) {
		this.posX = x;
		this.posY = y;
		this.img = Toolkit.getDefaultToolkit().createImage(imgPath);
		this.moving = false;
	}
	
	static boolean inRange(double x1, double y1, double x2, double y2) {
		double minX = x2;
		double minY = y2;
		double maxX = x2 + 40;
		double maxY = y2 + 40;
		return (x1 > minX && x1 < maxX) && (y1 > minY && y1 < maxY);
	}
	
	void setMonster(Monster m) {
		this.m = m;
	}
	
	public boolean collide(Monster m) {
		return Mask.inRange(this.posX, this.posY, m.getX(), m.getY());
	}
	
	public void moveRight() {
		this.direction = 2; 
		this.posX = this.posX + 40;
	}
	
	public void moveLeft() {
		this.direction = 0;
		this.posX = this.posX - 40;
	}
	
	public void moveUp() {
		this.direction = 1;
		this.posY = this.posY - 40;
	}
	
	public void moveDown() {
		this.direction = 3;
		this.posY = this.posY + 40;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void boomerang(int i) {
		this.moving = true;
		if (i == 0) {
			this.moveLeft();
		} else if (i == 1) {
			this.moveUp();
		} else if (i == 2) {
			this.moveRight();
		} else if (i == 3) {
			this.moveDown();
		}
		
		if ((Math.abs(this.posX - m.getX()) > 120) || ((Math.abs(this.posY - m.getY()) > 120))) {
			this.m.hasMask = true;
			this.posX = this.m.getX();
			this.posY = this.m.getY();
			this.moving = false;
		}
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public boolean isMoving() {
		return this.moving;
	}
	
	public boolean isOnPlayer() {
		return this.posX == this.m.getX() && this.posY == this.m.getY();
	}
	
	public void setX(double x) {
		this.posX = x;
	}
	
	public void setY(double y) {
		this.posY = y;
	}
}