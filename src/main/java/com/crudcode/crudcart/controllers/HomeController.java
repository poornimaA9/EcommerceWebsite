package com.crudcode.crudcart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HomeController {
	
	@GetMapping("/home")
	public String getHomePage() {
		return "welcome to Home Page";
	}	
}
