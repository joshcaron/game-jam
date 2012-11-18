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
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
}