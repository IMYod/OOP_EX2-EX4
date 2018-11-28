package GIS_Point;

import java.util.Date;

import File_format.GIS2Kml;
import GIS.Meta_data;
import Geom.Point3D;

public class GIS_Set_Meta_data implements Meta_data {

	protected long createdTime = Long.MAX_VALUE;
	private String fileName;
	
	public GIS_Set_Meta_data(String name) {
		fileName = name;
		Date date = new Date();
		createdTime = date.getTime();
	}
	
	public String getName( ) {
		return fileName;
	}
	
	@Override
	public long getUTC() {
		return createdTime;
	}

	@Override
	public String toString() {
		return "file name: " + fileName +"\n"
				+ "created at: " + GIS2Kml.longToKmlTime(createdTime);
	}
	
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
