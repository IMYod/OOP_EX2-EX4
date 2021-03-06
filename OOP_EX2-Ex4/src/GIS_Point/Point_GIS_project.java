package GIS_Point;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.GIS_project;
import GIS.Meta_data;
/**
* This class is an Collection of a Point Gis Elements.
 * This class represents a full csv file.
 * This object will contains all the Point Gis Eelments,
 * and also his meta data. 
 * @author Elad and yoav.	
 */
public class Point_GIS_project implements GIS_project {
	
	private Set<Point_GIS_layer> set = new HashSet<Point_GIS_layer>();
	protected GIS_Set_Meta_data metaData;
	/**
	 * This constructor set the name and calling to the project meta data constructor.
	 * @param name A string with the name of the Point Gis Element.
	 */
	public Point_GIS_project(String name) {
		metaData = new project_Meta_data(name);
	}
	
	@Override
	public boolean add(GIS_layer o) {
		return set.add((Point_GIS_layer) o);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		return set.addAll((Collection<? extends Point_GIS_layer>) arg0); 
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return set.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		Iterator<Point_GIS_layer> it = set.iterator();
		Iterator<GIS_layer> itInner = new Iterator<GIS_layer>() {
			
			@Override
			public GIS_layer next() {
				return it.next();
			}
			
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}
		};
		return itInner;
	}

	@Override
	public boolean remove(Object arg0) {
		return set.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return set.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return set.retainAll(arg0);
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return set.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		return metaData;
	}
	
	public GIS_Set_Meta_data get_set_Meta_data() {
		return metaData;
	}
}
