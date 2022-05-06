package org.ict.algorithm.leetcode.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * Input a number array and output all the permutations of it.
 * e.g.
 * Input: [1,2,3]
 * Output:123,132,213,231,312,321
 * @author sniper
 * @date 2022/5/6
 */
public class PermutationOfNumbers {

    public static void main(String[] args) {
        int[] numbers = new int[] {1,2,3};
        List<List<Integer>> result = permutate(numbers);
        result.forEach(item -> {
            System.out.println(item);
        });
    }

    public static List<List<Integer>> permutate(int[] numbers) {
        /**
         * 1.Use LinkedList instead of ArrayList
         */
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(numbers, track, result);
        return result;
    }

    public static void backtrack(int[] numbers, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == numbers.length) {
            /**
             * Use new LinkedList to wrapper track instead of adding track into result list directly
             */
            result.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (track.contains(numbers[i])) {
                /**
                 * Skip the number which has been accessed.
                 */
                continue;
            }
            track.add(numbers[i]);
            backtrack(numbers, track, result);
            track.removeLast();
        }
    }
}
