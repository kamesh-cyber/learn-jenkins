package com.jenkins.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@Value("${spring.profiles.active}")
	private String environment;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("environment", environment);
		return "index";
	}

}
