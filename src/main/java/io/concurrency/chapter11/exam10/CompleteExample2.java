package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;

public class CompleteExample2 {

	public static void main(String[] args) {
		CompletableFuture<String> cf = new CompletableFuture<>();
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			cf.complete("작업 완료!");
		}).start();

		try {
			String result = cf.get();

			System.out.println("비동기 작업 결과: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
