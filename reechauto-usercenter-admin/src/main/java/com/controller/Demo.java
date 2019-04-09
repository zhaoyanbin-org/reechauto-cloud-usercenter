package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.http.ResponseData;



@Controller
public class Demo {

	
	
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String hello4() {
		return "home.html";
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String hello5() {
		return "index.html";
	}
	
	
	
}
