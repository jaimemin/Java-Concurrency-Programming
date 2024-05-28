package io.concurrency.chapter09.exam02;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class Example2 {

	public static void main(String[] args) {
		AtomicReference<String> reference = new AtomicReference<>("초기값");
		String currentValue = reference.get();
		System.out.println(String.format("현재 값: %s", currentValue));

		reference.set("변경된 값");
		System.out.println(String.format("변경된 값: %s", reference.get()));

		boolean success = reference.compareAndSet("변경된 값", "업데이트된 값");
		System.out.println(String.format("변경 성공? %s", success));
		System.out.println(String.format("현재 값: %s", reference.get()));

		String oldValue = reference.getAndSet("최종 값");
		System.out.println(String.format("변경 전 값: %s", oldValue));
		System.out.println(String.format("변경 후 값: %s", reference.get()));

		UnaryOperator<String> operator = o -> o.concat("!");
		String newValue = reference.updateAndGet(operator);
		System.out.println(String.format("변경 후 값: %s", newValue));
		System.out.println(String.format("변경 후 값 검증: %s", reference.get()));
	}
}
