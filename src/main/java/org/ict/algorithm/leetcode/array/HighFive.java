package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Given a list of the scores of different students, items,
 * where items[i] = [ID-i, score-i] represents one score from a student with ID-i,
 * calculate each student's top five average.
 *
 * Return the answer as an array of pairs result, where result[j] = [ID-j, topFiveAverage-j]
 * represents the student with ID-j and their top five average.
 * Sort result by IDj in increasing order.
 *
 * A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.
 *
 *
 *
 * Example 1:
 *
 * Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
 * The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
 * Example 2:
 *
 * Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
 * Output: [[1,100],[7,100]]
 *
 *
 * Constraints:
 *
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * 1 <= ID-i <= 1000
 * 0 <= score-i <= 100
 * For each IDi, there will be at least five scores.
 * @author sniper
 * @date 14 Jun 2023
 * LC1086, Easy, frequency=12
 */
public class HighFive {

    public static void main(String[] args) {
        int[][] items = {{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}};
        int[][] items1 = {{1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{1,100},{7,100}};
        HighFive instance = new HighFive();
        int[][] res = instance.highFiveV1(items1);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * Understanding the following solution
     * @param items
     * @return
     */
    public int[][] highFiveV1(int[][] items) {
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
        for (int[] item: items) {
            map.putIfAbsent(item[0], new PriorityQueue<>(5));
            map.get(item[0]).offer(item[1]);
            if (map.get(item[0]).size() > 5) {
                map.get(item[0]).poll();
            }
        }
        int[][] res = new int[map.size()][2];
        int j = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int id = entry.getKey();
            int sum = 0;
            PriorityQueue<Integer> queue = entry.getValue();
            for (int i = 0; i < 5; i++) {
                sum += queue.poll();
            }
            int avg = sum / 5;
            res[j][0] = id;
            res[j][1] = avg;
            j++;
        }
        return res;
    }

    /**
     * class Solution:
     *     def highFive(self, items: List[List[int]]) -> List[List[int]]:
     *         d = defaultdict(list)
     *         m = 0
     *         for i, x in items:
     *             d[i].append(x)
     *             m = max(m, i)
     *         ans = []
     *         for i in range(1, m + 1):
     *             if xs := d[i]:
     *                 avg = sum(nlargest(5, xs)) // 5
     *                 ans.append([i, avg])
     *         return ans
     * @param items
     * @return
     */
    public int[][] highFive(int[][] items) {
        List<Integer>[] group = new List[1001];
        Arrays.setAll(group, k -> new ArrayList<>());
        for (int[] item : items) {
            int id = item[0];
            int score = item[1];
            group[id].add(score);
        }

        for (List<Integer> scores : group) {
            //scores.sort(Comparator.reverseOrder()); // is OK.
            scores.sort((a, b) -> (b - a));
        }

        List<int[]> res = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            List<Integer> scores = group[i];
            if (scores.isEmpty()) {
                continue;
            }
            int sumFive = 0;
            for (int j = 0; j < 5; j++) {
                sumFive += scores.get(j);
            }
            res.add(new int[]{i, sumFive / 5});
        }

        return res.toArray(new int[0][]);
    }
}
