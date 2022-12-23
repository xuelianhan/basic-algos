package org.ict.algorithm.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 *
 * @author sniper
 * @date 08 Dec, 2022
 * LC105
 */
public class ConstructBinaryTreeByPreorderInorder {


    /**
     * Stack Iterative Solution
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeV3(int[] preorder, int[] inorder) {
        return null;
    }


    /**
     * Understanding the following solution
     *
     * Time Cost 2ms
     * After converting inorder[] to the HashMap,
     * we don't need inorder[] anymore in the helper function.
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeV2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        int[] preorderIndex = new int[1];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return helpV2(preorder, 0, n - 1, indexMap, preorderIndex);
    }

    public TreeNode helpV2(int[] preorder, int pStart, int pEnd, Map<Integer, Integer> indexMap, int[] preorderIndex) {
        if (pStart > pEnd) {
            return null;
        }
        int val = preorder[preorderIndex[0]++];
        int i = indexMap.get(val);
        TreeNode cur = new TreeNode(val);
        cur.left = helpV2(preorder, pStart, i - 1, indexMap, preorderIndex);
        cur.right = helpV2(preorder, i + 1, pEnd, indexMap, preorderIndex);
        return cur;
    }


    /**
     *
     * Understand the following Solution
     * Time Cost 2ms
     * Using indexMap to speed up.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeV1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return helperV1(preorder, 0, n - 1, inorder, 0, n - 1, indexMap);
    }

    public TreeNode helperV1(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, Map<Integer, Integer> indexMap) {
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        int i = indexMap.get(preorder[pStart]);
        /**
         * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         * pStart:0, i:1, iStart:0, (i - iStart) is the size of the cur's left subtree.
         * so, in preorder,
         * left tree starts from (pStart + 1), ends at (pStart + i - iStart)
         * right tree starts from (pStart + i - iStart) + 1, ends at pEnd.
         */
        TreeNode cur = new TreeNode(preorder[pStart]);
        cur.left = helperV1(preorder, pStart + 1, pStart + i - iStart, inorder, iStart, i - 1, indexMap);
        cur.right = helperV1(preorder, pStart + i - iStart + 1, pEnd, inorder, i + 1, iEnd, indexMap);
        return cur;
    }

    /**
     * Understand the following Solution
     *
     * Time Cost 3ms
     * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * root:3, left:9, right:15, 20, 7
     * root:9, skip
     * root:20, left:15, right:7
     * ---------------------------
     * 0  1   2   3  4
     * 3  9  20  15  7
     * 9 [3] 15  20  7
     *
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return helper(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    public TreeNode helper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        int i = 0;
        for (i = iStart; i <= iEnd; i++) {
            if (preorder[pStart] == inorder[i]) {
                break;
            }
        }
        /**
         * (i - iStart) is the size of the cur's left subtree.
         * so, in preorder,
         * left tree starts from (pStart + 1), ends at (pStart + i - iStart)
         * right tree starts from (pStart + i - iStart) + 1, ends at pEnd.
         * e.g.preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         * pStart:0, i:1, iStart:0,
         *
         */
        TreeNode cur = new TreeNode(preorder[pStart]);
        cur.left = helper(preorder, pStart + 1, pStart + i - iStart, inorder, iStart, i - 1);
        cur.right = helper(preorder, pStart + i - iStart + 1, pEnd, inorder, i + 1, iEnd);
        return cur;
    }
}


