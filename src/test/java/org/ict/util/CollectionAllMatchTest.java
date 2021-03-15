package org.ict.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hanxuelian
 * @Date: 2021/3/15 11:08 AM
 */
public class CollectionAllMatchTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i * 1000);
        }
        boolean result = list.stream()
                .allMatch(item -> item > 5000);
        System.out.println(result);
    }
}
