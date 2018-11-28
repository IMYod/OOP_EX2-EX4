package GIS_Point;

import java.sql.Date;
import java.sql.Time;

import Coords.MyCoords;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;

public class Point_GIS_element implements GIS_element {

	private Point3D point;
	public Point_Meta_data metaData;
	
	public Point_GIS_element(Point3D _point, long time) {
		point = _point;
		metaData = new Point_Meta_data(time);
	}
	
	@Override
	public Geom_element getGeom() {
		return point;
	}

	@Override
	public Meta_data getData() {
		return metaData;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords coord = new  MyCoords();
		point = coord.add(point, vec);

	}
	
}
