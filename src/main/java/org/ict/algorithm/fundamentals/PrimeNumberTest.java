package org.ict.algorithm.fundamentals;

/**
 * test numbers are prime or not
 * A prime number is a natural number greater than 1 that
 * has no positive divisors other than 1 and itself.
 * see <a href="https://en.wikipedia.org/wiki/Prime_number">Prime_number</a>
 * see <a href="https://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/">Prime_number</a>
 * see <a href="http://world.mathigon.org/Prime_Numbers">Prime_Numbers</a>
 * 
 *
 */
public class PrimeNumberTest {

    public static boolean isPrimeV1(int N) {
        if (N < 2) {
            return false;
        }
        for (int i = 2; i * i < N; i++) {
            if (N % i == 0) return false;
        }
        return true;
    }
}
