package it.italiangrid.portal.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.portlet.RenderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;


/**
 * This class is the controller that authenticate the user and display some
 * information about the downloaded proxyes.
 * 
 * @author dmichelotto
 * 
 */

@Controller("statisticsController")
@RequestMapping(value = "VIEW")
public class StatisticsController {
	

	/**
	 * Logger of the class DownloadCertificateController. TODO to substitute it
	 * with slf4j.
	 */
	private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

	

	/**
	 * Method for render home.jsp page.
	 * 
	 * @return the page file name.
	 */
	@RenderMapping
	public String showLogin(RenderResponse response) {
		log.info("rendering");
		return "home";
	}
	
	@ModelAttribute("statistics")
	public List<String> getStatistics(){
		
		String file = getFileFormProperty();
		try {
			
			//List<String> tmp = new ArrayList<String>();
			List<String> statistics = new ArrayList<String>();
			
			FileInputStream fstream;
			
			fstream = new FileInputStream(file);
			
	        DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String strLine;
	        while ((strLine = br.readLine()) != null)
	        	statistics.add(strLine);
	            //tmp.add(strLine);
	
	        in.close();
	        
	        /*List<String> statistics = new ArrayList<String>();
	        
	        if (tmp.size()>25){
	        	int step = tmp.size()/25;
	        	int count=0;
	        	for (String string : tmp) {
					if((count%step)==0)
						statistics.add(string);
					count++;
				}
	        }else{
	        	return tmp;
	        }*/
	        
	        return statistics;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private String getFileFormProperty(){
		
		String contextPath = StatisticsController.class.getClassLoader().getResource("").getPath();
		
		log.info("dove sono:" + contextPath);
		
		FileInputStream inStream;
		try {
			inStream = new FileInputStream(contextPath + "/content/statistics.properties");
		

			Properties prop = new Properties();
		
			prop.load(inStream);
		
			inStream.close();
			
			return prop.getProperty("statistcs.file");
		
		} catch (IOException e) {
			log.error("Problem with file statistics.properties");
			e.printStackTrace();
		} 
		
		return null;
		
	}

	

}
