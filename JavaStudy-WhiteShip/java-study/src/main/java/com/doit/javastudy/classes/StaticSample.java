package com.doit.javastudy.classes;

public class StaticSample {
	private String instanceMemberField;
	private static String classMemberField;

	void instanceMemberMethod(){
		System.out.println("인스턴스 필드 접근 : "+instanceMemberField);
		System.out.println("클래스 멤버 메소드 : " + StaticSample.classMemberField);
	}
	static void classMemberMethod(){
		System.out.println("클래스 멤버 메소드 : " + StaticSample.classMemberField);
	}

	public String getInstanceMemberField(){return this.instanceMemberField;}
	public static String getClassMemberField(){return classMemberField;}
	public void setInstanceMemberField(String arg){
		instanceMemberField = arg;
	}
	public static void setClassMemberField(String arg){
		classMemberField = arg;
	}
}
class 머시깽이{
	public static void main(String[] args) {
		StaticSample sample = new StaticSample();
		StaticSample.setClassMemberField("꽑");
		sample.setInstanceMemberField("꽑");
		System.out.println("클래스 필드 접근 : "+StaticSample.getClassMemberField());
		System.out.println("인스턴스 필드 접근 : " +sample.getInstanceMemberField());
		StaticSample.classMemberMethod();
		sample.instanceMemberMethod();
	}
}
