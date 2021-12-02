package org.ict.algorithm.leetcode.tree;

/**
 * Given the roots of two binary trees root and subRoot,
 * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that
 * consists of a node in tree and all of this node's descendants.
 *
 * The tree tree could also be considered as a subtree of itself.
 *
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 *
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 *
 * Constraints:
 *
 * The number of nodes in the root tree is in the range [1, 2000].
 * The number of nodes in the subRoot tree is in the range [1, 1000].
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 * @author sniper
 *
 * LC572 is related LC100
 *
 */
public class SubtreeOfAnotherTree {

    /**
     * Input:
     * [3,4,5,1,2,null,null,null,null,0]
     * [4,1,2]
     *
     * Expected:
     * false
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtreeV1(TreeNode root, TreeNode subRoot) {
        if (null == root) {
            return false;
        }
        if (SameBinaryTree.isSameTree(root, subRoot)) {
            return true;
        }
        return (isSubtreeV1(root.left, subRoot) || isSubtreeV1(root.right, subRoot));
    }




}
