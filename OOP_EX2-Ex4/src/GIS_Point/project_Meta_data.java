package GIS_Point;

import GIS.Meta_data;
import Geom.Point3D;
/**
 * This class is to set a meta data to a Project layer.
 * @author Elad an Yoav
 *
 */
public class project_Meta_data extends GIS_Set_Meta_data {
	/**
	 * This function set the meta data to this project
	 * @param name	A string with the name of the project.
	 */
	public project_Meta_data(String name) {
		super(name);
	}
}
