package JAM.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import JAM.Article;
import JAM.service.ArticleService;
import JAM.service.MemberService;
import JAM.util.DBUtil;
import JAM.util.SecSql;

public class ArticleController extends Controller {
	
	private ArticleService articleService;
	
	public ArticleController(Connection conn, Scanner sc) {
		super(sc);
		articleService = new ArticleService(conn);
	}
	
	public void dowrite(String cmd) {
		System.out.println("== 게시물 작성 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		int id = articleService.doWrite(title,body);
		
		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}
	public void doDelete(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		boolean isArticleExists = articleService.isArticleExists(id);
		
		if(isArticleExists == false) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.printf("== %d번 게시물 삭제 ==\n", id);
		
		articleService.doDelete(id);
		
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
		
	}
	public void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		Map<String, Object> articleMap = articleService.showDetail(id); 
		
		if(articleMap.isEmpty()) {
			System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
			return;
		}
		
		System.out.printf("== %d번 게시물 상세보기 ==\n", id);
		
		Article article = new Article(articleMap);
		
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		
	}
	public void doModify(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		System.out.printf("== %d번 게시물 수정 ==\n", id);
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();
		
		articleService.doModify(id, title, body);
		
		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
		
	}
	public void showList(String cmd) {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = new ArrayList<>();
		
		List<Map<String,Object>> articlesListMap = articleService.showList();
		
		for (Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}
		
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		System.out.println("번호  /  제목");

		for (Article article : articles) {
			System.out.printf("%d  /  %s\n", article.id, article.title);
		}
		
	}
	
	
}
