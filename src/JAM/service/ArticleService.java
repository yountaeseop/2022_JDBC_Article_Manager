package JAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import JAM.Article;
import JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		return articleDao.doWrite(title, body);
	}

	public boolean isArticleExists(int id) {
		return articleDao.isArticleExists(id);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
		
	}

	public Map<String, Object> showDetail(int id) {
		return articleDao.showDetail(id);
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id, title, body);;
	}

	public List<Map<String, Object>> showList() {
		return articleDao.showList();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
}
