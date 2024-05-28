package io.concurrency.chapter11.exam08;

import java.util.concurrent.CompletableFuture;

public class AllOfExample {
	public static void main(String[] args) throws InterruptedException {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
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
		CompletableFuture<Void> voidCf = CompletableFuture.allOf(cf, cf2, cf3);
		CompletableFuture<Integer> finalCf = voidCf.thenApply(v -> {
			int result = cf.join();
			int result2 = cf2.join();
			int result3 = cf3.join();

			System.out.println("result = " + result);
			System.out.println("result2 = " + result2);
			System.out.println("result3 = " + result3);

			return result + result2 + result3;
		});

		finalCf.join();
		System.out.println("최종 소요 시간: " + (System.nanoTime() - started) / 1e9);
		System.out.println("최종결과: " + finalCf.join());
		System.out.println("메인 쓰레드 종료");
	}
}
