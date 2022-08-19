package JAM.session;

import JAM.Member;

public class Session {
	public int loginedMemberId = -1;
	public Member loginedMember;
	
	public Session() {
		loginedMemberId = -1;
	}

	public boolean isLogined() {
		return loginedMemberId != -1;
	}

	public void logout() {
		loginedMember = null;
		loginedMemberId = -1;
	}
	
	public void login(Member member) {
		loginedMember = member;
		loginedMemberId = member.id;
	}
	
}
