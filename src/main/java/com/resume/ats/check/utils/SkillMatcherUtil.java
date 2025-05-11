package com.resume.ats.check.utils;

import org.apache.commons.text.similarity.FuzzyScore;

import java.util.*;

public class SkillMatcherUtil {
    private static final Locale LOCALE = Locale.ENGLISH;
    private static final FuzzyScore scorer = new FuzzyScore(LOCALE);

    public static Map<String, Object> matchSkills(Set<String> resumePhrases, Set<String> jdPhrases, int threshold) {
        Set<String> matched = new HashSet<>();
        Set<String> unmatched = new HashSet<>();

        for (String jdSkill : jdPhrases) {
            boolean found = false;
            for (String resumeSkill : resumePhrases) {
                int score = scorer.fuzzyScore(resumeSkill, jdSkill);
                int max = Math.max(resumeSkill.length(), jdSkill.length()) * 2;
                if ((score * 100 / max) > threshold) {
                    matched.add(jdSkill);
                    found = true;
                    break;
                }
            }
            if (!found) unmatched.add(jdSkill);
        }

        int total = jdPhrases.size();
        int matchedScore = matched.size() * 100 / (total == 0 ? 1 : total);

        Map<String, Object> result = new HashMap<>();
        result.put("matchScore", matchedScore);
        result.put("matchedSkills", matched);
        result.put("missingSkills", unmatched);

        return result;
    }
}

