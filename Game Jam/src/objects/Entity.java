package objects;

import java.awt.Image;

public abstract class Entity {
	protected double posX;
	protected double posY;
	protected Image img;
	public double getX() {
		return this.posX;
	}
	
	public double getY() {
		return this.posY;
	}
	
	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void moveUp();
	public abstract void moveDown();
}
