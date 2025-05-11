package com.resume.ats.check.models;

import lombok.Data;

import java.util.Set;

@Data
public class ATSDetail {
	
	private Set<String> totalKeywords;
	private Set<String> unMatchedKeywords;
	private String matchPercentage;

}
