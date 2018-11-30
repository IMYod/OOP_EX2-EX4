package File_format;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS_Point.Point_GIS_element;
import GIS_Point.Point_GIS_layer;
import GIS_Point.Point_GIS_project;
import GIS_Point.Point_Meta_data;
import Geom.Point3D;

/**
 * This class knows to deal with a point gis project or layer a transform them 
 * to a kml files.
 * 
 * @author Yoav and Elad.
 *
 */
public class GIS2Kml {
	
	private Point_GIS_project project;
	private Point_GIS_layer layer;
	private StringBuilder kml = new StringBuilder();
	private String target;
	public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
	
	/**
	 * This function converting a Point_GIS_project to a Kml file.
	 * 
	 * @param _project The project that will convert.
	 * @param _target The location the file will be. 
	 */
	public void convert(Point_GIS_project _project, String _target) {
		project = _project;
		target = _target;
		kml.setLength(0);
		kml.append(beginnigFile());
		for (GIS_layer _layer: project) { // looking for gis layers
			layer = (Point_GIS_layer) _layer;
			layer2kml(); // add one layer to the kml project file.
		}
		kml.append(endFile());
		write(project.get_set_Meta_data().getName());
	}
	/**
	 * This function converting a Point_GIS_layer to a Kml file.
	 * 	@param _layer The layer that will convert
	 *	@param _target The location of the file.
	 */
	public void convert(Point_GIS_layer _layer, String _target) {
		layer = _layer;
		target = _target;
		kml.setLength(0);
		kml.append(beginnigFile());
		layer2kml();
		kml.append(endFile());
		write(layer.get_set_Meta_data().getName());
	}
	/**
	 * This is a Auxiliary function due to the same syntax for the beginning of kml file. 
	 * @return String of the first row of kml file.
	 */
	public static String beginnigFile() {
		String open = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>";
		return open;
	}
	/**
	 * This is a Auxiliary function due to the same syntax for the beginning of kml file.
	 * @return String of the last row of kml file.
	 */
	public static String endFile() {
		String end = "</Document></kml>";
		return end;
	}
	/**
	 * This function actually making the transformation.
	 * We use this function in the convert functions.  
	 */
	private void layer2kml() {

		kml.append("<Folder><name>" + layer.get_set_Meta_data().getName() + "</name>\n"); //add the open of the line
		
		for (GIS_element element: layer) //add elements
			kml.append(element2kml(element));
		
		kml.append("</Folder>\n");//The end of the file
	}
	/**
	 * This function takes the elements from the layer and Chain them to the kml file.
	 * @param element The element we want to put in the kml file.
	 * @return StringBuilder of all the Gis elements in this layer.
	 */
	private StringBuilder element2kml(GIS_element element) {
		StringBuilder sb = new StringBuilder();
		Point_Meta_data Metadata = (Point_Meta_data) element.getData();
		Point3D point = (Point3D) element.getGeom();
		TreeMap<String, String> data = Metadata.get_data();
		
		sb.append("<Placemark>\n");

		sb.append("<name><![CDATA["+ Metadata.get_name() +"]]></name>\n");

		sb.append("<description><![CDATA[");

		for (Map.Entry<String, String> entry: data.entrySet()) //add descriptions
			sb.append(entry.getKey() +": <b>"+ entry.getValue() +"</b><br/>");
		
		sb.append("]]></description><styleUrl>#red</styleUrl>\n");
		
		sb.append("<TimeStamp>\n" +
        "<when>" + this.longToKmlTime(Metadata.getUTC()) + "</when>\n" +
      "</TimeStamp>");
		sb.append("<Point>\n");

		sb.append("<coordinates>"+point.x()+", "+ //write coordinates
		point.y()+", "+
		point.z()+
		"</coordinates>\n"+
		"<altitudeMode>clampToGround"
		+ "</altitudeMode></Point>\n");

		sb.append("</Placemark>\n");
		return sb;
	}
	/**
	 * This function write the StringBuilder to a kml file in the Target folder.
	 * @param fileName A String wuth the name of the file.
	 */
	private void write(String fileName) {
		PrintWriter pw = null;
	
		try 
		{
			pw = new PrintWriter(new File(target + "\\" + fileName + ".kml"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
	
		pw.write(kml.toString());
		pw.close();
		System.out.println("done!"); // printing in the console a "done" message.
	}
	/**
	 * This function Knows to transform a utc to a kml format.
	 * @param utc The time of Point Gis Element
	 * @return A String with a kml time format.
	 */
	public static String longToKmlTime(long utc) {		
		java.util.Date date = new Date(utc);
		return format.format(date);
	}
}
