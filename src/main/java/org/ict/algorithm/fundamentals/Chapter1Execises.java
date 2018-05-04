package org.ict.algorithm.fundamentals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public class Chapter1Execises {

    public static void test() {
       List<String> list = Lists.newArrayList();
       for (int i = 0; i < 12; i++) {
           int x = getRandomNumberInRange(1, 13);
           System.out.println(x);
           list.add(x+"");
       }
       Collections.sort(list, new Comparator<String>(){
        @Override
        public int compare(String s1, String s2) {
            return Integer.valueOf(s1).compareTo(Integer.valueOf(s2));
        }
           
       });
       System.out.println(list);
    }
    
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    
    public static void main(String[] args) {
        test();
    }
}
