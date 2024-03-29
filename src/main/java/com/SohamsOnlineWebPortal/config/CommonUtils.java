package com.SohamsOnlineWebPortal.config;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.SohamsOnlineWebPortal.config.constants.BaseConstants;

public class CommonUtils {
	
	/*
	 * 1 - error (red)
	 * 2 - success (green)
	 * 3 - info (black)
	 * */
	public static void AddLog(String sessionId, String log, int type) {
		String logMessage = getUTCTimeStamp()+"   "+sessionId+"   ";
		if( type == 1 ) {
			logMessage += "error    ";
		}
		
		if( type == 2 ) {
			logMessage += "success  ";
		}
		
		if( type == 3 ) {
			logMessage += "info     ";
		}
		
		logMessage += log;
		System.out.println(logMessage);
	}
	
	public static Date getUTCTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   
        SimpleDateFormat ldf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        Date d1 = null;    
        try {   
            d1 = ldf.parse( sdf.format(new Date()) );  
        }     
        catch (java.text.ParseException e) {    
            e.printStackTrace();  
            System.out.println(e.getMessage());  
        }  
        
        return d1;  
	}

	public static String DeviceType( int width ) {	
		if( width < BaseConstants.MOBILE_SCREEN_WIDTH_LIMIT )
			return "Mobile";
		if( width < BaseConstants.TABLET_SCREEN_WIDTH_LIMIT )
			return "Tablet";
		return "Desktop/Laptop";	
	}
}