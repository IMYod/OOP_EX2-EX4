package File_format;

import java.io.File;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;

public class MultiCSV {

	private GIS_project answer;

	//https://stackoverflow.com/questions/10780747/recursively-search-for-a-directory-in-java

	private boolean isCSV(File f) {
		return true;	
	}

	private GIS_layer Csv2GIS(File f) {
		return null;
	}

	private void convert(String directory) {
		File dir = new File(directory);
		findDirectory(dir);
	}

	private void findDirectory(File directory) {
		if(directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File file : files)
				findDirectory(directory);
		}
		else if(isCSV(directory))
			answer.add(Csv2GIS(directory));
	}
}
