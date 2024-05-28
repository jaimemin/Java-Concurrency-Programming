package io.concurrency.chapter11.exam08;

import java.util.concurrent.CompletableFuture;

public class AnyOfExample {
	public static void main(String[] args) {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(500);
				System.out.println("비동기 작업 시작 1");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			return 10;
		});
		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
				System.out.println("비동기 작업 시작 2");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			return 20;
		});
		CompletableFuture<Integer> cf3 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
				System.out.println("비동기 작업 시작 3");
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			return 30;
		});

		long started = System.nanoTime();
		CompletableFuture<Object> finalCf = CompletableFuture.anyOf(cf, cf2, cf3)
			.thenApply(result -> (int)result * 10);
		finalCf.join();
		System.out.println("최종 소요 시간: " + (System.nanoTime() - started) / 1e9);
		System.out.println("최종결과: " + finalCf.join());
		System.out.println("메인 쓰레드 종료");
	}
}
