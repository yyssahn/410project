package ca.ubc.cpsc310.project.TreeFinder.server;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class XMLParser {
	public static void parseXML() throws IOException{
		URL url = new URL("http://www.ugrad.cs.ubc.ca/~h7r8/xml_street_trees.zip");
		String fname = "StreetTrees_WestPointGrey.xml";
		
		URLConnection connection = url.openConnection();
		connection.setConnectTimeout(0);
		ZipInputStream zstream = new ZipInputStream(connection.getInputStream());
		ZipEntry zentry = zstream.getNextEntry();
		
		System.out.println("Searching for xml file: \n");
		
		while(zentry != null){
			System.out.println("File Name: " + zentry);
			zentry = zstream.getNextEntry();
			
			if(zentry.getName().equals(fname)){
				System.out.println("\n" + zentry.getName() + " found mother fucker! \n");
				break;
			}
		}
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			TreeParser handler = new TreeParser();
			parser.parse(zstream, handler);			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		zstream.close();
	}
}