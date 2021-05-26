package org.ict.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @see <a href="https://www.callicoder.com/java-8-completablefuture-tutorial/"></a>
 * @date 2021/5/24 2:59 PM
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        System.out.println("start");
        CompletableFuture.runAsync(() -> {
            System.out.println("async result");
        });
        System.out.println("end");
    }

}
