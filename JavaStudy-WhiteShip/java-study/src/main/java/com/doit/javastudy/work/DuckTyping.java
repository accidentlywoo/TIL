package com.doit.javastudy.work;

public class DuckTyping implements Duck{
	@Override
	public void walking() { System.out.println("뒤뚱 뒤뚱"); }

	@Override
	public void flying() {System.out.println("푸드득"); }
}