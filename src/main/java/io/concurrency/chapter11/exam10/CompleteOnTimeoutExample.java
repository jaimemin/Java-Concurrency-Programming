package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompleteOnTimeoutExample {
	public static void main(String[] args) {
		CompletableFuture.supplyAsync(() -> {
				try {
					Thread.sleep(3000);

					return "Hello World";
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).completeOnTimeout("타임아웃에 따른 기본 메시지", 2, TimeUnit.SECONDS)
			.thenAccept(result -> {
				System.out.println("result = " + result);
			}).join();
	}
}
