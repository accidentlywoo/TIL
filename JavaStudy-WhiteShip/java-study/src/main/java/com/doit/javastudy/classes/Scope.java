package com.doit.javastudy.classes;

public class Scope {
	String upperScope = "냥!";
	void method_호도리(){
		String downScope = "꽑!";
		upperScope = "꽑꽑";
	}
//	downScope = "꽑꽑";
}
