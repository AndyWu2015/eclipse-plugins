package com.liferay.andywu.test;

import java.util.Timer;
import java.util.TimerTask;

public class ThreadTest extends Thread {

	public ThreadTest() {
		super("My Name");
	}

	public void run() {
		System.out.println("sleep...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("awake");
	}
	
	class MyTask extends TimerTask {
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name);
		}
	}

	public static void main(String[] args) {
		/*Thread myThread = new ThreadTest();
		myThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			myThread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		ThreadTest myThread = new ThreadTest();
		System.out.println(Thread.currentThread().getName());
		Timer timer = new Timer();
		myThread.runTimer(timer);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
		System.out.println("cancel");
	}
	public void runTimer(Timer timer){
		timer.schedule(new MyTask(),0L,1000L);
	}

}
