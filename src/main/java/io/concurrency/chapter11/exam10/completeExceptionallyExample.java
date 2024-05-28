package io.concurrency.chapter11.exam10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class completeExceptionallyExample {
    public static void main(String[] args) {
        CompletableFuture<String> cf = new CompletableFuture<>();

        new Thread(() -> {
            try {
                // 2초 동안 작업 수행 (예: 비동기 작업)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cf.completeExceptionally(new RuntimeException("비동기 작업 중 오류 발생"));
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
