package GIS;
import java.util.Set;

import GIS_Point.Point_GIS_element;
public interface GIS_layer extends Set<GIS_element>{
	public Meta_data get_Meta_data();

//	boolean add(Point_GIS_element e);

	
}
