package main;

/*
	XML PARSER FOR THE API RETURN DATA

	its purpose built for this file "screenshots.XML"
	could have used a library but wanted to be a bit more thoughtful
*/

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//these are standard libraries
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XMLparser {
	
	public String ImageLink;
	public String keycode;
	public boolean autocopy = false;
	public String autocopystring;
	

	
	
	public XMLparser(String filepath) throws SAXException, IOException, ParserConfigurationException{
		File xmlfile = new File(filepath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlfile);
		doc.getDocumentElement().normalize();

		if(filepath ==  "SCREENSHOT.xml"){
		NodeList nList = doc.getElementsByTagName("links");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			      Element eElement = (Element) nNode;
			      
			      ImageLink = getTagValue("original", eElement);
			      
			      
	 
			   }
			}
		}
		
		if(filepath == ( "config.xml")){
		NodeList nList = doc.getElementsByTagName("values");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			      Element eElement = (Element) nNode;
			      
			      keycode = getTagValue("keychar", eElement);
			      
			      autocopystring = getTagValue("autocopy", eElement);
			      System.out.println( "config.xml");
			      

			   }
			}
		}
		

		
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	  }
	
	public String getlink(){
		return ImageLink;
	}
	
	public String getcapchar(){
		
		return keycode;
	}
	
	public boolean getcopysetting(){
		

		
	      if(autocopystring.equals("false")){
	    	  autocopy = false;
	      }

	      
	      if(autocopystring.equals("true")){
	    	  autocopy = true;
	      }

		return autocopy;
	}
	
}
