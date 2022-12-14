package org.ict.algorithm.leetcode.tree;

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

    public TreeNode buildTreeV5(int[] preorder, int[] inorder) {
        //todo
        return null;
    }

    public TreeNode buildTreeV4(int[] preorder, int[] inorder) {
        //todo
        return null;
    }


    public TreeNode buildTreeV3(int[] preorder, int[] inorder) {
        //todo
        return null;
    }

    public TreeNode buildTreeV2(int[] preorder, int[] inorder) {
        //todo
        return null;
    }


    public TreeNode buildTreeV1(int[] preorder, int[] inorder) {
        //todo
        return null;
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
         * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
         * pStart:0, i:1
         *
         */
        TreeNode cur = new TreeNode(preorder[pStart]);
        cur.left = helper(preorder, pStart + 1, pStart + i - iStart, inorder, iStart, i - 1);
        cur.right = helper(preorder, pStart + i - iStart + 1, pEnd, inorder, i + 1, iEnd);
        return cur;
    }
}


