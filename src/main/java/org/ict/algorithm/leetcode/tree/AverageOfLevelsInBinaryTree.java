package org.ict.algorithm.leetcode.tree;


import java.util.*;

/**
 * Given the root of a binary tree,
 * return the average value of the nodes on each level in the form of an array.
 * Answers within 10^(-5) of the actual answer will be accepted.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 *
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 * this problem related LC102
 * @author sniper
 * @date 2021/12/1
 *  LC637
 */
public class AverageOfLevelsInBinaryTree {


    public List<Double> averageOfLevels(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            /* queue size indicates number of nodes at each level */
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            OptionalDouble average = temp.stream().mapToDouble(a->a).average();
            if (average.isPresent()) {
                result.add(average.getAsDouble());
            } else {
                result.add(0.0);
            }
        }
        return result;
    }
}
