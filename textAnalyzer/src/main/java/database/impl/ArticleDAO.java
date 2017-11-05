package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.ArticleVO;
import plug.JDBCPlug;

public class ArticleDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// mySQL 명령어
    // articles 테이블에 대하여
	private final String GET_LAST_ID  = "SELECT IFNULL(MAX(id), 0) FROM articles";
	private final String ARTICLE_GET  = "SELECT * FROM articles WHERE id=?";
	private final String ARTICLE_LIST = "SELECT * FROM articles WHERE '?'<=DATE(issued) AND DATE(issued)<='?' AND co_id LIKE '?'";
	
	// 마지막 글 구하기
	public int getLastId() {
		System.out.println("===> getLastId() Start");
		int id = -1;
		try {
			conn = JDBCPlug.getConnection("articles");
			
			stmt = conn.prepareStatement(GET_LAST_ID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				System.out.println("Warning: getLastId()");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
		return id;
	}
	
	public ArticleVO getArticle(int id) {
		System.out.println("===> getBoard() Start");
		ArticleVO article = null;
		try {
			conn = JDBCPlug.getConnection("articles");
			stmt = conn.prepareStatement(ARTICLE_GET);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				article = new ArticleVO();
				article.setId(rs.getInt("ID"));
				article.setCo_id(rs.getInt("CO_ID"));
				article.setIssued(rs.getDate("ISSUED"));
				article.setTitle(rs.getString("TITLE"));
				article.setContents(rs.getString("CONTENTS"));
				article.setCreated(rs.getDate("CREATED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
		return article;
	}
	
	// 글 목록 조회
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, String start, String end, int co_id) {
		System.out.println("===> getBoardListWhere() Start");
		List<ArticleVO> boardList = new ArrayList<ArticleVO>();
		try {
			conn = JDBCPlug.getConnection("articles");
			
			stmt = conn.prepareStatement(ARTICLE_LIST);
			stmt.setString(1, start);
			stmt.setString(2, end);
			if (co_id == 0) {
				stmt.setString(3, "#");
			} else {
				stmt.setInt(3, co_id);
			}
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				ArticleVO article = new ArticleVO();
				article.setId(rs.getInt("ID"));
				article.setCo_id(rs.getInt("CO_ID"));
				article.setIssued(rs.getDate("ISSUED"));
				article.setTitle(rs.getString("TITLE"));
				article.setContents(rs.getString("CONTENTS"));
				article.setCreated(rs.getDate("CREATED"));
				boardList.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
		return boardList;
	}
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, String issued, int co_id) {
		return getArticleListWhere(vo, issued, issued, co_id);
	}
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, String issued) {
		return getArticleListWhere(vo, issued, issued, 0);
	}
	public List<ArticleVO> getArticleListWhere(ArticleVO vo, int co_id) {
		return getArticleListWhere(vo, "2017-06-01", "2020-12-31", co_id);
	}
}
