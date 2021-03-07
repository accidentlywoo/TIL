package com.doit.javastudy.work;

public interface Duck {
	final String quack = "꽑 꽑!";
	public default void quack(){
		System.out.println(quack);
	}
	public void walking();
	public void flying();
}
