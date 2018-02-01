package gen;

import java.io.File;

/**
 * @author Crunchify.com
 * Updated: 03/20/2016 - added code to narrow border size 
 */
 
public class Generator  {
  
	public static void main(String[] args) {
		String imgPath = "C:/Test/";
		String xmlPath = "C:/TestOut/innerlabels.xml";
		xmlRead xmlr = new xmlRead(xmlPath);
		
		GeneratorFromXML gx = new GeneratorFromXML(imgPath,xmlr.getLabels());
		gx.makeImages();
	}
}