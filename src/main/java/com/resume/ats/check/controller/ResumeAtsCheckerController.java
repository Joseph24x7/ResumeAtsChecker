package com.resume.ats.check.controller;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResumeAtsCheckerController {

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
	    PDDocument document = PDDocument.load(file.getInputStream());
	    PDFTextStripper stripper = new PDFTextStripper();
	    String content = stripper.getText(document);
	    document.close();
	    return content;
	}

}