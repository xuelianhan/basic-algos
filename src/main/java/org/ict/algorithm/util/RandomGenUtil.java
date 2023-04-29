package org.ict.algorithm.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * In practice, the java.util.Random class is often preferable to java.lang.Math.random().
 * In particular, there is no need to reinvent the random integer generation wheel 
 * when there is a straightforward API within the standard library to accomplish the task.
 * 
 * @see <a href="http://www.mkyong.com/java/java-generate-random-integers-in-a-range/"></a>
 * @see <a href="https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java"></a>
 */
public class RandomGenUtil {
    /**
     * In Java 1.7 or later, the standard way to do this is as follows:
     * This approach has the advantage of not needing to explicitly initialize a java.util.Random instance, 
     * which can be a source of confusion and error if used inappropriately.
     * However, conversely there is no way to explicitly set the seed 
     * so it can be difficult to reproduce results in situations 
     * where that is useful such as testing or saving game states or similar.
     * 
     * This will generates a random integer between min (inclusive) and max (inclusive).
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumberInRangeV4(int min, int max) {
    	// nextInt is normally exclusive of the top value,
    	// so add 1 to make it inclusive
    	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    /**
     * This will generates a random integer between min (inclusive) and max (inclusive).
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumberInRangeV3(int min, int max) {
    	Random r = new Random();
    	return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    
	/**
	 * In those situations, the pre-Java 1.7 technique shown below can be used.
	 * This will generates a random integer between min (inclusive) and max (inclusive).
	 * @param min
	 * @param max
	 * @return
	 */
    public static int getRandomIntInRangeV1(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min!");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    /**
     * This will generates a random integer between min (inclusive) and max (inclusive).
     * @param min
     * @param max
     * @return
     */
    public static int getRandomIntInRangeV2(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min!");
        }
        return (int)(Math.random() * ((max - min) + 1)) + min; 
    }


}
