package org.ict.algorithm.company.coupang;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the credits for 10 classes and the range of credits you want to take, find all possible combinations of classes. For example
 *    C1 2 credits
 *    C2 3 credits
 *    C3 4 credits
 *    C4 4 credits
 *    C5 5 credits
 *    Range is [9, 18].
 *    Returns {C1, C2, C3, C4}, etc. as long as the total number of credits is [9, 18].
 * @author sniper
 * @date 17 Aug 2023
 */
public class FindCombinationsOfClasses {

    public List<List<Integer>> findCombinations(int[] credits, int minCredits, int maxCredits) {
        List<List<Integer>> combinations = new ArrayList<>();

        // Recursively find all combinations of classes.
        dfs(combinations, new ArrayList<>(), credits, 0, minCredits, maxCredits);

        return combinations;
    }

    private void dfs(List<List<Integer>> combinations, List<Integer> currentCombination, int[] credits, int index, int minCredits, int maxCredits) {
        // If the current combination has the required number of credits, then add it to the list of combinations.
        if (currentCombination.size() == maxCredits - minCredits && credits[index] >= minCredits) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        // Recursively explore all possible combinations by adding the current element to the current combination.
        if (index < credits.length && credits[index] >= minCredits) {
            currentCombination.add(credits[index]);
            dfs(combinations, currentCombination, credits, index + 1, minCredits, maxCredits);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
