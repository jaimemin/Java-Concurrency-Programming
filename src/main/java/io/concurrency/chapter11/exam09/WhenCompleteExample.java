package io.concurrency.chapter11.exam09;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class WhenCompleteExample {
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			if (System.nanoTime() % 2 != 0) {
				try {
					Thread.sleep(500);

					throw new RuntimeException("error has occurred");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

			return 10;
		}).whenComplete((result, exception) -> {
			// 반환 값이 없음
			if (exception != null) {
				System.out.println("Exception: " + exception.getMessage());
			} else {
				System.out.println("result: " + result);
			}
		});

		try {
			Thread.sleep(2000);

			cf.join();
		} catch (CompletionException e) {
			System.out.println("예외 처리를 합니다");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
