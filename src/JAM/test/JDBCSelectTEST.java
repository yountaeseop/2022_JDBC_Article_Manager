package JAM.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JAM.Article;

public class JDBCSelectTEST {
	public static void main(String[] args) {
<<<<<<< HEAD
		
=======
>>>>>>> 9204384fbaee9d48d7b59f19705de557d0a25b40
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Article> articles = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

<<<<<<< HEAD
			conn = DriverManager.getConnection(url, "root", "5420");
=======
			conn = DriverManager.getConnection(url, "root", "");
>>>>>>> 9204384fbaee9d48d7b59f19705de557d0a25b40
			System.out.println("연결 성공!");

			String sql = "SELECT *";
			sql += " FROM article";
			sql += " ORDER BY id DESC";

			System.out.println(sql);

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String regDate = rs.getString("regDate");
				String updateDate = rs.getString("updateDate");
				String title = rs.getString("title");
				String body = rs.getString("body");

				Article article = new Article(id, regDate, updateDate, title, body);
				articles.add(article);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
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
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("결과 : " + articles);
	}
}