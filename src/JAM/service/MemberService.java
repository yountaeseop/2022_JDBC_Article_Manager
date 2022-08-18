package JAM.service;


import java.sql.Connection;

import JAM.Member;
import JAM.container.Container;
import JAM.dao.MemberDao;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
		
	}

	public int doJoin(String loginId, String loginPw, String name) {
		return memberDao.doJoin(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
