package Ex3Classes;
import Geom.Point3D;

/**
 * A class that represents a "robot" with a location, 
 * orientation and ability to move (at a defined speed).
 * @author Yoav and elad.
 *
 */
public class Packman {
	
	private Point3D location;///Starts on the (0,0,0) location.
	private double speed; 
	private double radius;
	private boolean alive;
	//public ----  Where to go///
	public Packman(double speed, double radius) {
		this.speed = speed;
		this.radius = radius;
		this.alive = true;
	}
	public Point3D getLocation() {
		return location;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double getRadius() {
		return radius;
	}
	public boolean isAlive() {
		return this.alive;
	}
	
	
	
}
