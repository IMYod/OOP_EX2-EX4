package GIS_Point;

import java.util.Date;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import GIS.Meta_data;
import Geom.Point3D;

public class Layer_Meta_data implements Meta_data {
	
	protected long minTime = Long.MAX_VALUE;
	private String fileName;
	
	public Layer_Meta_data(String name) {
		fileName = name;
	}
	
	@Override
	public long getUTC() {
		return minTime;
	}

	@Override
	public String toString() {
		return "file name: " + fileName;
	}
	
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
