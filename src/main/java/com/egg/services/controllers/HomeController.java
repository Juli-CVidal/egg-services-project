package com.egg.services.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping("/sign_up")
	public String signUp(ModelMap model) {
		model.put("form_name", "sign-up-form");
		return "form-basic.html";
	}
}
