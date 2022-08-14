package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * There are n rings and each ring is either red, green, or blue.
 * The rings are distributed across ten rods labeled from 0 to 9.
 *
 * You are given a string rings of length 2n that describes the n rings that are placed onto the rods.
 * Every two characters in rings forms a color-position pair that is used to describe each ring where:
 *
 * The first character of the ith pair denotes the ith ring's color ('R', 'G', 'B').
 * The second character of the ith pair denotes the rod that the ith ring is placed on ('0' to '9').
 * For example, "R3G2B1" describes n == 3 rings:
 * a red ring placed onto the rod labeled 3,
 * a green ring placed onto the rod labeled 2,
 * and a blue ring placed onto the rod labeled 1.
 *
 * Return the number of rods that have all three colors of rings on them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: rings = "B0B6G0R6R0R6G9"
 * Output: 1
 * Explanation:
 * - The rod labeled 0 holds 3 rings with all colors: red, green, and blue.
 * - The rod labeled 6 holds 3 rings, but it only has red and blue.
 * - The rod labeled 9 holds only a green ring.
 * Thus, the number of rods with all three colors is 1.
 * Example 2:
 *
 *
 * Input: rings = "B0R0G0R9R0B0G0"
 * Output: 1
 * Explanation:
 * - The rod labeled 0 holds 6 rings with all colors: red, green, and blue.
 * - The rod labeled 9 holds only a red ring.
 * Thus, the number of rods with all three colors is 1.
 * Example 3:
 *
 * Input: rings = "G4"
 * Output: 0
 * Explanation:
 * Only one ring is given. Thus, no rods have all three colors.
 *
 *
 * Constraints:
 *
 * rings.length == 2 * n
 * 1 <= n <= 100
 * rings[i] where i is even is either 'R', 'G', or 'B' (0-indexed).
 * rings[i] where i is odd is a digit from '0' to '9' (0-indexed).
 *
 * @author sniper
 * @date 14 Aug, 2022
 * LC2103
 */
public class RingsAndRods {

    /**
     * Solution provided by hetvigarg
     * @param rings
     * @return
     */
    public int countPointsV3(String rings) {
        int r[] = new int[10];
        int g[] = new int[10];
        int b[] = new int[10];

        int n = rings.length();
        for(int i = 0; i < n; i += 2){
            //convert char to integer
            int a = rings.charAt(i+1)-'0';
            //wherever rings are present add it in that colour array
            if(rings.charAt(i) == 'R'){
                r[a]++;
            }
            else if(rings.charAt(i) == 'G'){
                g[a]++;
            }
            else if(rings.charAt(i) == 'B'){
                b[a]++;
            }
        }
        
        //if all three rings are present increase count
        int count=0;
        for(int j = 0; j < 10; j++){
            if(r[j] > 0 && g[j] > 0 && b[j] > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Code colors with bits (1, 2 and 4),
     * and use the OR operation for each rod.
     * Return number of rods that have all 3 bits (colors) set
     * which value of 7.
     * @param rings
     * @return
     */
    public int countPointsV2(String rings) {
        int[] rods = new int[10];
        for (int i = 0; i < rings.length() - 1; i += 2) {
            int c = rings.charAt(i + 1) - '0';
            if(rings.charAt(i) == 'R') {
                rods[c] |= (1 << 0);
            }
            if(rings.charAt(i) == 'G') {
                rods[c] |= (1 << 1);
            }
            if(rings.charAt(i) == 'B') {
                rods[c] |= (1 << 2);
            }
        }
        int total = 0;
        for(int i = 0; i < rods.length; i++){
            if(rods[i] == 7)
                total++;
        }
        return total;
    }

    public int countPoints(String rings) {
        AtomicInteger res = new AtomicInteger();
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        for (int i = rings.length() - 1; i >=0; i--) {
            if (Character.isDigit(rings.charAt(i))) {
                map.put(rings.charAt(i), map.getOrDefault(rings.charAt(i), new HashSet<>()));
            }
            if (Character.isLetter(rings.charAt(i))) {
                Set<Character> set = map.get(rings.charAt(i + 1));
                set.add(rings.charAt(i));
                map.put(rings.charAt(i + 1), set);
            }
        }
        map.forEach((k, v) -> {
            if (v.size() == 3) {
                res.getAndIncrement();
            }
        });
        return res.get();
    }
}
