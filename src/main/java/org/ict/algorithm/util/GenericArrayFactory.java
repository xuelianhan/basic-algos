package org.ict.algorithm.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenericArrayFactory<Item> {

    /**
     * Notice parameter is Class<Item[]> claszz, not Class<Item>[] clazz.
     */
    public Item[] arrayOf(Class<Item[]> clazz, int length) {
       return clazz.cast(Array.newInstance(clazz.getComponentType(), length)); 
    }

    public static void main(String[] args) {
        Integer[] intArray = new GenericArrayFactory<Integer>().arrayOf(Integer[].class, 4);
        for (int i = 0; i < 4; i++) {
            intArray[i] = i + 1;
        }
        StdOut.println(Arrays.toString(intArray));
    }
}
