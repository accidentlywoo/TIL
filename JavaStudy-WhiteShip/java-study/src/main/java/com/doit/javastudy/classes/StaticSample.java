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
	public  String getClassMemberField(){return classMemberField;}
	public void setInstanceMemberField(String instanceMemberField){
		this.instanceMemberField = instanceMemberField;
	}
	public void setClassMemberField(String classMemberField){
		StaticSample.classMemberField = classMemberField;
	}
}
class 머시깽이{
	public static void main(String[] args) {
		StaticSample sample1 = new StaticSample();
		sample1.setClassMemberField("꽑");
		sample1.setInstanceMemberField("꽑");
		System.out.println("클래스 필드 접근 : "+sample1.getClassMemberField());
		System.out.println("인스턴스 필드 접근 : " +sample1.getInstanceMemberField());
	}
}
