package org.ict.algorithm.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureUtil {

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures){

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

        return completableFuture.thenApply(v->
                futures.stream()
                        .map(future-> future.join())
                        .collect(Collectors.toList())
        );
    }
}
