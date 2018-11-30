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

/**
 * This class runs the convert for each object(Point_GIS_project,Point_GIS_layer)
 *  to kml file.
 * 
 * @author Elad and Yoav
 *
 */
public class MultiCSV {

	//	private GIS_project answer = new GIS_project();
	private Point_GIS_project project;
	private File folder;
	private CSV2GIS_layer GISconvertor = new CSV2GIS_layer();
	private Csv2Kml KMLconvertor = new Csv2Kml();

	//https://stackoverflow.com/questions/10780747/recursively-search-for-a-directory-in-java

	/**
	 * This function create a Point_GIS_project object from a folder. 
	 * @param folderName The folder that we will make from her a Point_GIS_project.
	 * @return Point_GIS_project object.
	 */
	public Point_GIS_project folder2GIS_project(String folderName) {
		folder = new File(folderName);
		project = new Point_GIS_project(folder.getName());
		searchDirectory(folder, true);
		return project;
	}
	/**
	 * This function convert csv files in some folder to a kml file.
	 * @param folderName The name of the folder we will convert.
	 */
	public void convert2kml(String folderName) {
		folder = new File(folderName);
		searchDirectory(folder, false);
	}
	/**
	 * This function looking for a Point_GIS_layer to add to the project
	 * @param directory The folder to look for.
	 * @param GIS GIS true - create GIS, false - create kml.
	 */
	private void searchDirectory(File directory, boolean GIS) {//GIS true - create GIS, false - create kml
		if(directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File file : files)
				searchDirectory(file, GIS);
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
	/**
	 *This function checks if the file is csv. 
	 * @param f Name of the file.
	 * @return true if the file is csv, else returns false.
	 */
	private boolean isCSV(File f) {
		String name = f.getName();
		return (name.substring(name.length()-3).equals("csv"));
	}
	
//	public static void main(String[] args) {
//		
//		MultiCSV input = new MultiCSV();
//		String path = "C:\\Users\\נביאי\\Desktop\\data";
//		Point_GIS_project project = input.folder2GIS_project(path);
//		System.out.println(project.get_set_Meta_data());
//		
//		GIS2Kml convertor = new GIS2Kml();
//		convertor.convert(project, path);
//	}

}
