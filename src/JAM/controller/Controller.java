package JAM.controller;

import java.util.Scanner;

import JAM.container.Container;

public abstract class Controller {
	protected Scanner sc;
	
	public Controller() {
		this.sc = Container.sc;
	}
	// Controller에 Scanner sc를 지워야 ArticleController랑 MemberController
	// 에서 오류가 나지 않는다. 부모클래스의 생성자 형태를 따라야하기 때문?
}
