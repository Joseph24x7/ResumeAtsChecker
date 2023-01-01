package com.virtual.resume.builder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VirtualResumeController {

	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/getLinkedInDetails")
    public ResponseEntity<String> helloWorld(@RequestBody Data data) {
        return new ResponseEntity<String>("Success! You have submitted the form: "+data.getUrl(), HttpStatus.OK);
    }

}