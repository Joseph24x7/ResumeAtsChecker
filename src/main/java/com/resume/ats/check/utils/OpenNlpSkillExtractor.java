package com.resume.ats.check.utils;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class OpenNlpSkillExtractor {

    public static Set<String> extractNouns(String text) {
        try (
                InputStream modelIn = OpenNlpSkillExtractor.class.getResourceAsStream("/models/en-pos-maxent.bin")
        ) {
            POSModel model = new POSModel(modelIn);
            POSTaggerME tagger = new POSTaggerME(model);
            String[] tokens = SimpleTokenizer.INSTANCE.tokenize(text);
            String[] tags = tagger.tag(tokens);

            Set<String> nouns = new HashSet<>();
            for (int i = 0; i < tags.length; i++) {
                if (tags[i].startsWith("NN")) {
                    nouns.add(tokens[i].toLowerCase());
                }
            }
            return nouns;
        } catch (Exception e) {
            e.printStackTrace();
            return Set.of();
        }
    }
}

