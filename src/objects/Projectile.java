package objects;


//Represents the abstract class of any projectile thrown in game
public abstract class Projectile extends Entity {
	abstract boolean collide(Entity e);
}

//Represents the mask as a projectile
class Mask extends Projectile {
	public Mask(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	boolean collide(Entity e) {
		return e.posX == this.posX && e.posY == this.posY;
	}
}