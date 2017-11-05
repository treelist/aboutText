package database.impl;

// 불필요하게 instance가 많이 생기는 것을 막기 위한 싱글톤 팩토리
public class ImplFactory {
	static ArticleServiceImpl articleService = new ArticleServiceImpl();
	static WordTwitterServiceImpl wordTwitterService = new WordTwitterServiceImpl();
	static WordArtTwitterServiceImpl wordArtTwitterService = new WordArtTwitterServiceImpl();
	
	public static ArticleServiceImpl getArticleServiceImpl() {
		return articleService;
	}
	
	public static WordTwitterServiceImpl getWordTwitterServiceImpl() {
		return wordTwitterService;
	}
	
	public static WordArtTwitterServiceImpl getWordArtTwitterServiceImpl() {
		return wordArtTwitterService;
	}
}
