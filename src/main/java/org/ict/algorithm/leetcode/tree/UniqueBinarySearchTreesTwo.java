package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n.
 * Return the answer in any order.
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * Input: n = 1
 * Output: [[1]]
 *
 * Constraints:
 * 1 <= n <= 8
 *
 * @see <a href="https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/"></a>
 * @author sniper
 * @date 2021/12/2
 * LC95
 */
public class UniqueBinarySearchTreesTwo {

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return null;
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }
        /**
         * iterating through all values from start to end
         */
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList  = generateTrees(start, i-1);
            List<TreeNode> rightList = generateTrees(i + 1, end);
            /**
             * now looping through all left and right subtrees
             * and connecting them to ith root  below
             */
            for (int j = 0; j < leftList.size(); j++) {
                TreeNode left = leftList.get(j);
                for (int k = 0; k < rightList.size(); k++) {
                    TreeNode right = rightList.get(k);
                    /**
                     * Let value i as root
                     */
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
