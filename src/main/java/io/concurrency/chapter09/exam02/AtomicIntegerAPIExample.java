package io.concurrency.chapter09.exam02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicIntegerAPIExample {

	public static void main(String[] args) throws InterruptedException {
		AtomicInteger atomicInteger = new AtomicInteger(10);
		int currentValue = atomicInteger.get();
		System.out.println(String.format("현재 값: %d", currentValue));

		atomicInteger.set(20);
		System.out.println(String.format("변경된 값: %d", atomicInteger.get()));

		int previousValue = atomicInteger.getAndSet(30);
		System.out.println(String.format("변경 전 값: %d", previousValue));

		int newValue = atomicInteger.incrementAndGet();
		System.out.println(String.format("변경 후 값: %d", newValue));

		boolean updated = atomicInteger.compareAndSet(31, 40);
		System.out.println(String.format("성공적으로 값을 변경? %s", updated));  // true
		System.out.println(String.format("변경 후 값: %d", atomicInteger.get()));

		IntUnaryOperator addFive = value -> value + 5;
		int previousValue2 = atomicInteger.getAndUpdate(addFive);
		System.out.println(String.format("변경 전 값: %d", previousValue2));
		System.out.println(String.format("변경 후 값: %d", atomicInteger.get()));
	}
}
