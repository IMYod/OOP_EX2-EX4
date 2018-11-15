package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Csv2kml {

	private String csvName; //name of csv file
	private StringBuilder kml = new StringBuilder(); //here we build the text of the kml file
	private final String cvsSplitBy = ","; //the character separator of the csv file
	private String[] title; //the titles of the csv file, separated
	private int CurrentLatitude, CurrentLongitude, AltitudeMeters;

	Csv2kml(String name){
		csvName = name; // in the format: "name.csv"
	}

	public void convert() {
		try (BufferedReader br = new BufferedReader(new FileReader(csvName))) 
		{
			String[] csvRow; //one row from the csv file, separated
			String line = ""; //one row from the csv file

			kml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
					"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n"); //add the open of the line
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
				kml.append(writePlacemark(csvRow));
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}	

		kml.append("</Folder>\n"+
		"</Document></kml>");//The end of the file

		write();
	}

	private StringBuilder writePlacemark(String[] line) {
		StringBuilder sb = new StringBuilder();
		sb.append("<Placemark>\n");

		sb.append("<name><![CDATA["+line[0]+"]]></name>\n");

		sb.append("<description><![CDATA[");

		int i=1;
		while(i<title.length) { //write description
			if (i!=CurrentLatitude && i!=CurrentLongitude && i!=AltitudeMeters)	
				sb.append(title[i]+": <b>"+line[i]+"</b><br/>");
			i++;
		}
		sb.append("]]></description><styleUrl>#red</styleUrl>\n");
		
		sb.append("<Point>\n");

		sb.append("<coordinates>"+line[CurrentLatitude]+", "+ //write coordinates
		line[CurrentLongitude]+", "+
		line[AltitudeMeters]+
		"</coordinates>\n"+
		"<altitudeMode>clampToGround"
		+ "</altitudeMode></Point>\n");

		sb.append("</Placemark>\n");
		
		return sb;
	}

	private void write() {
		String fileName =  csvName.substring(0, csvName.length()-3) + "kml"; //replace the end-type from csv to kml
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

		pw.write(kml.toString());
		pw.close();
		System.out.println("done!");
	}
	
	public static void main(String[] args) {
		Csv2kml first = new Csv2kml("WigleWifi_20171203085618.csv");
		first.convert();
	}
}
