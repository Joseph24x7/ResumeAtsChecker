package com.resume.ats.check.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ScanPdfService {

	public String scanPdfFromFile(MultipartFile file) throws IOException {
		
		PDDocument document = PDDocument.load(file.getInputStream());
	    PDFTextStripper stripper = new PDFTextStripper();
	    String content = stripper.getText(document);
	    content = content.toLowerCase();
	    document.close();
		return content;
		
	}

}
