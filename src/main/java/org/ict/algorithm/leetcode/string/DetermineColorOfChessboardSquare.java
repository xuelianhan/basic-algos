package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given coordinates,
 * a string that represents the coordinates of a square of the chessboard.
 * Below is a chessboard for your reference.
 *
 * Return true if the square is white, and false if the square is black.
 *
 * The coordinate will always represent a valid chessboard square.
 * The coordinate will always have the letter first, and the number second.
 *
 *
 *
 * Example 1:
 *
 * Input: coordinates = "a1"
 * Output: false
 * Explanation: From the chessboard above, the square with coordinates "a1" is black, so return false.
 * Example 2:
 *
 * Input: coordinates = "h3"
 * Output: true
 * Explanation: From the chessboard above, the square with coordinates "h3" is white, so return true.
 * Example 3:
 *
 * Input: coordinates = "c7"
 * Output: false
 *
 *
 * Constraints:
 *
 * coordinates.length == 2
 * 'a' <= coordinates[0] <= 'h'
 * '1' <= coordinates[1] <= '8'
 *
 * @author sniper
 * @date 2022/8/16
 * LC1812
 */
public class DetermineColorOfChessboardSquare {

    public static void main(String[] args) {
        String coordinates = "a1";
        /**
         * a: 97
         * 1: 49
         */
        int c1 = coordinates.charAt(0);
        int c2 = coordinates.charAt(1);
        int c3 = (coordinates.charAt(0) - 'a') + (coordinates.charAt(1) - '0');
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        boolean res = squareIsWhite(coordinates);
        System.out.println(res);

    }

    public boolean squreIsWhiteV3(String coordinates) {
        return ((coordinates.charAt(0) - 'a') + (coordinates.charAt(1) - '0')) % 2 == 0 ? true : false;
    }

    /**
     * Solution provided by lee215, a legend man whose codes like a elegant poem.
     *
     * a~z:97~122
     * 1~9:49~57
     *
     * @param a
     * @return
     */
    public boolean squareIsWhiteV2(String a) {
        return a.charAt(0) % 2 != a.charAt(1) % 2;
    }

    public static boolean squareIsWhite(String coordinates) {
        Set<Character> acegSet = new HashSet<>(Arrays.asList(new Character[]{'a', 'c', 'e', 'g'}));
        Set<Character> bdfhSet = new HashSet<>(Arrays.asList(new Character[]{'b', 'd', 'f', 'h'}));
        boolean white = false;
        char c1 = coordinates.charAt(0);
        int c2 = (int)(coordinates.charAt(1) - '0');
        if (acegSet.contains(c1)) {
            white = (c2 % 2 == 0 ? true : false);
        } else if (bdfhSet.contains(c1)) {
            white = (c2 % 2 == 0 ? false : true);
        }
        return white;
    }
}
