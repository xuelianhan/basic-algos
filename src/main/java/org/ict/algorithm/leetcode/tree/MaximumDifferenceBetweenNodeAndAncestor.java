package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree,
 * find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Example 2:
 *
 *
 * Input: root = [1,null,2,null,0,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5000].
 * 0 <= Node.val <= 10^5
 * @author sniper
 * @date 09 Dec, 2022
 * LC1026
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    public int maxAncestorDiffV2(TreeNode root) {
        return 0;
    }

    /**
     * Time Cost 1ms
     *
     * We pass the minimum and maximum values to the children,
     * At the leaf node, we return max - min through the path from the root to the leaf.
     *
     * @author lee215
     * @param root
     * @return
     */
    public int maxAncestorDiffV1(TreeNode root) {
        return inOrderV1(root, root.val, root.val);
    }

    public int inOrderV1(TreeNode node, Integer max, Integer min) {
        if (null == node) {
            return max - min;
        }
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        return Math.max(inOrderV1(node.left, max, min), inOrderV1(node.right, max, min));
    }

    /**
     * Time Cost 196ms
     * @param root
     * @return
     */
    public int maxAncestorDiff(TreeNode root) {
        int[] res = new int[1];
        inOrder(root, root.val, root.val, res);
        return res[0];
    }

    public void inOrder(TreeNode node, Integer max, Integer min, int[] res) {
        if (null == node) {
            return;
        }
        res[0] = Math.max(res[0], Math.abs(max - node.val));
        res[0] = Math.max(res[0], Math.abs(node.val - min));
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        if (node.left != null) {
            inOrder(node.left, max, min, res);
        }
        if (node.right != null) {
            inOrder(node.right, max, min, res);
        }
    }
}
