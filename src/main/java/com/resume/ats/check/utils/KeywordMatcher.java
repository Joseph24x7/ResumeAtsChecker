package com.resume.ats.check.utils;

import java.util.Map;
import java.util.Set;

public class KeywordMatcher {

    public static Map<String, Object> calculateMatch(String resumeText, String jdText) {
        Set<String> resumeKeywords = OpenNlpSkillExtractor.extractNouns(resumeText);
        Set<String> jdKeywords = OpenNlpSkillExtractor.extractNouns(jdText);

        return SkillMatcherUtil.matchSkills(resumeKeywords, jdKeywords, 70);
    }
}

