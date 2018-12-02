package Ex3Classes;
import Geom.Point3D;

/**
 * Class that represents a "target" in a known geographic location (without movement).
 * 
 * @author Yoav and Elad.
 *
 */
public class Fruit {
	
	private Point3D location;
	private String fruitName;
	public boolean alive;
	
	public Fruit (Point3D location,String name) 
	{
		this.location = location;
		fruitName =name;
		alive=true;
	}

	public String getFruitName() {
		return fruitName;
	}

	public boolean isAlive() {
		return alive;
	}

	public Point3D getlocation() {
		return location;
	}
	public String toString() 
	{
		return ("The location is of the "+fruitName+" is "+location.toString()+".");
	}
}
