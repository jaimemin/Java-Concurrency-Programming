package io.concurrency.chapter09.exam02;

import java.util.concurrent.atomic.AtomicBoolean;

public class Example {

	public static void main(String[] args) {
		/**
		 * 전달된 값으로 초기값이 설정되며 default 값은 false
		 */
		AtomicBoolean atomicBoolean = new AtomicBoolean(true);
		boolean currentValue = atomicBoolean.get();
		System.out.println(String.format("현재 값: %s", currentValue));

		atomicBoolean.set(false);
		System.out.println(String.format("변경된 값: %s", atomicBoolean.get()));

		boolean prevValue = atomicBoolean.getAndSet(true);
		System.out.println(String.format("변경 전 값: %s", prevValue));
		System.out.println(String.format("변경 후 값: %s", atomicBoolean.get()));
	}
}
