package GIS_Point;
import java.sql.Date;
import java.sql.Time;
import java.util.TreeMap;

import GIS.Meta_data;
import Geom.Point3D;

/**
 * This class holds all the meta data of Point gis Element.
 * @author Yoav and Elad.
 *
 */
public class Point_Meta_data implements Meta_data {

	private long firstSeen;
	private TreeMap data = new TreeMap();//This holds what is non a Coordinates of the point
	String name = ""; 
	/**
	 * This is a constructor of meta data.
	 * @param time A format of the time.
	 */
	public Point_Meta_data(long time) {
		firstSeen = time;
	}
	/**
	 * This function add the data to the TreeMap, for the Point meta data.
	 * @param key The name of the key
	 * @param value The value in this time.
	 * @return
	 */
	public boolean addData(Object key, Object value) {
		if (name == "")
			name = value.toString();
		if (data.containsKey(key))
			return false;
		data.put(key, value);
		return true;
	}
	
	@Override
	public long getUTC() {
		return firstSeen;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	public String get_name() {
		return name;
	}
	
	public TreeMap get_data() {
		return data;
	}

}
