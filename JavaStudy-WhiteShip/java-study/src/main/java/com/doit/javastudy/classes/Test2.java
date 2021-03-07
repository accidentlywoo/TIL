package com.doit.javastudy.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
		// 방법 1 Collection 사용하기 :: 원본 데이터를 지켜라!
		int max=0;
		int[] array = {1, 5, 3, 8, 2}; // 원본 데이터
		int[] copy_array= array.clone(); // 복제 데이터

		Arrays.sort(copy_array);
		// 배열을 출력하는 방법
		System.out.println(Arrays.toString(copy_array));
		max = copy_array[copy_array.length-1];
		System.out.println("max : " + max);


		// 방법 2 연산의 횟수를 조금이라도 줄이기!
		int max2 = 0;
		int arrayLength = array.length;
		// 고정된 배열의 길이를 구하는 연산을 반복하지않고 1번만하게 바깥으로 빼기!
		for(int i=0; i < arrayLength; i++) {
			if(max2 < array[i]) {
				max2 = array[i];
			}
		}
		System.out.println("max2 : " + max2);

		// 방법 3 Super Short 한줄로 대 통일!
		int max3 = 0;
		for(int item : array) if(max3 < item) max3 = item;
		System.out.println("max3 : " + max3);
	}
}
