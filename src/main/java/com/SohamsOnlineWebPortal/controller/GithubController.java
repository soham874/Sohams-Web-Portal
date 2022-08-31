package com.SohamsOnlineWebPortal.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SohamsOnlineWebPortal.middleware.CommonUtils;
import com.SohamsOnlineWebPortal.model.StateResponse;
import com.SohamsOnlineWebPortal.services.*;

@Controller
@ComponentScan
@Component
@RestController
public class GithubController {
	
    //@RequestMapping(value = "/receiveGithubData", method = RequestMethod.GET, produces = "application/json")
    @GetMapping(value = "/receiveGithubData",produces = "application/json")
    public String GetGithubData(){
    	
    	StateResponse ControllerLayerResponse;
    	
    	try {	// try to get response from service layer 
    		
    		StateResponse ServicelerLayerResponse = GithubService.getProfileData("soham874");
    		
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
        int status = ControllerLayerResponse.getStatus();
        if( status >= 200 && status < 400 ) {
        	CommonUtils.AddLog("Github information fetched successfully",2);
        }else {
        	CommonUtils.AddLog("Response from Github Controller layer ->\n"+ControllerLayerResponse.toString(),1);
        }
     
    	return ControllerLayerResponse.toString();
    	
    }
}