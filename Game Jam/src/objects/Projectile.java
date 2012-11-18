package objects;


//Represents the abstract class of any projectile thrown in game
public abstract class Projectile extends Entity {
	abstract boolean collide(Monster m);
}
