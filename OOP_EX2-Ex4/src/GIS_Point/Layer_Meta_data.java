package GIS_Point;

import java.util.Date;

import GIS.Meta_data;
import Geom.Point3D;

public class Layer_Meta_data implements Meta_data {
	
	protected long minTime = Long.MAX_VALUE;
	
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
