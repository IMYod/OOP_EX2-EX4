package File_format;

import java.util.Map;
import java.util.TreeMap;

import GIS.GIS_element;
import GIS_Point.Point_GIS_element;
import GIS_Point.Point_GIS_layer;
import GIS_Point.Point_Meta_data;
import Geom.Point3D;

public class GIS_layer2Kml {

	public GIS_layer2Kml() {
		super();
	}
	
	public StringBuilder layer2kml(Point_GIS_layer layer) {
		StringBuilder kml = new StringBuilder();
		
		kml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n"); //add the open of the line
		
		for (GIS_element element: layer) //add elements
			kml.append(element2kml(element));
		
		kml.append("</Folder>\n"+
				"</Document></kml>");//The end of the file
		
		return kml;
	}
	
	private StringBuilder element2kml(GIS_element element) {
		StringBuilder sb = new StringBuilder();
		Point_Meta_data Metadata = (Point_Meta_data) element.getData();
		Point3D point = (Point3D) element.getGeom();
		TreeMap<String, String> data = Metadata.get_data();
		
		sb.append("<Placemark>\n");

		sb.append("<name><![CDATA["+ Metadata.get_name() +"]]></name>\n");

		sb.append("<description><![CDATA[");

		for (Map.Entry<String, String> entry: data.entrySet()) //add descriptions
			sb.append(entry.getValue() +": <b>"+ entry.getKey() +"</b><br/>");
		
		sb.append("]]></description><styleUrl>#red</styleUrl>\n");
		
		sb.append("<TimeStamp>\n" +
        "<when>" + this.longToKmlTime(Metadata.getUTC()) + "</when>\n" +
      "</TimeStamp>)");
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

	private String longToKmlTime(long utc) {
//		return like 2007-01-14T21:05:02Z
	}
}
