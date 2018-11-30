package File_format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS_Point.Point_GIS_element;
import GIS_Point.Point_GIS_layer;
import Geom.Point3D;

/**
 * This class knows how to transform a csv file to 
 * GIS_layer, That consist a Points elements from the csv file.
 * 
 * 
 * @author Yoav adn Elad.
 *
 */
public class CSV2GIS_layer {

	private File file;
	private String csvName; //name of csv file
	private final String cvsSplitBy = ","; //the character separator of the csv file
	private String[] title; //the titles of the csv file, separated
	private int CurrentLatitude, CurrentLongitude, AltitudeMeters, timeIndex;
	public final static DateFormat format = new SimpleDateFormat("dd-MM-yyyy' 'hh:mm:ss");

	public GIS_layer create(String fileName) {
		file = new File(fileName);
		
		return create();
	}

	public GIS_layer create(File _file) {
		file = _file;
		return create();
	}
/**
 * This private function sends to the Gis_Element use the GIS_element create function
 * that any row in the csv file transform to GIS_element and push him to the layer. 
 * 
 * @return layer that containing the csv file. 
 */
	private GIS_layer create() {
		csvName = file.getName();
		String name = csvName.substring(0, csvName.length()-4); //delete the end-type ".csv"
		GIS_layer layer = new Point_GIS_layer(name);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) 
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

				case "FirstSeen":
					timeIndex  = i;

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
/**
 * This function get a row from csv file, which represents a GIS_element .
 * And knows to return a Gis element object.
 * @param points An array that represents a csv row.
 * @return Gis element object.
 */
	private GIS_element create(String[] points) {

		double lat = Double.parseDouble(points[CurrentLatitude]);
		double lon = Double.parseDouble(points[CurrentLongitude]);
		double alt = Double.parseDouble(points[AltitudeMeters]);
		long time = this.stringToTime(points[timeIndex]); // Makes it to a long time format.

		Point3D elemntPoint = new Point3D(lat, lon, alt);
		Point_GIS_element element = new Point_GIS_element(elemntPoint, time); 

		for (int i=0; i<points.length; i++) {  // looking for the meta data.
			if (i != CurrentLatitude && i != CurrentLongitude && i != AltitudeMeters && i != timeIndex)
				element.metaData.addData(title[i], points[i]); //add a meta data to the element.
		}

		return element;
	}
/**
 * This function makes a string that represent a data dnd time to 
 * an Noun of long object.
 * @param s Time and date
 * @return  A represent in a number to the time date string. 
 */
	
	protected static long stringToTime(String s) {
		try {
			java.util.Date date = format.parse(s);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (long)0;
	}
}
