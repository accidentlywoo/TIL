package com.doit.javastudy.classes2;

public class DynamicTest {
	public static void main(String[] args) {
		Book book = new Book();
		A test = new ASub();

		book.useCase(test);
	}
}
