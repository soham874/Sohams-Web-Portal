package com.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.StateResponse;
import com.services.*;

@Controller
@ComponentScan
@Component
public class WebpageController {
	
	// Displaying the initial users list.
    @RequestMapping("/temp")
    public String ViewPage(Model m) throws ParseException {
    	System.out.println("Coming through 'viewpage' mapping of com controller");
    	//m.addAttribute("leetcodeJSON", GetLeetCodeData());
        return "ViewPage";
    }

    @RequestMapping(value = "/receiveLeetCodeData", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody StateResponse GetLeetCodeData() throws org.json.simple.parser.ParseException, IOException {
    	
    	return LeetcodeService.getProfileData("soham874");
    	
    }
}
