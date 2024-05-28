package io.concurrency.chapter11.exam11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GetExample {

	public static void main(String[] args) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return "비동기 작업 처리!";
		});

		try {
			String result = future.get(3, TimeUnit.SECONDS);

			System.out.println("Result: " + result);
		} catch (TimeoutException e) {
			System.out.println("Timeout occurred before the task was completed");
		} catch (InterruptedException e) {
			System.out.println("Thread was interrupted while waiting");
		} catch (ExecutionException e) {
			System.out.println("Exception occurred during the computation: " + e.getCause());
		}
	}
}
