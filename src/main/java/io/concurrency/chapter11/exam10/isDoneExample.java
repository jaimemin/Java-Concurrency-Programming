package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class isDoneExample {

    public static void main(String[] args) {
        CompletableFuture<String> cf = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);

                cf.complete("작업 완료!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 메인 스레드에서 완료 상태를 주기적으로 확인
        new Thread(() -> {
            while (!cf.isDone()) {
                System.out.println("작업 진행 중...");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("작업이 완료되었습니다.");
        }).start();

        try {
            String result = cf.get();

            System.out.println("비동기 작업 결과: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
