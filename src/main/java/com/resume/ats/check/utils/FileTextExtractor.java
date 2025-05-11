package com.resume.ats.check.utils;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

public class FileTextExtractor {

    private static final Tika tika = new Tika();

    public static String extractText(MultipartFile file) throws Exception {
        return tika.parseToString(file.getInputStream());
    }
}

