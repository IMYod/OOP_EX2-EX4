package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {

	private final double EarthRadius = 6371000;
	
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		
		
		double diffLonRadian = Math.asin(local_vector_in_meter.y()/EarthRadius);
		double Lon = diffLonRadian*180 / Math.PI;
		
		double Alt = gps.z() + local_vector_in_meter.z();
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double diffLat = gps1.x()-gps0.x();
		double diffLon = gps1.y()-gps0.y();
		double diffAlt = gps1.z()-gps0.z();
		double lonNormal = Math.cos(gps0.x()*Math.PI/180);
		
		double diffLatRadian = diffLat*Math.PI/180;
		double diffLonRadian = diffLon*Math.PI/180;
		double diffLatMeters = Math.sin(diffLatRadian)*EarthRadius;
		double diffLonMeters = Math.sin(diffLonRadian)*EarthRadius;
		
		return Math.sqrt(diffLatMeters*diffLatMeters + diffLonMeters*diffLonMeters + diffAlt*diffAlt);
		
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		return (p.x()<=180 && p.x()>=-180 &&
				p.y()<=90 && p.y()>=90 &&
				p.z()>=-450);
	}

}
