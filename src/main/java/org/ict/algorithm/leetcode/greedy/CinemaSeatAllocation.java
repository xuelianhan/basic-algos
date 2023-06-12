package org.ict.algorithm.leetcode.greedy;

import java.util.*;

/**
 *   1 2 3   4 5 6 7   8 9 10
 * 1 _______________________
 * 2 _______________________
 * 3 _______________________
 *   .......................
 * n _______________________
 *
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row,
 * labelled from 1 to 10 as shown in the figure above.
 * Given the array reservedSeats containing the numbers of seats already reserved,
 * for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
 * Return the maximum number of four-person groups you can assign on the cinema seats.
 * A four-person group occupies four adjacent seats in one single row.
 * Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent,
 * but there is an exceptional case on which an aisle split a four-person group,
 * in that case, the aisle split a four-person group in the middle, which means to have two people on each side.
 *
 *
 * Example 1:
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four groups,
 * where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
 *
 * Example 2:
 * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 *
 * Example 3:
 * Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4
 *
 *
 * Constraints:
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10*n, 10^4)
 * reservedSeats[i].length == 2
 * 1 <= reservedSeats[i][0] <= n
 * 1 <= reservedSeats[i][1] <= 10
 * All reservedSeats[i] are distinct.
 * @author sniper
 * @date 11 Jun 2023
 * LC1386, Medium, frequency=17
 */
public class CinemaSeatAllocation {

    public static void main(String[] args) {
        int n = 3;
        int[][] reservedSeats = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        CinemaSeatAllocation instance = new CinemaSeatAllocation();
        instance.maxNumberOfFamilies(n, reservedSeats);
    }

    /**
     * From the beginning I used a Map<Integer, Set<Integer>> to represent reserved seats as a graph.
     * But then I realized that we can use a bit vector instead of Set<Integer>.
     * Also, seats 2,3,4,5 can be represented as (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5) = 60,
     * for example.
     * So I use this value to check whether the seats 2,3,4,5 are available
     * when traversing the graph (togather with 6,7,8,9 and 4,5,6,7).
     * The author started the seat indexing from the right.
     * You can easily start from the left (we are looking at the problem from different angles).
     * ---------------------
     * Two confusing point
     *
     * The author starts the seat with index 0 instead of 1 which the description does.
     * For the sake of consistency, I will follow the author's logic with the first seat as 0.
     * The author started the seat indexing from the right (opposite with the description but the answer is still the same)
     * 60 binary is 0000111100: This means seats 2 ~ 5 are available
     * 960 binary is 1111000000: This means seats 6 ~ 9 are available
     * 240 binary is 0011110000: This means seats 4 ~ 7 are available
     * --------------------------------------------------------------
     * The lionkingeatapple's comments are as following:
     * I want to add some explanations for the last line max + 2 * (n - graph.size()).
     * We don't necessarily have reserved seats for each row.
     * If we don't have any reserved seats in that particular row,
     * we can maximum possibly allocate 2 families with a group of 4 people each.
     * For example, there are seats we can allocate for these two families with seats 2, 3, 4, 5 and seats 6, 7, 8, 9,
     * seat 1 and seat 10 does not matter.
     * From the code, max means the maximum allocations we can do for the rows with reserved seats.
     * After that, we also need to count the rows won't have any reserved seats.
     * The graph.size() contains all rows which have seats reserved. n - graph.size() contains all rows don't have any seats reserved,
     * and we can allocate 2 families with a group of 4 people each, so 2 * (n - graph.size()) is the total number of allocations
     * we can do for rows don't have any seats reserved.
     * Finally, we have maximum number of allocations:
     * Maximum Allocations = Total number allocations for rows
     * with reserved seats + Total number allocations for rows don't have any reserved seats
     * = max + 2 * (n - graph.size()).
     * ---------------------------------------------------------------
     * @see <a href="https://leetcode.com/problems/cinema-seat-allocation/solutions/546451/java-straightforward-solution-bitwise/"></a>
     * @author OPyrohov
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamiliesV4(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        for (int[] reserved : reservedSeats) {
            int row = reserved[0];
            int col = reserved[1];
            /**
             * Create a bit vector of reserved seats
             */
            graph.put(row, graph.getOrDefault(row, 0) | (1 << col));
        }
        int max = 0;
        for (int row : graph.keySet()) {
            int reserved = graph.get(row);
            int cnt = 0;
            /**
             * Check if seats 2,3,4,5 are available(0b00000111100, from right side view)
             * (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5) = 60
             */
            if ((reserved & 60) == 0) cnt += 1;
            /**
             * Check if seats 6,7,8,9 are available(0b1111000000, from right side view)
             * (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9) = 960
             */
            if ((reserved & 960) == 0) cnt += 1;
            /**
             * Check if seats 4,5,6,7 are available(0b0011110000, from right side view)
             * (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7) = 240
             */
            if ((reserved & 240) == 0 && cnt == 0) {
                cnt = 1;
            }
            max += cnt;
        }
        return max + 2 * (n - graph.size());
    }

    /**
     * Bit-Vector Solution
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamiliesV3(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        for (int[] e : reservedSeats) {
            int i = e[0];
            int j = e[1];
            graph.merge(i, 1 << (10 - j), (x, y) -> x | y);
        }
        /**
         * From left to right angle, index start from 1
         * 480 = 0b0111100000, it means seats 2,3,4,5
         * 30  = 0b0000011110, it means seats 6,7,8,9
         * 120 = 0b0001111000, it means seats 4,5,6,7
         */
        int[] masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int res = (n - graph.size()) * 2;
        for (int x : graph.values()) {
            for (int mask : masks) {
                if ((x & mask) == 0) {
                    x |= mask;
                    ++res;
                }
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Bit-Vector Solution
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamiliesV2(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        for (int[] e : reservedSeats) {
            int i = e[0];
            int j = e[1];
            graph.put(i, graph.getOrDefault(i, 0) | 1 << (10 - j));
        }

        int res = 2 * (n - graph.size());
        for (int i : graph.keySet()) {
            int seats = graph.get(i);
            if ((seats & 0b0111111110) == 0) {
                res += 2;
            } else if ((seats & 0b0111100000) == 0
                    || (seats & 0b0001111000) == 0
                    || (seats & 0b0000011110) == 0) {
                res += 1;
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * HashSet Solution
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamiliesV1(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] e : reservedSeats) {
            int i = e[0];
            int j = e[1];
            graph.putIfAbsent(i, new HashSet<>());
            graph.get(i).add(j);
        }

        int res = 2 * (n - graph.size());
        for (int i : graph.keySet()) {
            Set<Integer> set = graph.get(i);
            if (noneOfRange(set, 2, 9)) {
                res += 2;
            } else if (noneOfRange(set, 2, 5)
                    || noneOfRange(set, 4, 7)
                    || noneOfRange(set, 6, 9)) {
                res += 1;
            }
        }
        return res;
    }

    private boolean noneOfRange(Set<Integer> set, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (set.contains(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Understanding the following solution
     * --------------------------------------
     * We use three numbers to record whether the left, the middle or the right is occupied or not.
     * First, we record whether the left, middle or right is occupied or not using a set as the value in the dictionary.
     * For n rows, the maximum number of families that can sit together are 2*n.
     * Then we iterate through the dictionary, if all three positions in the row was blocked, the total cnt should -2.
     * If less than 3 positions was blocked, the total cnt should -1.
     * ------------------------------------
     * Here is my mere understanding of this masterpiece of conciseness.
     * Author did not explain len()==2 and len()==1 details but here it is.
     * 0,1,2 are the three modes to fit a family, but not available due to occupied seats.
     * default put 2 families every row, for each row with occupants (meaning no rows have len()==0 with this syntax for i in seats )
     * if len() ==3, nothing available, -2
     * if len() ==2, only one way to fit a family, -1
     * if len()==1, two ways to fit a family,
     * but it can only fit one family as putting family there will block the other option, -1
     * It would be easier to understood with better variable naming, but I still learnt a ton.
     * ------------------------------------
     * class Solution:
     * 	def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
     * 		seats = collections.defaultdict(set)
     *
     * 		for i,j in reservedSeats:
     * 			if j in [2,3,4,5]:
     * 				seats[i].add(0)
     * 			if j in [4,5,6,7]:
     * 				seats[i].add(1)
     * 			if j in [6,7,8,9]:
     * 				seats[i].add(2)
     * 		res = 2*n
     * 		for i in seats:
     * 			if len(seats[i]) == 3:
     * 				res -= 2
     * 			else:
     * 				res -= 1
     *
     * 		return res
     * @author lichuan199010
     * @param n
     * @param reservedSeats
     * @return
     */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> seats = new HashMap<>();
        Set<Integer> set1 = new HashSet<>(Arrays.asList(2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(6, 7, 8, 9));
        for (int[] e : reservedSeats) {
            int i = e[0];
            int j = e[1];
            seats.putIfAbsent(i, new HashSet<>());
            if (set1.contains(j)) {
                seats.get(i).add(0);
            }
            if (set2.contains(j)) {
                seats.get(i).add(1);
            }
            if (set3.contains(j)) {
                seats.get(i).add(2);
            }
        }

        int res = 2 * n;
        for (Map.Entry<Integer, Set<Integer>> entry : seats.entrySet()) {
            if (entry.getValue().size() == 3) {
                res -= 2;
            } else if (entry.getValue().size() == 2 || entry.getValue().size() == 1) {
                res -= 1;
            } else if (entry.getValue().size() == 0) {
                res -= 0;
            }
        }
        return res;
    }
}
