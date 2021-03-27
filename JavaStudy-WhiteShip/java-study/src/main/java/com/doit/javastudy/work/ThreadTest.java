package com.doit.javastudy.work;

public class ThreadTest implements Runnable{
	public ThreadTest(){ }
	public void run(){
		System.out.println(Thread.currentThread().getName()+" : Thread Start.");
		for (int i = 0; i < 10; i++){
			try {
				Thread.sleep(1000);
				System.out.println(i+1+"초");
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+" : Thread End.");
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadTest());
		thread.start();
		System.out.println("Thread가 종료될때까지 기다립니다.");
		try{
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Main Thread End");
	}
}
// 훌륭합니다!
//https://gist.github.com/accidentlywoo/bfca35138b40cf3022609231ab36841a 를 참고해주세요.