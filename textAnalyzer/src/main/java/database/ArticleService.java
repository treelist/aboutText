package database;

import java.util.List;

public interface ArticleService {
	
	// 마지막 번호 구하기
	int getLastId();
	
	// 글 상세 조회
	ArticleVO getArticle(int id);

	// 글 목록 조회
	List<ArticleVO> getArticleListWhere(ArticleVO vo, String start, String end, int co_id);
	List<ArticleVO> getArticleListWhere(ArticleVO vo, String issued, int co_id);
	List<ArticleVO> getArticleListWhere(ArticleVO vo, String issued);
	List<ArticleVO> getArticleListWhere(ArticleVO vo, int co_id);
}
