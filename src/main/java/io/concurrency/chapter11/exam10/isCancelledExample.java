package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class isCancelledExample {

	public static void main(String[] args) {
		CompletableFuture<String> cf = new CompletableFuture<>();

		new Thread(() -> {
			try {
				Thread.sleep(2000);

				if (cf.isCancelled()) {
					System.out.println("작업이 취소되었습니다.");

					return;
				}

				cf.complete("작업 완료!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		try {
			TimeUnit.SECONDS.sleep(1);
			boolean cancelled = cf.cancel(true);

			System.out.println("취소 시도 결과: " + cancelled);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (cf.isCancelled()) {
			System.out.println("CompletableFuture가 취소되었습니다.");
		} else {
			try {
				String result = cf.get();
				
				System.out.println("비동기 작업 결과: " + result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
