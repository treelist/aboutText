package database.impl;

import database.WordTwitterService;

public class WordTwitterServiceImpl implements WordTwitterService {
	private WordTwitterDAO wordTwitterDAO = new WordTwitterDAO();
	
	public int insertWord(String word) {
		return wordTwitterDAO.insertWord(word);
	}
	
	public int getWordId(String word) {
		return wordTwitterDAO.getWordId(word);
	}
}
