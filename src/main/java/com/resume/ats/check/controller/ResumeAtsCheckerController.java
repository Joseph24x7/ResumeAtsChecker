package com.resume.ats.check.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resume.ats.check.service.KeywordExtractorService;
import com.resume.ats.check.service.ScanPdfService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ResumeAtsCheckerController {
	
	private final KeywordExtractorService keywordExtractorService;
	private final ScanPdfService scanPdfService;

	@PostMapping("/upload")
	public ATSDetail uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc) throws IOException{
		
		ATSDetail atsDetail=new ATSDetail();
		
	    String pdfContent = scanPdfService.scanPdfFromFile(file);
	    
	    atsDetail.setTotalKeywords(keywordExtractorService.extractKeywords(desc));

	    List<String> unmatchedKeywords = new ArrayList<>();
	    List<String> matchedKeywords = new ArrayList<>();
	    
	    for (String keyword : atsDetail.getTotalKeywords()) {
	        if (pdfContent.contains(keyword)) {
	            matchedKeywords.add(keyword);
	        }else {
	        	unmatchedKeywords.add(keyword);
	        }
	    }
	    
	    atsDetail.setMatchedKeywords(matchedKeywords);
	    atsDetail.setUnMatchedKeywords(unmatchedKeywords);
	    
	    double percentage = (double) matchedKeywords.size() / atsDetail.getTotalKeywords().size() * 100;
	    
	    atsDetail.setMatchPercentage(String.format("%.2f", percentage) + "% of keywords matched");
	    
	    return atsDetail;
	}


}