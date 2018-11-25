package Coords;
import java.util.Arrays;
import Geom.Point3D;
/**
 * This class implements coords_converter , Which help in building the geographical
 *  infrastructure of points in space, finding distances computation degrees of the points on the earth 
 *  and so on...
 * 
 * @author Yoav & elad
 *
 */
public class MyCoords implements coords_converter {

	private final double EarthRadius = 6371000;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double diffLatRadian = Math.asin(local_vector_in_meter.x()/EarthRadius);
		double Lat = gps.x() + diffLatRadian*180 / Math.PI;

		double lonNormal = Math.cos(gps.x()*Math.PI/180);
		double diffLonRadian = Math.asin(local_vector_in_meter.y()/(EarthRadius*lonNormal));
		double Lon = gps.y() + diffLonRadian*180 / Math.PI;

		double Alt = gps.z() + local_vector_in_meter.z();

		return new Point3D(Lat, Lon, Alt);
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D vector = vector3D(gps0, gps1);
		return vector.distance3D(0, 0, 0);	
	}
	
	public double distance2D(Point3D gps0, Point3D gps1) {
		Point3D vector = vector3D(gps0, gps1);
		return vector.distance2D(new Point3D(0,0,0));	
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double diffLat = gps1.x()-gps0.x();
		double diffLon = gps1.y()-gps0.y();
		double diffAlt = gps1.z()-gps0.z();
		double lonNormal = Math.cos(gps0.x()*Math.PI/180);

		double diffLatRadian = diffLat*Math.PI/180;
		double diffLonRadian = diffLon*Math.PI/180;
		double diffLatMeters = Math.sin(diffLatRadian)*EarthRadius;
		double diffLonMeters = Math.sin(diffLonRadian)*EarthRadius*lonNormal;

		return new Point3D(diffLatMeters, diffLonMeters, diffAlt);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) 
	{	
		 
		double azimuth;
		
		Point3D vectorNorth = this.vector3D(gps0, new Point3D(gps0.x()+0.01, gps0.y(), 0));
		Point3D vector = this.vector3D(gps0, gps1);
		double skalarMultuply2D = vectorNorth.x()*vector.x()+ vectorNorth.y()*vector.y();
		double cosAzimuth = skalarMultuply2D/(vectorNorth.distance2D(new Point3D(0,0,0))*vector.distance2D(new Point3D(0,0,0)));
		azimuth = Math.toDegrees(Math.acos(cosAzimuth));
		
		double diffLat = gps1.x()-gps0.x();
		double diffLon = gps1.y()-gps0.y();
		double diffLatRadian = Math.toRadians(diffLat);
		double diffLonRadian = Math.toRadians(diffLon);
		
//		azimuth = Math.toDegrees(Math.atan(diffLon/diffLat));
//		
//		if(diffLon > 0) {
//			if (diffLat < 0)
//				azimuth = 180 - azimuth;
//		}
//		else 
//		{ 
//			if(diffLat < 0) 
//				azimuth += 180;
//			else 						
//				azimuth = 360 - azimuth;
//		}		
//		
//		azimuth = 90 - Math.toDegrees(Math.atan(diffLon/diffLat));
		
		double diffAlt = gps1.z()-gps0.z();
		
		double distance3D = this.distance3d(gps0, gps1);
		double elevation = Math.toDegrees(Math.asin(diffAlt/distance3D));
		
		double distance2D = this.distance2D(gps0, gps1);
		return new double[]{azimuth, elevation, distance2D}; 
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		return (p.x()<=90 && p.x()>=-90 &&
				p.y()<=1800 && p.y()>=-1800 &&
				p.z()>=-450);
	}


}
