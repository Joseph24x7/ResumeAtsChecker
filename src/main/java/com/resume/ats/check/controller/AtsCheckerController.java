package com.resume.ats.check.controller;

import com.resume.ats.check.utils.FileTextExtractor;
import com.resume.ats.check.utils.KeywordMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AtsCheckerController {

    private static final String[] SUPPORTED_FORMATS = {".pdf", ".doc", ".docx", ".txt", ".odt", ".rtf"};

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeResumeAndJD(
            @RequestParam("resume") MultipartFile resumeFile,
            @RequestParam(value = "jobDescription", required = false) MultipartFile jdFile,
            @RequestParam(value = "jobDescriptionText", required = false) String jdText) {

        try {
            if (resumeFile == null || !isValidFormat(resumeFile.getOriginalFilename())) {
                throw new IllegalArgumentException("Invalid resume format. Supported formats: PDF, DOC, DOCX, TXT, ODT, RTF.");
            }

            if (jdFile != null && !isValidFormat(jdFile.getOriginalFilename())) {
                throw new IllegalArgumentException("Invalid JD format. Supported formats: PDF, DOC, DOCX, TXT, ODT, RTF.");
            }

            String resumeText = FileTextExtractor.extractText(resumeFile);
            String jobDescriptionText = jdFile != null ? FileTextExtractor.extractText(jdFile) : jdText;

            if (jobDescriptionText == null || jobDescriptionText.isEmpty()) {
                throw new IllegalArgumentException("Job description is required as a file or plain text.");
            }

            Map<String, Object> result = KeywordMatcher.calculateMatch(resumeText, jobDescriptionText);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            throw new RuntimeException("Error processing files: " + e.getMessage(), e);
        }
    }

    private boolean isValidFormat(String fileName) {
        if (fileName == null) return false;
        for (String format : SUPPORTED_FORMATS) {
            if (fileName.toLowerCase().endsWith(format)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}