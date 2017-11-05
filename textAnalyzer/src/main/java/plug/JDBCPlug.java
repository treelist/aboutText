package plug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCPlug {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL    = "jdbc:mysql://localhost:3306/articles?useSSL=true&verifyServerCertificate=false";
    private static final String USER   = "article_scrapper"; // 임시 사용자 이름
    private static final String PW     = "article";          // 임시 패스워드
    
	public static Connection getConnection(String table) {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		if (stmt != null) {
			try {
				if (!stmt.isClosed()) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		
		if (conn != null) {
			try {
				if (!conn.isClosed()) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if (rs != null) {
			try {
				if (!rs.isClosed()) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if (stmt != null) {
			try {
				if (!stmt.isClosed()) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		
		if (conn != null) {
			try {
				if (!conn.isClosed()) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
