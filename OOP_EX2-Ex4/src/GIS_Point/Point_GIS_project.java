package GIS_Point;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.GIS_project;
import GIS.Meta_data;

public class Point_GIS_project implements GIS_project {
	
	private Set<Point_GIS_layer> set = new HashSet<Point_GIS_layer>();
	protected project_Meta_data metaData;
	
	public Point_GIS_project(String name) {
		metaData = new project_Meta_data(name);
	}
	
	@Override
	public boolean add(GIS_layer o) {
		if (!(o instanceof Point_GIS_layer))
			return false;
		Point_GIS_layer layer = (Point_GIS_layer)o;
		if (layer.get_Meta_data().getUTC() < metaData.getUTC())
			metaData.minTime =  layer.get_Meta_data().getUTC();
		return set.add(layer);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> arg0) {
		
		for (GIS_layer layer: arg0)
			if (!(layer instanceof Point_GIS_layer))
				return false;
		boolean answer = set.addAll((Collection<? extends Point_GIS_layer>) arg0);
		if (answer)
			metaData.minTime = findMinTime();
		return answer;
	}

	@Override
	public void clear() {
		set.clear();
		metaData.minTime = Long.MAX_VALUE;
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
		return (Iterator<GIS_layer>)set.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		boolean answer = set.remove(arg0);
		if (set.isEmpty())
			metaData.minTime = Long.MAX_VALUE;
		return answer;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		boolean answer = set.removeAll(arg0);
		if (set.isEmpty())
			metaData.minTime = Long.MAX_VALUE;
		return answer;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean answer = set.retainAll(arg0);
		if (set.isEmpty())
			metaData.minTime = Long.MAX_VALUE;
		return answer;
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

	private long findMinTime() {
		long min = Long.MAX_VALUE;
		for (Point_GIS_layer layer: set)
			if (layer.metaData.getUTC() < min)
				min = layer.metaData.getUTC();
		return min;
	}
}
