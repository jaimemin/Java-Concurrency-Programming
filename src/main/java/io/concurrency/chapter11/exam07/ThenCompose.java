package io.concurrency.chapter11.exam07;

import java.util.concurrent.CompletableFuture;

public class ThenCompose {

	public static void main(String[] args) {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 10)
			.thenCompose(result -> CompletableFuture.supplyAsync(() -> result * 10));

		Integer result = cf.join();
		System.out.println(result);
	}
}
