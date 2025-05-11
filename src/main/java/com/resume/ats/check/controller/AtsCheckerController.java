package com.resume.ats.check.controller;

import com.resume.ats.check.utils.FileTextExtractor;
import com.resume.ats.check.utils.KeywordMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AtsCheckerController {

	@PostMapping("/analyze")
	public ResponseEntity<Map<String, Object>> analyzeResumeAndJD(
			@RequestParam("resume") MultipartFile resumeFile,
			@RequestParam("jobDescription") MultipartFile jdFile) {

		try {
			String resumeText = FileTextExtractor.extractText(resumeFile);
			String jdText = FileTextExtractor.extractText(jdFile);

			Map<String, Object> result = KeywordMatcher.calculateMatch(resumeText, jdText);
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			Map<String, Object> error = new HashMap<>();
			error.put("error", "Error processing files: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

	@GetMapping("/{path:[^\\.]*}")
	public String redirect() {
		return "forward:/index.html";
	}
}
