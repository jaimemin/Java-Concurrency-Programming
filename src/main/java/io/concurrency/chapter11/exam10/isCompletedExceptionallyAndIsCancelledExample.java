package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class isCompletedExceptionallyAndIsCancelledExample {

	public static void main(String[] args) {
		CompletableFuture<String> cf = new CompletableFuture<>();

		new Thread(() -> {
			try {
				Thread.sleep(2000);

				cf.completeExceptionally(new RuntimeException("비동기 작업 중 오류 발생"));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			while (!cf.isDone()) {
				System.out.println("작업 진행 중...");

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (cf.isCompletedExceptionally()) {
				System.out.println("작업이 예외처리 되었습니다.");
			} else {
				System.out.println("작업이 정상적으로 완료되었습니다.");
			}
		}).start();

		try {
			String result = cf.get();

			System.out.println("비동기 작업 결과: " + result);
		} catch (ExecutionException e) {
			System.out.println("예외 발생: " + e.getCause().getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
