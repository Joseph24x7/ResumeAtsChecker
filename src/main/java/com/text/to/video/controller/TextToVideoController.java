package com.text.to.video.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.text.to.video.service.NlpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TextToVideoController {

	private final NlpService nlpService;

	@PostMapping("/")
	public List<String> helloWorld(@RequestBody String data) {
		List<String> keywords = nlpService.getKeywords(data);
		return keywords;
	}

}