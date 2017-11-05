package database.impl;

import java.util.List;

import database.ArticleService;
import database.ArticleVO;

public class ArticleServiceImpl implements ArticleService {
	private ArticleDAO articleDAO = new ArticleDAO();
	
	public int getLastId() {
		return articleDAO.getLastId();
	}
	
	public ArticleVO getArticle(int id) {
		return articleDAO.getArticle(id);
	}
	
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, String start, String end, int co_id) {
		return articleDAO.getArticleListWhere(vo, start, end, co_id);
	}
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, String issued, int co_id) {
		return articleDAO.getArticleListWhere(vo, issued, co_id);
	}
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, String issued) {
		return articleDAO.getArticleListWhere(vo,issued);
	}
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, int co_id) {
		return articleDAO.getArticleListWhere(vo, co_id);
	}
}
