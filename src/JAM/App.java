package JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import JAM.exception.SQLErrorException;
import JAM.util.DBUtil;
import JAM.util.SecSql;

public class App {

	public void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			//DB 연결을 위한 Connection 객체 생성
			Connection conn = null;
			
			try {
				//1. 드라이버 클래스 로드
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (ClassNotFoundException e) {
				System.out.println("예외 : MySql 드라이버 클래스가 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			
			try {
				//2. DB연결을 위한 Connection 객체 생성
				conn = DriverManager.getConnection(url, "root", "5420");
				
				int actionResult = doAction(conn, sc, cmd);
				
				if(actionResult == -1) {
					break;
				}
				
			}
			catch (SQLException e) {
				System.out.println("@@@@에러@@@@ : " + e);
				break;
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						//5. 리소스 정리
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private int doAction(Connection conn, Scanner sc, String cmd) {
		
		if (cmd.equals("member join")) {
			String loginId = null;
			String loginPw = null;
			String loginPwConfirm = null;
			String name = null;

			System.out.println("== 회원가입 ==");
			
			// id 입력
			while (true) {
				System.out.printf("아이디 : ");
				loginId = sc.nextLine().trim();
				if (loginId.length() == 0) {
					System.out.println("아이디를 입력해주세요");
					continue;
				}
				break;
			}
			
			// Pw,PwConfirm 입력
			while (true) {
				System.out.printf("비밀번호 : ");
				loginPw = sc.nextLine().trim();

				if (loginPw.length() == 0) {
					System.out.println("비밀번호를 입력해주세요");
					continue;
				}

				boolean loginPwCheck = true;

				while (true) {
					System.out.printf("비밀번호 확인 : ");
					loginPwConfirm = sc.nextLine().trim();

					if (loginPwConfirm.length() == 0) {
						System.out.println("비밀번호 확인을 입력해주세요");
						continue;
					}

					if (loginPw.equals(loginPwConfirm) == false) {
						System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
						loginPwCheck = false;
						break;
					}
					break;
				}
				if (loginPwCheck) {
					break;
				}
			}

			while (true) {
				System.out.printf("이름 : ");
				name = sc.nextLine().trim();

				if (name.length() == 0) {
					System.out.println("이름을 입력해주세요");
					continue;
				}
				break;
			}

			SecSql sql = new SecSql();

			sql.append("INSERT INTO `member`");
			sql.append(" SET regDate = NOW()");
			sql.append(", updateDate = NOW()");
			sql.append(", loginId = ?", loginId);
			sql.append(", loginPw = ?", loginPw);
			sql.append(", name = ?", name);

			int id = DBUtil.insert(conn, sql);

			System.out.printf("%d번 회원님, 가입 되었습니다.\n", id);

		}
		else if (cmd.equals("article write")) {
			System.out.println("== 게시물 작성 ==");
			System.out.printf("제목 : ");
			String title = sc.nextLine();
			System.out.printf("내용 : ");
			String body = sc.nextLine();
			
			SecSql sql = new SecSql();
			
			sql.append("INSERT INTO article");
			sql.append(" SET regDate = NOW()");
			sql.append(", updateDate = NOW()");
			sql.append(", title = ?", title);
			sql.append(", `body` = ?", body);

			int id = DBUtil.insert(conn, sql);
			
			System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
			
			
		}  else if (cmd.startsWith("article delete ")) {
			int id = Integer.parseInt(cmd.split(" ")[2]);
			
			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*)");
			sql.append("FROM article");
			sql.append("WHERE id = ?", id);
			
			int articlesCount = DBUtil.selectRowIntValue(conn, sql);
			
			if(articlesCount == 0) {
				System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
				return 0;
			}
			
			System.out.printf("== %d번 게시물 삭제 ==\n", id);
			
			sql = new SecSql();
			sql.append("DELETE FROM article");
			sql.append("WHERE id = ?", id);
			
			DBUtil.delete(conn, sql);
			
			System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
			
		}else if (cmd.startsWith("article detail ")) {
			int id = Integer.parseInt(cmd.split(" ")[2]);
			
			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id = ?", id);
			
			Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
			
			if(articleMap.isEmpty()) {
				System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
				return 0;
			}
			
			System.out.printf("== %d번 게시물 상세보기 ==\n", id);
			
			Article article = new Article(articleMap);
			
			System.out.printf("번호 : %d\n", article.id);
			System.out.printf("작성날짜 : %s\n", article.regDate);
			System.out.printf("수정날짜 : %s\n", article.updateDate);
			System.out.printf("제목 : %s\n", article.title);
			System.out.printf("내용 : %s\n", article.body);
			
			System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			
		} else if (cmd.startsWith("article modify ")) {
			int id = Integer.parseInt(cmd.split(" ")[2]);
			
			System.out.printf("== %d번 게시물 수정 ==\n", id);
			System.out.printf("새 제목 : ");
			String title = sc.nextLine();
			System.out.printf("새 내용 : ");
			String body = sc.nextLine();
			
			SecSql sql = new SecSql();
			
			sql.append("UPDATE article");
			sql.append(" SET updateDate = NOW()");
			sql.append(", title = ?", title);
			sql.append(", `body` = ?", body);
			sql.append(" WHERE id = ?", id);
			
			DBUtil.update(conn, sql);
			
			System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			
		}
		else if (cmd.equals("article list")) {
			System.out.println("== 게시물 리스트 ==");

			List<Article> articles = new ArrayList<>();

			SecSql sql = new SecSql();
			
			sql.append("SELECT *");
			sql.append("FROM article");
			sql.append("ORDER BY id DESC");
			
			List<Map<String,Object>> articlesListMap = DBUtil.selectRows(conn, sql);
			
			for (Map<String, Object> articleMap : articlesListMap) {
				articles.add(new Article(articleMap));
			}
			
			if (articles.size() == 0) {
				System.out.println("게시물이 없습니다");
				return 0;
			}

			System.out.println("번호  /  제목");

			for (Article article : articles) {
				System.out.printf("%d  /  %s\n", article.id, article.title);
			}
			
		}
		if (cmd.equals("exit")) {
			System.out.println("프로그램을 종료합니다");
			return -1;
		}
		return 0;
	}
	
}














