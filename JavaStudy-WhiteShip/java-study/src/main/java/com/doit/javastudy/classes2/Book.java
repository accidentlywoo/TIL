package com.doit.javastudy.classes2;

class Book implements Cloneable{
	public void method1(){
		System.out.println("PUBLIC METHOD");
	}
	private void method2(){
		System.out.println("PRIVATE METHOD");
	}
	protected void method3(){
		System.out.println("PROTECTED METHOD");
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public void useCase(A a){
		a.aMethod("test", 3);
	}
}
