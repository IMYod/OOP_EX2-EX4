package File_format;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Meta_data;

public class MultiCSV {

	//	private GIS_project answer = new GIS_project();
	private GIS_project project;
	private File folder;
	private CSV2GIS_layer GISconvertor = new CSV2GIS_layer();
	private Csv2kml KMLconvertor = new Csv2kml();

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
				project.add(GISconvertor.create(directory));
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
	
	public GIS_layer Csv2GIS_layer(File f) {
		return GISconvertor.create(f);
	}

}
