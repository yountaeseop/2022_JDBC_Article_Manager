package JAM.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCinsertTEST {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			conn = DriverManager.getConnection(url, "root", "5420");
			System.out.println("연결 성공!");
			
			String sql = "INSERT INTO article";
			sql	+= " SET regDate = NOW()";
			sql	+= ", updateDate = NOW()";
			sql	+= ", title = CONCAT('제목',RAND())";
			sql	+= ", `body` = CONCAT('내용',RAND());";
			
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			
			int affectedRows = pstmt.executeUpdate();
			
			System.out.println("affectedRows : " + affectedRows);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



