package org.ict.algorithm.leetcode.string;

/**
 * Given a string s and a character letter,
 * return the percentage of characters in s that equal letter rounded down to the nearest whole percent.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "foobar", letter = "o"
 * Output: 33
 * Explanation:
 * The percentage of characters in s that equal the letter 'o' is 2 / 6 * 100% = 33% when rounded down, so we return 33.
 * Example 2:
 *
 * Input: s = "jjjj", letter = "k"
 * Output: 0
 * Explanation:
 * The percentage of characters in s that equal the letter 'k' is 0%, so we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of lowercase English letters.
 * letter is a lowercase English letter.
 * @author sniper
 * @date 17 Aug, 2022
 * LC2278
 */
public class PercentageOfLetterInString {

    public static void main(String[] args) {
        String s = "foobar";
        char letter = 'o';
        int result = percentageLetter(s, letter);
        System.out.println(result);
    }

    public static int percentageLetter(String s, char letter) {
        /**
         * double cnt = 0
         *
         * That avoids a cast.
         * But you'll find that the cast conversions are well-defined.
         * You don't have to guess, just check the JLS.
         * int to double is a widening conversion. From §5.1.2:
         *
         * Widening primitive conversions do not lose information about the overall magnitude of a numeric value.
         *
         * [...]
         *
         * Conversion of an int or a long value to float,
         * or of a long value to double, may result in loss of precision-that is,
         * the result may lose some of the least significant bits of the value.
         * In this case, the resulting floating-point value will be a correctly rounded version of the integer value,
         * using IEEE754 round-to-nearest mode (§4.2.4).
         *
         * Thanks for the answer provided by Matthew Flaschen
         */
        double cnt = 0;
        for (char c : s.toCharArray()) {
            if (letter == c) {
                cnt++;
            }
        }
        if (cnt == 0) {
            return 0;
        }
        return (int)(cnt * 100 / (s.length()));
    }
}
