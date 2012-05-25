package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class upload {
	
	
	public ImageIcon xii;
	public Image ximage;
	public BufferedImage xbuf;
	String line;
	String XMLDATA;
	public Robot xbot;
	public Rectangle caparea;
	
	public upload(int initx, int inity, int w, int h) throws IOException{
		
		// rectangle object created from the user maouse drag
		caparea = new Rectangle(initx, inity, w, h);
		
		//create a new ROBOT
			//Robot is the object that will get the screen image.
		try {
			xbot  = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//read the screen capture into a buffered image object
		xbuf = xbot.createScreenCapture(caparea);

		
		// Creates Byte Array from picture
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(xbuf, "png", baos);
		
		//--------- IMGUR API INTERACTION------------------//
		//explanations and examples available from 
		//http://api.imgur.com/
		
		
		URL url = new URL("http://api.imgur.com/2/upload");

		//encodes picture with Base64 and inserts api key
		String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encode(baos.toByteArray()), "UTF-8");
		data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("4ef87c70129ead8513e62baecfec95e4", "UTF-8");

		// opens connection and sends data
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();
		
		
		// Get the response
	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    
	    while ((line = rd.readLine()) != null) {
	    	line = rd.readLine();
	    	
	    	//this line dumps the XML data/string into a variable "XMLDATA" 
	    	XMLDATA = line;
	        
	    }
	    
	    //outputting xml to a file for reading later
	    
	    try {
	    	            FileWriter outFile = new FileWriter( "SCREENSHOT.xml");
	    	            PrintWriter out = new PrintWriter(outFile);

	    	       
	    	            // Write text to file
	    	              out.println(XMLDATA);

	               out.close();
	    	      } catch (IOException e){
	    	          e.printStackTrace();
	          }
	    wr.close();
	    rd.close();

	}
	
	public String getxmldata(){
		return XMLDATA;
	}

	}



