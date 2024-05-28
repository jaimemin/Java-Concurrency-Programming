package io.concurrency.chapter11.exam07;

import java.util.concurrent.CompletableFuture;

public class ThenCombine {

	public static void main(String[] args) {
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 10);
		CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 30);

		Integer multiply = cf.thenCombine(cf2, (result, result2) -> result * result2).join();
		System.out.println(multiply);
	}
}
