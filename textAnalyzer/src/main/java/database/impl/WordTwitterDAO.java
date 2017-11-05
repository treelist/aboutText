package database.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import plug.JDBCPlug;

public class WordTwitterDAO {
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// mySQL 명령어
    // words_twitter 테이블에 대하여
	private final String WORD_INSERT = "INSERT INTO words_twitter (word) VALUES (?)";
	private final String WORD_GET    = "SELECT * FROM words_twitter WHERE word=?";
	
    // DB에 word를 넣으며 해당 word가 저장된 id를 반환
	public int insertWord(String word) {
		int word_id = 0;
		System.out.println("===> insertWord() Start");
		try {
			conn = JDBCPlug.getConnection("words_twitter");
			
			stmt = conn.prepareStatement(WORD_GET);
			stmt.setString(1, word);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				stmt.clearParameters();
				
				stmt = conn.prepareStatement(WORD_INSERT);
				stmt.setString(1, word);
				stmt.executeUpdate();
			}
			
			stmt.clearParameters();
			
			stmt = conn.prepareStatement(WORD_GET);
			stmt.setString(1, word);
			rs = stmt.executeQuery();
			if (rs.next()) {
				word_id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
		return word_id;
	}
	
	public int getWordId(String word) {
		int word_id = 0;
		try {
			conn = JDBCPlug.getConnection("words_twitter");
			
			stmt = conn.prepareStatement(WORD_GET);
			stmt.setString(1, word);
			rs = stmt.executeQuery();
			if (rs.next()) {
				word_id = rs.getInt(1);
			} else {
				System.out.println("Warning: WordTwitterDAO.getWordId(String)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCPlug.close(rs, stmt, conn);
		}
		return word_id;
	}
}
