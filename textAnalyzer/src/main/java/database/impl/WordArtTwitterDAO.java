package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.WordArtTwitterVO;
import plug.JDBCPlug;

public class WordArtTwitterDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// SQL 명령어들
    // wordart_twitter 테이블에 대하여
	private final String WORDART_CHECK  = "SELECT * FROM wordart_twitter WHERE word_id=? AND art_id=?";
	private final String WORDART_INSERT = "INSERT INTO wordart_twitter (word_id, art_id) VALUES (?, ?)";
	private final String WORDART_UPDATE = "UPDATE wordart_twitter SET count=? WHERE word_id=? AND art_id=?";
	private final String WORDART_LIST   = "SELECT * FROM wordart_twitter WHERE art_id=?";
	
	public void insertWordArt(int word_id, int article_id) {
		System.out.println("===> insertBoard() Start");
		try {
			conn = JDBCPlug.getConnection("wordart_twitter");
			
			stmt = conn.prepareStatement(WORDART_CHECK);
			stmt.setInt(1, word_id);
			stmt.setInt(2, article_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				
				stmt.clearParameters();
				
				stmt = conn.prepareStatement(WORDART_UPDATE);
				stmt.setInt(1, count + 1);
				stmt.setInt(2, word_id);
				stmt.setInt(3, article_id);
				stmt.executeUpdate();
			} else {
				stmt.clearParameters();
				
				stmt = conn.prepareStatement(WORDART_INSERT);
				stmt.setInt(1, word_id);
				stmt.setInt(2, article_id);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
	}
	
	// 글 목록 조회
	public List<WordArtTwitterVO> getWordArtList(int article_id) {
		System.out.println("===> getBoardList() Start");
		List<WordArtTwitterVO> wordArtList = new ArrayList<WordArtTwitterVO>();
		try {
			conn = JDBCPlug.getConnection("wordart_twitter");
			stmt = conn.prepareStatement(WORDART_LIST);
			stmt.setInt(1, article_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				WordArtTwitterVO wordArt = new WordArtTwitterVO();
				wordArt.setWord_id(rs.getInt("WORD_ID"));
				wordArt.setArt_id(rs.getInt("ART_ID"));
				wordArt.setCount(rs.getInt("COUNT"));
				wordArtList.add(wordArt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
		return wordArtList;
	}
}
