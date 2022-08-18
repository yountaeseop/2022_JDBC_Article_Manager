package JAM.dao;


import java.util.Map;

import JAM.Member;
import JAM.container.Container;
import JAM.util.DBUtil;
import JAM.util.SecSql;

public class MemberDao {
	
	public MemberDao() {
	}

	public boolean isLoginIdDup(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) > 0");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		
		return DBUtil.selectRowBooleanValue(Container.conn, sql);
	}

	public int doJoin(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);

		int id = DBUtil.insert(Container.conn, sql);
		
		return id;
		
	}

	public Member getMemberByLoginId(String loginId) {
		
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		
		Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);
		
		if (memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}
}















