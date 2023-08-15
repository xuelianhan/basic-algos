package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree,
 * construct a 0-indexed m x n string matrix res that represents a formatted layout of the tree.
 * The formatted layout matrix should be constructed using the following rules:
 *
 * The height of the tree is height and the number of rows m should be equal to height + 1.
 * The number of columns n should be equal to 2^(height+1) - 1.
 * Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 * For each node that has been placed in the matrix at position res[r][c],
 * place its left child at res[r+1][c-2^(height-r-1)] and its right child at res[r+1][c+2^(height-r-1)].
 * Continue this process until all the nodes in the tree have been placed.
 * Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 *
 * Example 1:
 *      1
 *     /
 *    2
 *
 * Input: root = [1,2]
 * Output:
 * [["","1",""],
 *  ["2","",""]]
 *
 * Example 2:
 *       1
 *      / \
 *     2   3
 *      \
 *       4
 *
 * Input: root = [1,2,3,null,4]
 * Output:
 * [["","","","1","","",""],
 *  ["","2","","","","3",""],
 *  ["","","4","","","",""]]
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 2^10].
 * -99 <= Node.val <= 99
 * The depth of the tree will be in the range [1, 10].
 * @author sniper
 * @date 15 Aug 2023
 * LC655, Medium
 */
public class PrintBinaryTree {

    /**
     * Time Cost 1ms
     * Iterative level order traversal with Queue
     * @param root
     * @return
     */
    public List<List<String>> printTreeV1(TreeNode root) {
        int h = maxHeight(root);
        int w = (int)Math.pow(2, h) - 1;
        int m = h;
        int n = w;

        List<List<String>> res = new ArrayList<>();
        List<String> row = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            row.add("");
        }
        for (int i = 0; i < m; i++) {
            res.add(new ArrayList<>(row));
        }

        int curRow = -1;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        Queue<int[]> indexQueue = new LinkedList<>();
        indexQueue.offer(new int[] {0, n - 1});
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            curRow++;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int[] idx = indexQueue.poll();
                if (node == null) {
                    continue;
                }
                int left = idx[0];
                int right = idx[1];
                int mid = left + (right - left) / 2;

                res.get(curRow).set(mid, Integer.toString(node.val));
                nodeQueue.offer(node.left);
                nodeQueue.offer(node.right);
                indexQueue.offer(new int[] {left, mid});
                indexQueue.offer(new int[] {mid + 1, right});
            }
        }
        return res;
    }

    /**
     * Recursive Solution
     * Time Cost 1ms
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        int h = maxHeight(root);
        int w = (int)Math.pow(2, h) - 1;
        int m = h;
        int n = w;

        List<List<String>> res = new ArrayList<>();
        List<String> row = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            row.add("");
        }
        for (int i = 0; i < m; i++) {
            res.add(new ArrayList<>(row));
        }
        helper(root, 0, 0, n - 1, res);
        return res;
    }

    private void helper(TreeNode node, int row,  int left, int right, List<List<String>> res) {
        if (node == null) {
            return;
        }
        int mid = (left + right) / 2;
        res.get(row).set(mid, Integer.toString(node.val));
        helper(node.left, row + 1, left, mid - 1, res);
        helper(node.right, row + 1, mid + 1, right, res);
    }

    private int maxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }

}
