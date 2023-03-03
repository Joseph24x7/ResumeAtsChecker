package com.resume.ats.check.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resume.ats.check.models.ATSDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtsCheckerService {
	
	private final KeywordExtractorService keywordExtractorService;
	private final ScanPdfService scanPdfService;
	
	public ATSDetail generateAtsDetails(MultipartFile file,String desc) throws IOException{
		
		ATSDetail atsDetail=new ATSDetail();
		
	    String pdfContent = scanPdfService.scanPdfFromFile(file);
	    
	    atsDetail.setTotalKeywords(keywordExtractorService.extractKeywords(desc));

	    Set<String> unmatchedKeywords = new HashSet<>();
	    Set<String> matchedKeywords = new HashSet<>();
	    
	    for (String keyword : atsDetail.getTotalKeywords()) {
	    	keyword = keyword.toLowerCase();
	        if (pdfContent.contains(keyword)) {
	            matchedKeywords.add(keyword);
	        }else {
	        	unmatchedKeywords.add(keyword);
	        }
	    }
	    
	    atsDetail.setUnMatchedKeywords(unmatchedKeywords);
	    
	    double percentage = (double) matchedKeywords.size() / atsDetail.getTotalKeywords().size() * 100;
	    
	    atsDetail.setMatchPercentage(String.format("%.2f", percentage) + "% of keywords matched");
	    
	    return atsDetail;
	}

}
