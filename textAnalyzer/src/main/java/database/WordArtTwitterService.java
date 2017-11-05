package database;

import java.util.List;

public interface WordArtTwitterService {
	
	void insertWordArt(int word_id, int article_id);
	
	List<WordArtTwitterVO> getWordArtList(int article_id);
	
}
