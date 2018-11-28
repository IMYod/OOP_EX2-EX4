package GIS_Point;

import java.sql.Date;
import java.sql.Time;
import java.util.TreeMap;

import GIS.Meta_data;
import Geom.Point3D;

public class Point_Meta_data implements Meta_data {

	private long firstSeen;
	private TreeMap data = new TreeMap();
	String name = ""; //change it!!!!!!!!!!!!!!
	
	public Point_Meta_data(long time) {
		firstSeen = time;
	}
	
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
