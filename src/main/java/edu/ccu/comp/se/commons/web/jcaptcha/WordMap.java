package edu.ccu.comp.se.commons.web.jcaptcha;

import java.util.HashMap;
import java.util.Map;

public class WordMap {
	private static Map<String, String> wordsMap = new HashMap<String, String>();

	public static Map<String, String> getWordsMap() {
		return wordsMap;
	}
	
}
