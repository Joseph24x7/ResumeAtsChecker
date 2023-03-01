package com.text.to.video.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

@Service
public class NlpService {
	
	private final StanfordCoreNLP pipeline;

    public NlpService() {
    	
        Properties properties = new Properties();
        
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        properties.setProperty("pos.model", "english-left3words-distsim.tagger");
        properties.setProperty("ner.model", "english.all.3class.distsim.crf.ser.gz");

        this.pipeline = new StanfordCoreNLP(properties);
    }

    public List<String> getKeywords(String text) {
    	
        List<String> keywords = new ArrayList<>();

        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (!ner.equals("O")) {
                    keywords.add(token.word());
                }
            }
        }

        return keywords;
    }

}
