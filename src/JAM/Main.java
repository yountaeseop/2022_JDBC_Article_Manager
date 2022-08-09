package JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("article write")) {
				System.out.println("== 게시물 작성 ==");
				int id = lastArticleId + 1;
				
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);
				lastArticleId++;

				System.out.println(article);
				
			} else if (cmd.equals("article list")) {
				
				System.out.println("== 게시물 리스트 ==");

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}

				System.out.println("번호  /  제목");

				for (Article article : articles) {
					System.out.printf("%d  /  %s\n", article.id, article.title);
				}
				
			} else if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다");
				break;
			}
		}
	}
}
