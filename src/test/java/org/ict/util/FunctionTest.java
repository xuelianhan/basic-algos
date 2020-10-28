package org.ict.util;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> doubledFunction = e -> e * 2;
        Function<Integer, Integer> squareFunction = e -> e * e;

        // execute 
        int value = doubledFunction.andThen(squareFunction).apply(3);
        System.out.println("andThen value=" + value);

        int value2 = doubledFunction.compose(squareFunction).apply(3);
        System.out.println("compose value2=" + value2);

        Object identity = Function.identity().apply("xxx");
        System.out.println(identity);
    }
}
