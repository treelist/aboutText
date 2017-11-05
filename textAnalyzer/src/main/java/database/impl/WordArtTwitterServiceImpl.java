package database.impl;

import java.util.List;

import database.WordArtTwitterService;
import database.WordArtTwitterVO;

public class WordArtTwitterServiceImpl implements WordArtTwitterService {
	private WordArtTwitterDAO articleDAO = new WordArtTwitterDAO();
	
	public void insertWordArt(int word_id, int article_id) {
		articleDAO.insertWordArt(word_id, article_id);
	}

	public List<WordArtTwitterVO> getWordArtList(int article_id) {
		return articleDAO.getWordArtList(article_id);
	}

}
