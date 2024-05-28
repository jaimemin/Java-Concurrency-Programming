package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;

public class CompletedFutureExample {
	public static void main(String[] args) {
		CompletableFuture<String> future = CompletableFuture.completedFuture("미리 완료된 결과");

		// 결과를 즉시 얻을 수 있음
		String result = future.join();  // join()은 예외 처리를 하지 않는 get() 메서드
		System.out.println("결과: " + result);
	}
}
