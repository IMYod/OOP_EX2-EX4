package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_layer_class;
import GIS.Gis_element_Class;

public class CSV2GIS_layer {

	private String csvName; //name of csv file
	private final String cvsSplitBy = ","; //the character separator of the csv file
	private String[] title; //the titles of the csv file, separated
	private int CurrentLatitude, CurrentLongitude, AltitudeMeters;

	public GIS_layer create(String str) {
		csvName = str;
		return create();
	}

	public GIS_layer create(File f) {
		csvName = f.getName();
		return create();
	}

	private GIS_layer create() {
		GIS_layer layer = new GIS_layer_class();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvName))) 
		{
			String[] csvRow; //one row from the csv file, separated
			String line = ""; //one row from the csv file

			br.readLine(); //skip the first line on csv

			line = br.readLine();
			title = line.split(cvsSplitBy);
			for (int i=0; i<title.length; i++) {
				switch (title[i]) {
				case "CurrentLatitude":
					CurrentLatitude = i;
					break;

				case "CurrentLongitude":
					CurrentLongitude = i;
					break;

				case "AltitudeMeters":
					AltitudeMeters = i;
					break;

				default:
					break;
				}
			}

			while ((line = br.readLine()) != null) //add rows
			{
				csvRow = line.split(cvsSplitBy);
				layer.add(create(csvRow));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return layer;
	}

	private GIS_element create(String[] point) {
		GIS_element element = new Gis_element_Class();
		//TODO
		//we need to convert row of csv by the title, and create the element
		return element;
	}

}
