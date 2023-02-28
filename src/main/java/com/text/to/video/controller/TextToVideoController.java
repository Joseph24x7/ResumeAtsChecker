package com.text.to.video.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextToVideoController {

    @PostMapping("/")
    public String helloWorld(@RequestBody String data) {
        return "Hellow World";
    }

}