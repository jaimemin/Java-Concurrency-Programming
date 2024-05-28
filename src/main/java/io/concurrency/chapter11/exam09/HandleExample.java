package io.concurrency.chapter11.exam09;

import java.util.concurrent.CompletableFuture;

public class HandleExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return 10;
        }).handle((result, error) -> {
            if (error != null) {
                System.out.println("비동기 예외처리: " + error.getMessage());

                return -1;
            }

            return result;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            if (System.nanoTime() % 2 != 0) {
                try {
                    Thread.sleep(500);

                    throw new RuntimeException("error has occurred");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            return 20;
        }).handle((result, error) -> {
            if (error != null) {
                System.out.println("비동기 예외처리 2: " + error.getMessage());

                return -1;
            }

            return result;
        });

        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (result, result2) -> {
            // 둘 중 하나라도 예외가 발생하면 예외 처리
            if (result == -1 || result2 == -1) {
                return -1;
            }

            return result + result2;
        });

        System.out.println("결과: " + cf3.join());
    }
}
