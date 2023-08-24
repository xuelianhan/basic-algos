package org.ict.algorithm.company.coupang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement a Texas Hold'em size comparator for 4 suits.
 * Cards from smallest to largest 2,3,4,5,6,7,8,9,10, J, Q, K, A
 * Suits: w,x,y,z
 * A deck of poker is divided into 10 groups, group 1 is the largest, group 10 is the smallest, if it is the same group, according to the number of cards to be compared
 * If T1 < T2, return -1, T1 > T2 return 1, T1 equal to T2 return 0.
 *     1. Royal Flush 10, J, Q, K, A same suit
 *     2. Royal flush 5, 6, 7, 8, 9 same suit
 *     3. Four of a kind 5 5 5 5 5 5 7 Four of a kind
 *     4. huckleberry 5 5 5 6 6 three of a kind + pair (66655)
 *     5. Flush same color
 *     6. Straight 5, 6, 7, 8, 9, different suits, straights
 *     7. Three of a kind 5 5 5 5 7 8 Same number of cards
 *     8. Two pairs of 5, 5, 6, 6, 7
 *     9. Pair 5 5 6 7 8
 *     10. High card 2 6 7 9 Ace maximum, 2 minimum, different suits
 * Test case.
 * T1: ["x2", "y3", "y4", "y6", "y7"]
 * T2: ["x2", "x3", "x8", "x9", "xA"]
 * T2 > T1, return -1
 * Since both T2 and T1 belong to the 10th high card group, the comparison is based on the number of points, x8 > y4, so T2 > T1, return -1
 * T1: ["x2", "y2", "z2", "y6", "y7"]
 * T2: ["x2", "z2", "x3", "y3", "yA"]
 * T1 > T2, because T1 belongs to the seventh group of three, T2 belongs to the eighth group of two pairs, so T1 > T2, return 1
 * @author sniper
 * @date 18 Aug 2023
 */
public class TexasHoldEmSizeComparator {

    private static final Map<Integer, String> GROUP_NAMES = new HashMap<>();

    static {
        GROUP_NAMES.put(1, "Royal Flush");
        GROUP_NAMES.put(2, "Straight Flush");
        GROUP_NAMES.put(3, "Four of a Kind");
        GROUP_NAMES.put(4, "Full House");
        GROUP_NAMES.put(5, "Flush");
        GROUP_NAMES.put(6, "Straight");
        GROUP_NAMES.put(7, "Three of a Kind");
        GROUP_NAMES.put(8, "Two Pairs");
        GROUP_NAMES.put(9, "Pair");
        GROUP_NAMES.put(10, "High Card");
    }

    public int compare(String[] t1, String[] t2) {
        int group1 = getGroup(t1);
        int group2 = getGroup(t2);

        if (group1 != group2) {
            return group1 - group2;
        }

        switch (group1) {

        }
        return 0;
    }

    private int getGroup(String[] hand) {
        int[] values = new int[hand.length];
        for (int i = 0; i < hand.length; i++) {
            values[i] = 0;//getCardValue(hand[i]);
        }
        Arrays.sort(values);

        return 0;
    }

    private int compareStraightFlush(String[] t1, String[] t2) {
        int[] values1 = null;//getCardValues(t1);
        int[] values2 = null;//getCardValues(t2);

        for (int i = 0; i < values1.length; i++) {
            if (values1[i] != values2[i]) {
                return values1[i] - values2[i];
            }
        }

        return 0;
    }

    private int compareFourOfAKind(String[] t1, String[] t2) {
        return 0;
    }

}
