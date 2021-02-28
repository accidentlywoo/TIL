package com.doit.javastudy.classes;

public class SingleTon {
	private static SingleTon getInstance(){
		System.out.println("ㄲㅓㄹ");
		return new SingleTon();
	}


}

class Test{
	public static void main(String[] args) {
//		SingleTon.getInstance();
	}
}
