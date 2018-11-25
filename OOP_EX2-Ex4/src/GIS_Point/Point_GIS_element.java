package GIS_Point;

import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;

public class Point_GIS_element implements GIS_element {

	private Point3D point;
	Point_Meta_data metaData;
	
	public Point_GIS_element(Point3D _point) {
		point = _point;
	}
	
	@Override
	public Geom_element getGeom() {
		return point;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}

}
