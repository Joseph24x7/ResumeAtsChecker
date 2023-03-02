package com.resume.ats.check.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResumeAtsCheckerController {

	@PostMapping("/")
	public String helloWorld(@RequestBody String data) {
		return data;
	}

}