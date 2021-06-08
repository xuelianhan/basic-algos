package org.ict.thread;

import java.util.concurrent.CompletableFuture;

/**
 * https://crondev.blog/2017/01/23/timeouts-with-java-8-completablefuture-youre-probably-doing-it-wrong/
 * https://stackoverflow.com/questions/30705981/what-is-the-recommended-way-to-wait-till-the-completable-future-threads-finish
 * https://github.com/spring-projects/spring-framework/blob/main/spring-context/src/main/java/org/springframework/scheduling/concurrent/ExecutorConfigurationSupport.java
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
