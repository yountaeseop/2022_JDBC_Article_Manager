package JAM.container;

import java.sql.Connection;
import java.util.Scanner;

import JAM.controller.ArticleController;
import JAM.controller.MemberController;
import JAM.dao.ArticleDao;
import JAM.dao.MemberDao;
import JAM.service.ArticleService;
import JAM.service.MemberService;
import JAM.session.Session;

public class Container {
	public static ArticleController articleController;
	public static MemberController memberController;
	
	public static ArticleService articleService;
	public static MemberService memberService;
	
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	public static Session session;
	
	public static Scanner sc;
	
	public static Connection conn;
	
	public static void init() {
		session = new Session();
		
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		
		articleController = new ArticleController();
		memberController = new MemberController();
		
	}
}
















