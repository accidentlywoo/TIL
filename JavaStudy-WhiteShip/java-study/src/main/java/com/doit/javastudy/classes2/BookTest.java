package com.doit.javastudy.classes2;

public class BookTest {
	public static void main(String[] args) {
		Book book = new QuickBooK();
		book.method1();
		book.method3();
		Object book2 = new Book();

		QuickBooK quickBooK = new QuickBooK();
		quickBooK.method1();
		quickBooK.method3();

		Book b = new QuickBooK();
		try {
			b.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		QuickBooK q = new QuickBooK();
		try {
			q.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
