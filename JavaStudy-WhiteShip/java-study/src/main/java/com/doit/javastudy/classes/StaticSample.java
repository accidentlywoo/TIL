package com.doit.javastudy.classes;

public class StaticSample {
	String instanceMemberField;
	static String classMemberField;

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
		StaticSample sample1 = new StaticSample();
		sample1.instanceMemberField="꽑";
		StaticSample.classMemberField="꽑"
		;
		System.out.println("sample1 인스턴스 필드 접근 : "+sample1.instanceMemberField);
		System.out.println("StaticSample 클래스 필드 접근 : " +StaticSample.classMemberField);

		StaticSample sample2 = new StaticSample();
		sample2.instanceMemberField = "꽑꽑";
		StaticSample.classMemberField="꽑꽑";

		System.out.println("sample1 인스턴스 필드 접근 : "+sample1.instanceMemberField);
		System.out.println("sample2 인스턴스 필드 접근 : "+sample2.instanceMemberField);
		System.out.println("StaticSample 클래스 필드 접근 : " +StaticSample.classMemberField);
	}
}
