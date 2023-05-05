package org.ict.algorithm.leetcode.greedy;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * In the world of Dota2, there are two parties: the Radiant and the Dire.
 * The Dota2 senate consists of senators coming from two parties.
 * Now the Senate wants to decide on a change in the Dota2 game.
 * The voting for this change is a round-based procedure.
 * In each round, each senator can exercise one of the two rights:
 * Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
 * Announce the victory: If this senator found the senators who still have rights to vote are all from the same party,
 * he can announce the victory and decide on the change in the game.
 * Given a string senate representing each senator's party belonging.
 * The character 'R' and 'D' represent the Radiant party and the Dire party,
 * then if there are n senators, the size of the given string will be n.
 * The round-based procedure starts from the first senator to the last senator in the given order.
 * This procedure will last until the end of voting.
 * All the senators who have lost their rights will be skipped during the procedure.
 * Suppose every senator is smart enough and will play the best strategy for his own party.
 * Predict which party will finally announce the victory and change the Dota2 game.
 * The output should be "Radiant" or "Dire".
 *
 * Example 1:
 * Input: senate = "RD"
 * Output: "Radiant"
 * Explanation:
 * The first senator comes from Radiant, and he can just ban the next senator's right in round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And in round 2,
 * the first senator can just announce the victory since he is the only guy in the senate who can vote.
 *
 * Example 2:
 * Input: senate = "RDD"
 * Output: "Dire"
 * Explanation:
 * The first senator comes from Radiant, and he can just ban the next senator's right in round 1.
 * And the second senator can't exercise any rights anymore since his right has been banned.
 * And the third senator comes from Dire, and he can ban the first senator's right in round 1.
 * And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
 *
 * Constraints:
 * n == senate.length
 * 1 <= n <= 10^4
 * senate[i] is either 'R' or 'D'.
 *
 * @author sniper
 * @date 05 May 2023
 * LC649, Medium
 */
public class Dota2Senate {

    /**
     * Understanding the following solution
     *
     * We can use two queues, pushing the position of the respective camp into different queues.
     * Each time from the two queues, we take a position out to see the size of the relationship,
     * the small one in the front indicates that it can ban off the following one.
     * So we have to add the smaller one back into the queue,
     * but can not directly add at the original position,
     * because the next round before his turn to Ban,
     * so we have to add an n, and then push into the queue.
     * The loop exit while one of the queue is empty,
     * and we return to the camp that its queue is not empty.
     *
     * e.g. senate = "RD"
     * q1:0, q2:1
     * 0 < 1, q1:2
     * q1.size():1, q2.size():0
     * return "Radiant"
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                q1.offer(i);
            } else {
                q2.offer(i);
            }
        }
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int i = q1.poll();
            int j = q2.poll();
            if (i < j) {
                q1.offer(i + n);
            } else {
                q2.offer(j + n);
            }
        }
        return (q1.size() > q2.size()) ? "Radiant" : "Dire";
    }
}
