package File_format;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Meta_data;
import GIS_Point.Point_GIS_layer;
import GIS_Point.Point_GIS_project;

public class MultiCSV {

	//	private GIS_project answer = new GIS_project();
	private GIS_project project;
	private File folder;
	private CSV2GIS_layer GISconvertor = new CSV2GIS_layer();
	private Csv2Kml KMLconvertor = new Csv2Kml();

	//https://stackoverflow.com/questions/10780747/recursively-search-for-a-directory-in-java

	public GIS_project folder2GIS_project(String folderName) {
		folder = new File(folderName);
		findDirectory(folder, true);
		return project;
	}
	
	public void convert2kml(String folderName) {
		folder = new File(folderName);
		findDirectory(folder, false);
	}

	private void findDirectory(File directory, boolean GIS) {//GIS true - create GIS, false - create kml
		if(directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File file : files)
				findDirectory(file, GIS);
		}
		else if(isCSV(directory)) {
			if (GIS) {
				project.add((Point_GIS_layer)GISconvertor.create(directory));
			}
			else {
				System.out.println("converting: " + directory.getAbsolutePath());
				KMLconvertor.convert(directory.getAbsolutePath());
			}
		}
	}

	private boolean isCSV(File f) {
		String name = f.getName();
		return (name.substring(name.length()-3).equals("csv"));
	}
	
	public static void main(String[] args) {
		MultiCSV input = new MultiCSV();
		String from = "E:\\yoav\\מדעי המחשב\\סמסטר א\\מונחה עצמים\\מטלה2\\Ex2";
		Point_GIS_project project = (Point_GIS_project) input.folder2GIS_project(from);
		System.out.println(project);
	}

}
