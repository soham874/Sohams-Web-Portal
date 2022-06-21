package com.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.StateResponse;
import com.model.VisitorInformation;
import com.services.GoogleServices;

@Controller
@ComponentScan
@Component
public class GoogleController {

	@RequestMapping(value = "/updateBrowserData", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetAcademicData( @RequestBody String UserData ) throws ParseException{
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonRequestBody = (JSONObject) jsonParser.parse(UserData);
		
		VisitorInformation visitorInformation = new VisitorInformation(
				jsonRequestBody.get("browser").toString(), 
				Integer.parseInt(jsonRequestBody.get("height").toString()), 
				Integer.parseInt(jsonRequestBody.get("width").toString()), 
				jsonRequestBody.get("timeStamp").toString());
		
    	StateResponse ControllerLayerResponse;
    	
    	try {	// try to get response from service layer 
    		    		
    		StateResponse ServicelerLayerResponse = GoogleServices.Post(visitorInformation.toString());
    		
    		ControllerLayerResponse = new StateResponse(
					ServicelerLayerResponse.getStatus(), 
					ServicelerLayerResponse.getBody(), 
					ServicelerLayerResponse.getMessage()
        	);
    		
    	}catch( Exception e ) { // Service layer refuses to respond 
    		
    		StringWriter sw = new StringWriter();
        	PrintWriter pw = new PrintWriter(sw);
        	
        	e.printStackTrace(pw);
        	
        	ControllerLayerResponse = new StateResponse(
        			500, 
        			sw.toString(), 
        			"Backend error, unable to reach Service Layer"
        	);
    	}
    	
    	// return the response
        System.out.println("Response from Google Controller layer for submitting browser details");
        System.out.println(ControllerLayerResponse.toString());
    	
    	return ControllerLayerResponse.toString();
	}
}
