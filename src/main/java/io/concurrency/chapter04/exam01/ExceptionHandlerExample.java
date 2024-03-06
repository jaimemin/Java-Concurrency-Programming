package io.concurrency.chapter04.exam01;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandlerExample {

	private static final Logger LOGGER = Logger.getLogger(ExceptionHandlerExample.class.getName());

	public static void main(String[] args) {

		// 모든 쓰레드의 예외에 대한 기본 핸들러 설정
		Thread.setDefaultUncaughtExceptionHandler(
			(t, e) -> System.out.println(String.format("%s에서 예외 발생: %s", t.getName(), e)));

		// 예외를 발생시키는 여러 쓰레드
		Thread thread1 = new Thread(() -> {
			throw new RuntimeException("쓰레드 1 예외!");
		});

		Thread thread2 = new Thread(() -> {
			throw new RuntimeException("쓰레드 2 예외!");
		});
		thread2.setUncaughtExceptionHandler((t, e) -> {
			LOGGER.log(Level.SEVERE, String.format("%s에서 예외가 발생했습니다.", t.getName()), e);

			sendNotificationToSlack(e);
		});

		thread1.start();
		thread2.start();
	}

	private static void sendNotificationToSlack(Throwable e) {
		System.out.println(String.format("Slack Notification: %s", e.getMessage()));
	}
}
