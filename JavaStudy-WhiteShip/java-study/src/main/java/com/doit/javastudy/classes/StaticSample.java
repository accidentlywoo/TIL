package com.doit.javastudy.classes;

public class StaticSample {
	String instanceMemberField;
	static final String classMemberField = "꽑";

	void instanceMemberMethod(){
		System.out.println("인스턴스 필드 접근 : "+instanceMemberField);
		System.out.println("클래스 멤버 메소드 : " + StaticSample.classMemberField);
	}
	static void classMemberMethod(){
		System.out.println("클래스 멤버 메소드 : " + StaticSample.classMemberField);
	}
}
class 머시깽이{
	public static void main(String[] args) {
		StaticSample sample = new StaticSample();
		System.out.println("클래스 필드 접근 : "+StaticSample.classMemberField);
		System.out.println("인스턴스 필드 접근 : " +sample.instanceMemberField);
		StaticSample.classMemberMethod();
		sample.instanceMemberMethod();
	}
}
