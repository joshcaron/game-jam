package objects;

//Represents the node that Monsters move between
public class Node extends Entity {
	int type;
	
	public Node(int i, int x, int y) {
		this.type = i;
		this.posX = x;
		this.posY = y;		
	}
	
	int direction() {
		return this.type;
	}
	
	public double getX() {
		return this.posX;
	}
	
	public double getY() {
		return this.posY;
	}

	public void moveLeft() {
	}
	public void moveRight() {
	}
	public void moveUp() {
	}
	public void moveDown() {
	}
}