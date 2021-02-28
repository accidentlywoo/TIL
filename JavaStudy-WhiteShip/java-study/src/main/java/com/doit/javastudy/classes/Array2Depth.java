package com.doit.javastudy.classes;

public class Array2Depth {
	public static void main(String[] args) {
		int[][] arr = {{1,2,3},{4,5,6}};

		for(int i =0; i<arr.length;i++ ){
			for(int j =0; j < arr[i].length; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
}
