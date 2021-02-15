package com.doit.javastudy.classes;

public class Week1Loof {
	public static void main(String[] args) {
		for(int x = 0; x < 100; x ++){
			if(x % 2 == 0){
				continue;
			}
			x+=x;
			if(x > 60){
				System.out.println("답 : " + x);
				break;
			}
		}
		for(float x = 0.1f; x <= 1.0f; x+=0.1f) {System.out.println(x);}

		for(int i = 0; i < 100; i++){
			if(i%2 == 0) continue;
			System.out.println(i);
			if(i>=77) break;
		}
	}
}
