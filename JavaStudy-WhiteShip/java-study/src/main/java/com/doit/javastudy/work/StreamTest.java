package com.doit.javastudy.work;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		IntStream stream = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
		int sum = stream.skip(2).limit(5).sorted().filter(e -> e % 2 == 0).sum();
		System.out.println("딥 : "+sum);
	}
}
