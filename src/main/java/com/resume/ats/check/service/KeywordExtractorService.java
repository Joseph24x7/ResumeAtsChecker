package com.resume.ats.check.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

@Service
public class KeywordExtractorService {

	public List<String> extractKeywords(String inputText) throws IOException {

		InputStream tokenModelIn = new FileInputStream("src/main/resources/en-token.bin");
		TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
		Tokenizer tokenizer = new TokenizerME(tokenModel);

		InputStream posModelIn = new FileInputStream("src/main/resources/en-pos-maxent.bin");
		POSModel posModel = new POSModel(posModelIn);
		POSTaggerME posTagger = new POSTaggerME(posModel);

		// Tokenize input text
		String[] tokens = tokenizer.tokenize(inputText);

		// Tag parts of speech in input text
		String[] posTags = posTagger.tag(tokens);

		// Extract keywords based on relevant parts of speech
		List<String> keywords = new ArrayList<>();
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			String posTag = posTags[i];
			if (posTag.startsWith("N") || posTag.startsWith("J")) {
				// Consider only nouns and adjectives
				if (token.matches(".*\\[.*\\].*")) {
					// Remove any parts-of-speech tags from token
					token = token.replaceAll("\\[.*\\]", "");
				}
				keywords.add(token);
			}
		}

		return keywords;
	}

}
