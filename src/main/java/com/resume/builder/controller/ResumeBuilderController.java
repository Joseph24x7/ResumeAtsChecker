package com.resume.builder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResumeBuilderController {

	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getSuccessMessage")
    public ResponseEntity<String> helloWorld(@RequestBody String url) {
		System.out.println("hello wordl");
        return new ResponseEntity<String>("Success! You have submitted the form: "+url, HttpStatus.OK);
    }

}