package GIS_Point;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_element;
import GIS.Meta_data;
/**
 * This class is an Collection of a Point Gis Elements.
 * This class represents a full csv file.
 * This object will contains all the Point Gis Eelments,
 * and also his meta data. 
 * @author Elad and Yoav.
 *
 */
public class Point_GIS_layer implements GIS_layer {

	private Set<Point_GIS_element> set = new HashSet<Point_GIS_element>();
	protected GIS_Set_Meta_data metaData;
	
	/**
	 * This constructor gets the name of the csv file.
	 * @param name A string of some file.
	 */
	public Point_GIS_layer(String name) {
		metaData = new GIS_Set_Meta_data(name);
	}
	
	@Override
	public boolean add(GIS_element o) {
		return set.add((Point_GIS_element) o);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return set.addAll((Collection<? extends Point_GIS_element>) arg0); 
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
	public Iterator<GIS_element> iterator() {
		Iterator<Point_GIS_element> it = set.iterator();
		Iterator<GIS_element> itInner = new Iterator<GIS_element>() {
			
			@Override
			public GIS_element next() {
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
