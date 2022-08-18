package JAM.controller;

import java.util.Scanner;

import JAM.container.Container;

public abstract class Controller {
	protected Scanner sc;
	
	public Controller() {
		this.sc = Container.sc;
	}
	
}
