package com.doit.javastudy.algorithm.B1215Level2.helpme;

public class MaxConsecutiveOnes {
	public int findMaxConsecutiveOnes(int[] nums) {
		int count = 0;
		int current = 0;
		int max = 0;
		for(int num : nums){
			if(num == 1){
				if(current == 1){
					++count;
					if(count > max) max = count;
					continue;
				}
				count = 1;
				current = num;
				if(count > max) max = count;
				continue;
			}
			count = 0;
			current = num;
		}
		return max;
	}
	public static void main(String[] args) {
		MaxConsecutiveOnes m = new MaxConsecutiveOnes();
		System.out.println(m.findMaxConsecutiveOnes(new int[]{0}));
	}
}
