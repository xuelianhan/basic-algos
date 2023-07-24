package org.ict.algorithm.hackerrank.geometry;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Given  two-dimensional points in space, determine whether they lie on some vertical or horizontal line.
 * If yes, print YES; otherwise, print NO.
 *
 * Input Format
 *
 * The first line contains a single positive integer, , denoting the number of points.
 * Each line  of  subsequent lines contain two space-separated integers detailing the respective values of,
 * and  (i.e., the coordinates of the  point).
 *
 * Constraints
 *
 * Output Format
 *
 * Print YES if all points lie on some horizontal or vertical line; otherwise, print NO.
 *
 * Sample Input 0
 *
 * 5
 * 0 1
 * 0 2
 * 0 3
 * 0 4
 * 0 5
 * Sample Output 0
 *
 * YES
 * Explanation 0
 *
 * All points lie on a vertical line.
 *
 * Sample Input 1
 *
 * 5
 * 0 1
 * 0 2
 * 1 3
 * 0 4
 * 0 5
 * Sample Output 1
 *
 * NO
 * Explanation 1
 *
 * The points do not all form a horizontal or vertical line.
 * @author sniper
 * @date 24 Jul 2023
 */
public class PointsOnALine {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Example-1:
     * 2
     * 5 -5
     * -4 -5
     * Expected YES
     *
     * Example-2:
     * 5
     * 4 4
     * 4 -8
     * 4 -4
     * 4 -10
     * 4 -3
     * Expected YES
     */
    public void printOnALine() {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        for (int nItr = 0; nItr < n; nItr++) {
            String[] xy = scanner.nextLine().split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            xSet.add(x);
            ySet.add(y);
        }
        if ((xSet.size() == 1 || ySet.size() == 1) && n >= 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
