package io.concurrency.chapter07.exam03;

public class VolatileExample2 {
	private volatile int counter = 0;

	// 쓰기 작업 가시성 보장

	/**
	 * 하나의 쓰레드가 쓰기 작업을 하고 여러 개의 쓰레드가 읽기 작업을 할 때는 동시성 보장
	 * 허나 volatile 키워드만으로는 N:N 관계는 동시성 보장 X
	 */
	public void increment() {
		counter++;
	}

	// 읽기 작업 가시성 보장
	public int getCounter() {
		return counter;
	}

	public static void main(String[] args) {
		VolatileExample2 example = new VolatileExample2();

		// 쓰기 스레드
		Thread writer = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				example.increment();
			}
			System.out.println("쓰기 스레드가 쓰기 작업을 마쳤습니다.");
		});

		// 읽기 스레드
		Runnable reader = () -> {
			int localValue = -1;
			while (localValue < 1000) {
				localValue = example.getCounter();
				System.out.println(Thread.currentThread().getName() + " 읽은 값: " + localValue);
				try {
					Thread.sleep(100); // Reader는 값을 더 천천히 읽는다.
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		};

		writer.start();
		for (int i = 0; i < 5; i++) {
			new Thread(reader).start();
		}
	}
}
