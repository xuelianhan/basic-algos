package org.ict.algorithm.leetcode.hoyoverse;


import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Given a binary tree with n nodes, reconstruct the binary tree and return its head node.
 * For example, if you enter a preorder traversal sequence {1,2,4,7,3,5,6,8},
 * and a mid-order traversal sequence {4,7,2,1,5,3,8,6},
 * you can reconstruct the tree as shown in the figure below.
 *
 * Hints.
 * 1. vin.length == pre.length
 * 2. pre and vin have no duplicate elements
 * 3. all the elements of vin appear in pre
 * 4. only need to return the root node, the system will automatically output the whole tree to do the answer comparison
 * Data range: n ≤ 2000, the value of the node -10000 ≤ val ≤ 10000
 * Requirements: space complexity O(n), time complexity O(n)
 * Example 1
 * Input: [1,2,4,7,3,5,6,8],[4,7,2,1,5,3,8,6]
 * Return value: {1,2,3,4,#,5,6,#,7,#,#,8}
 * Description:
 * Return to the root node, the system will output the whole binary tree comparison result,
 * the reconstruction result is shown in the figure of the question
 *
 * Example 2
 * Input: [1],[1]
 * Return value: {1}
 *
 * Example 3
 * Input: [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
 * Return value: {1,2,5,3,4,6,7}
 * @author sniper
 * @date 21 Jun 2023
 * NC12, Medium
 * LC105, Medium
 */
public class ConstructBinaryTreeByPreInOrder {
    public TreeNode reConstructBinaryTree (int[] preOrder, int[] inOrder) {
        int n = preOrder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inOrder[i], i);
        }

        return build(preOrder, 0, n -1, inOrder, 0, n - 1, map);
    }

    public TreeNode build(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preOrder[preStart];
        int rootInOrderIndex = map.get(rootVal);
        int leftSize = rootInOrderIndex - inStart;

        TreeNode cur = new TreeNode(rootVal);
        cur.left = build(preOrder, preStart + 1, preStart + leftSize, inOrder, inStart, rootInOrderIndex - 1, map);
        cur.right = build(preOrder, preStart + leftSize + 1, preEnd, inOrder, rootInOrderIndex + 1, inEnd, map);
        return cur;
    }


    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
