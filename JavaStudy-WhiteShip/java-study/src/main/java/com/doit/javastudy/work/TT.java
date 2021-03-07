package com.doit.javastudy.work;

public class TT {
	public static void main(String[] args) {
		Tire gTire = new GTire();
		System.out.println(gTire.figure());
		System.out.println(gTire.frame());
		System.out.println(gTire.wheel());

		Tire hTire = new HTire();

		System.out.println(hTire.figure());
		System.out.println(hTire.frame());
		System.out.println(hTire.wheel());
	}
}
