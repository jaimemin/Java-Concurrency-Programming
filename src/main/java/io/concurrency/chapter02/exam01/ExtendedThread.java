package io.concurrency.chapter02.exam01;

public class ExtendedThread extends Thread {

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(String.format("%s Value %d", Thread.currentThread().getId(), i));
		}
	}

	public static void main(String[] args) {
		ExtendedThread thread = new ExtendedThread();
		thread.start();
	}
}
