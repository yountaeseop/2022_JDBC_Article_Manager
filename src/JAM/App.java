package JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import JAM.container.Container;
import JAM.controller.ArticleController;
import JAM.controller.MemberController;

public class App {

	public void run() {
		Container.sc = new Scanner(System.in);
		
		Container.init();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = Container.sc.nextLine().trim();
			
			//DB 연결을 위한 Connection 객체 생성
			Connection conn = null;
			
			try {
				//1. 드라이버 클래스 로드
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (ClassNotFoundException e) {
				System.out.println("예외 : 클래스가 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			
			String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			
			try {
				//2. DB연결을 위한 Connection 객체 생성
				conn = DriverManager.getConnection(url, "root", "5420");
				
				Container.conn = conn;
				
				int actionResult = action(cmd);
				
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
	
	private int action(String cmd) {
		
		if (cmd.equals("exit")) {
			System.out.println("프로그램을 종료합니다");
			return -1;
		}
		
		MemberController memberController = Container.memberController;
		ArticleController articleController = Container.articleController;
		
		if (cmd.equals("member join")) {
			memberController.doJoin(cmd);
		}	else if (cmd.equals("member login")) {
			memberController.doLogin(cmd);
		}	else if (cmd.equals("member logout")) {
			memberController.doLogout(cmd);
		}	else if (cmd.equals("member profile")) {
			memberController.showProfile(cmd);
		}	else if (cmd.equals("article write")) {
			articleController.dowrite(cmd);
		}	else if (cmd.startsWith("article delete ")) {
			articleController.doDelete(cmd);
		}	else if (cmd.startsWith("article detail ")) {
			articleController.showDetail(cmd);
		} 	else if (cmd.startsWith("article modify ")) {
			articleController.doModify(cmd);
		}	else if (cmd.equals("article list")) {
			articleController.showList(cmd);
		} else {
			System.out.println("존재하지 않는 명령어 입니다.");
		}
		
		return 0;
	}
	
}














