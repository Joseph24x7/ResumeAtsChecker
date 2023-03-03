package com.resume.ats.check.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resume.ats.check.models.ATSDetail;
import com.resume.ats.check.service.AtsCheckerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AtsCheckerController {
	
	private final AtsCheckerService atsCheckerService;
	
	@PostMapping("/check-resume")
	public ATSDetail generateAtsDetails(@RequestParam("file") MultipartFile file, @RequestParam("desc") String desc) throws IOException{
		return atsCheckerService.generateAtsDetails(file, desc);
	}


}