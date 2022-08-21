package JAM;

import java.time.LocalDateTime;
import java.util.Map;

public class Article extends Object {

	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public int memberId;
	public String title;
	public String body;
	public int hit;
	
	public String extra__writer;
	
	public Article(Map<String, Object> articleMap) {
		this.id = (int)articleMap.get("id");
		this.regDate = (LocalDateTime)articleMap.get("regDate");
		this.updateDate = (LocalDateTime)articleMap.get("updateDate");
		this.memberId = (int)articleMap.get("memberId");
		this.title = (String)articleMap.get("title");
		this.body = (String)articleMap.get("body");
		this.hit = (int)articleMap.get("hit");
		
		if(articleMap.get("extra__writer") != null) {
			this.extra__writer = (String)articleMap.get("extra__writer");
		}
	}

	public Article(int id2, String regDate2, String updateDate2, String title2, String body2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", memberId=" + memberId
				+ ", title=" + title + ", body=" + body + ", hit=" + hit + ", extra__writer=" + extra__writer + "]";
	}
	// 자동생성하는 방법 => 마우스 우클릭 - source 선택 - generate to String
	
}


