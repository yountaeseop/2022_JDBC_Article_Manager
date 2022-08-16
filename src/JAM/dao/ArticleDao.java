package JAM.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import JAM.Article;
import JAM.util.DBUtil;
import JAM.util.SecSql;

public class ArticleDao {
	
	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int doWrite(String title, String body) {
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);

		int id = DBUtil.insert(conn, sql);
		return id;
	}

	public boolean isArticleExists(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*)");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
		return DBUtil.selectRowBooleanValue(conn, sql);
		
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		DBUtil.delete(conn, sql);
	}

	public Map<String, Object> showDetail(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
		return DBUtil.selectRow(conn, sql);
		
	}

	public void doModify(int id, String title, String body) {
		SecSql sql = new SecSql();
		
		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);
		
		DBUtil.update(conn, sql);;
		
	}

	public List<Map<String, Object>> showList() {
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		
		return DBUtil.selectRows(conn, sql);
	}

	public Article getArticleById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

		if (articleMap.isEmpty()) {
			return null;
		}

		return new Article(articleMap);
		
	}

	public List<Article> getArticles() {
		SecSql sql = new SecSql();
		
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		
		List<Map<String, Object>> articlesListMap = DBUtil.selectRows(conn,sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}
}
