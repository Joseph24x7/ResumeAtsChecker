package com.resume.ats.check.models;

import java.util.List;

import lombok.Data;

@Data
public class ATSDetail {
	
	private List<String> totalKeywords;
	private List<String> matchedKeywords;
	private List<String> unMatchedKeywords;
	private String matchPercentage;

}
