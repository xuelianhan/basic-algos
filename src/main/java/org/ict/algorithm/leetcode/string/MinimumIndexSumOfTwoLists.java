package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner,
 * and they both have a list of favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum.
 * If there is a choice tie between answers, output all of them with no order requirement.
 * You could assume there always exists an answer.
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
 * Input:
 * ["Shogun","Tapioca Express","Burger King","KFC"]
 * ["KFC","Burger King","Tapioca Express","Shogun"]
 * Expected:
 * ["KFC","Burger King","Tapioca Express","Shogun"]
 *
 * Input:
 * ["Shogun","Tapioca Express","Burger King","KFC"]
 * ["KFC","Shogun","Burger King"]
 * Expected:
 * ["Shogun"]
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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        //String[] list2 = {"KFC","Shogun","Burger King"};
        //String[] list1 = {"C", "A", "B"};
        //String[] list2 = {"C", "B", "A"};
        String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = {"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"};
        String[] result = findRestaurant(list1, list2);
        System.out.println(Arrays.toString(result));
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        Integer minimumIdxSum = Integer.MAX_VALUE;
        List<String> result = new ArrayList<>();
        if (list1.length > list2.length) {
            find(list2, list1,  minimumIdxSum, result);
        } else {
            find(list1, list2,  minimumIdxSum, result);
        }
        String[] a = new String[result.size()];
        return result.toArray(a);
    }

    private static void find(String[] shorter, String[] longer,  Integer minimumIdxSum, List<String> result) {
        Map<String, Integer> map = IntStream.range(0, longer.length)
                .boxed()
                .collect(Collectors.toMap(i -> longer[i], i -> i + 1));
        for(int i = 0; i < shorter.length; i++) {
            Integer idx = map.get(shorter[i]);
            if (map.get(shorter[i]) != null) {
                Integer idxSum = idx + (i + 1);
                if (idxSum < minimumIdxSum) {
                    minimumIdxSum = idxSum;
                    result.clear();
                    result.add(shorter[i]);
                } else if (idxSum.equals(minimumIdxSum)) {
                    result.add(shorter[i]);
                }
            }
        }
    }
}

