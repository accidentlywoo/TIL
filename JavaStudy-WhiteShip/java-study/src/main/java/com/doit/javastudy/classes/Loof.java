package com.doit.javastudy.classes;

import java.util.Scanner;

public class Loof {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int sum = 0;
		int count = 0;
		int avg = 0;
		int input = scanner.nextInt();
//		while (false){//d error
//			sum += input;
//			count++;
//			System.out.println("count"+count);
//		}
		avg = sum/count;
		System.out.println("count" + count);
		System.out.println("sum" + sum);
		System.out.println("avg" + avg);

	}
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//
//		int sum = 0;
//		int count = 0;
//		int avg = 0;
//		int input = 0;
//		while ((input=scanner.nextInt()) != 0){
//			sum += input;
//			count++;
//			System.out.println("count"+count);
//		}
//		avg = sum/count;
//		System.out.println("count" + count);
//		System.out.println("sum" + sum);
//		System.out.println("avg" + avg);
//	}
}
