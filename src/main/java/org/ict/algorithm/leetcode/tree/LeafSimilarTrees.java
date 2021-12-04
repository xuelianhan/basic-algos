package org.ict.algorithm.leetcode.tree;

import org.ict.algorithm.leetcode.breadthfirstsearch.BinaryTreeLevelOrderTraversal;

import java.util.*;

/**
 * Consider all the leaves of a binary tree,
 * from left to right order,
 * the values of those leaves form a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * Example 2:
 *
 * Input: root1 = [1], root2 = [1]
 * Output: true
 * Example 3:
 *
 * Input: root1 = [1], root2 = [2]
 * Output: false
 * Example 4:
 *
 * Input: root1 = [1,2], root2 = [2,2]
 * Output: true
 *
 * Example 5:
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 *
 * Constraints:
 *
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
 *
 *
 * @author sniper
 * @date 2021/12/1
 * LC872
 */
public class LeafSimilarTrees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = inOrder(root1);
        List<Integer> list2 = inOrder(root2);
        if (list1.size() != list2.size()) {
            return false;
        }
        for(int i = 0; i < list1.size(); i++) {
            int x = list1.get(i);
            int y = list2.get(i);
            if (x != y) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> inOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                // Only collect the leaf node value.
                if (node.left == null && node.right == null) {
                    // Add after all left children
                    result.add(node.val);
                }
                cur = node.right;
            }
        }
        return result;
    }

}
