package org.ict.algorithm.util;

import java.util.Random;

/**
 *
 * @see http://www.mkyong.com/java/java-generate-random-integers-in-a-range/
 */
public class RandomGenUtil {
    
    public static int getRandomIntInRangeV1(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min!");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int getRandomIntInRangeV2(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min!");
        }
        Random r = new Random();
        return (int)(Math.random() * ((max - min) + 1)) + min; 
    }

}
