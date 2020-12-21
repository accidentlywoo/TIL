package com.doit.javastudy.algorithm.B1215Level2;

import java.util.Arrays;

public class Albamon {
	public int solution(int[][] origin){
		int maxMoney = 0;
		int len = origin[0].length;
		int[][] cal = new int[0][len+1];

		initialize(origin, cal, len);
		return maxMoney;
	}
	public void initialize(int[][] origin, int[][] cal, int len){
		for(int i = 0; i < len; i++){
			cal[i] = origin[i];
		}
	}

	public static void main(String[] args) {
		Albamon albamon = new Albamon();
		System.out.println(Double.MAX_VALUE);
		System.out.println(Arrays.toString(new int[][]{{1, 2, 1},{1, 2, 2},{2, 3, 1},{3, 4, 1},{1, 4, 2}}));
	}
}
