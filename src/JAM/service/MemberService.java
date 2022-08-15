package JAM.service;

import java.sql.Connection;

import JAM.dao.MemberDao;
import JAM.util.DBUtil;
import JAM.util.SecSql;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService(Connection conn) {
		memberDao = new MemberDao(conn);
	}

	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
		
	}

	public int doJoin(String loginId, String loginPw, String name) {
		return memberDao.doJoin(loginId, loginPw, name);
	}

}
