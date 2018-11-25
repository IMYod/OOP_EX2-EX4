package GIS_Point;

import java.sql.Time;

import GIS.Meta_data;
import Geom.Point3D;

public class Point_Meta_data implements Meta_data {

	Time firstSeen;
	Point3D orientation = new Point3D(0,0,0);
	
	String[] data = new String[7];
	String MAC = data[0];
	String SSID = data[1];
	String AuthMode = data[2];
	String Channel = data[3];
	String RSSI = data[4];
	String AccuracyMeters = data[5];
	String Type = data[6];
	
	@Override
	public long getUTC() {
		return firstSeen.getTime();
	}
	
	@Override
	public String toString() {
		return("MAC: " + MAC +
				" SSID: " + SSID +
				" AuthMode: " + AuthMode +
				" Channel: " + Channel +
				" RSSI: " + RSSI +
				" AccuracyMeters: " + AccuracyMeters +
				" Type: " + Type);
	}

	@Override
	public Point3D get_Orientation() {
		return orientation;
	}

}
