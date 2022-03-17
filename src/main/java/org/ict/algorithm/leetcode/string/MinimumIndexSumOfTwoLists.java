package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner,
 * and they both have a list of favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum.
 * If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
 *
 *
 *
 * Example 1:
 *
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"],
 * list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * Example 2:
 *
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"],
 * list2 = ["KFC","Shogun","Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 *
 *
 * Constraints:
 *
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] and list2[i] consist of spaces ' ' and English letters.
 * All the stings of list1 are unique.
 * All the stings of list2 are unique.
 * @author sniper
 * @date 2022/3/17
 * LC599
 */
public class MinimumIndexSumOfTwoLists {

    public String[] findRestaurant(String[] list1, String[] list2) {
        int minimumIdxSum = Integer.MAX_VALUE;
        if (list1.length > list2.length) {
            Map<String, Integer> map = IntStream.range(0, list1.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> list1[i], i -> i));
            for(String s : list2) {
                if (map.get(s) != null) {
                    int idxSum =
                } else {

                }
            }
        } else {
            Map<Integer, String> map = IntStream.range(0, list2.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> i, i -> list2[i]));

        }
        return null;
    }
}
