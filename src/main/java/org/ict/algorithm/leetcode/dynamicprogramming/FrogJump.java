package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * A frog is crossing a river.
 * The river is divided into some number of units, and at each unit,
 * there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order,
 * determine if the frog can cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
 *
 * If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units.
 * The frog can only jump in the forward direction.
 *
 *
 * Example 1:
 * Input: stones = [0,1,3,5,6,8,12,17]
 * Output: true
 * Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone,
 * then 2 units to the 3rd stone,
 * then 2 units to the 4th stone,
 * then 3 units to the 6th stone,
 * 4 units to the 7th stone,
 * and 5 units to the 8th stone.
 *
 * Example 2:
 * Input: stones = [0,1,2,3,4,8,9,11]
 * Output: false
 * Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.
 *
 *
 * Constraints:
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 2^31 - 1
 * stones[0] == 0
 * stones is sorted in a strictly increasing order.
 * @author sniper
 * @date 27 Aug 2023
 * LC403, Hard,
 */
public class FrogJump {

    public boolean canCrossV2(int[] stones) {
        //todo
        return false;
    }

    public boolean canCrossV1(int[] stones) {
        //todo
        return false;
    }

    /**
     * Time Cost 37ms
     * Use map to represent a mapping from the stone (not index) to the steps that can be taken from this stone.
     * so this will be
     * [0,1,3,5,6,8,12,17]
     * {17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4, 5]}
     * Notice that no need to calculate the last stone.
     * On each step, we look if any other stone can be reached from it,
     * if so, we update that stone's steps by adding step, step + 1, step - 1.
     * If we can reach the final stone, we return true.
     * No need to calculate to the last stone.
     * @author quincyhehe
     * @see <a href="https://leetcode.com/problems/frog-jump/solutions/88824/very-easy-to-understand-java-solution-with-explanations/"></a>
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        if (null == stones || stones.length == 0) {
            return true;
        }
        int n = stones.length;
        Map<Integer, HashSet<Integer>> map = new HashMap<>(n);
        map.put(0, new HashSet<>());
        map.get(0).add(1);
        for (int i = 1; i < n; i++) {
            map.put(stones[i], new HashSet<>() );
        }

        for (int i = 0; i < n - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[n - 1]) {
                    return true;
                }
                HashSet<Integer> set = map.get(reach);
                if (set != null) {
                    set.add(step);
                    if (step - 1 > 0) {
                        set.add(step - 1);
                    }
                    set.add(step + 1);
                }
            }
        }

        return false;
    }
}
