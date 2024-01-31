package io.concurrency.chapter02.exam01;

class RunnableImpl implements Runnable {

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(String.format("%s Value %d", Thread.currentThread().getId(), i));
		}
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new RunnableImpl());
		thread.start();
	}
}
