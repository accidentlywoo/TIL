package com.doit.javastudy.work;

public class 닭 implements Duck {
	final String quack = "꼬끼오!";
	public void quack() {
		System.out.println(quack);
	}

	public void walking() { System.out.println("뒤뚱 뒤뚱"); }

	public void flying() {
		System.out.println("호다닭!");
	}
}
