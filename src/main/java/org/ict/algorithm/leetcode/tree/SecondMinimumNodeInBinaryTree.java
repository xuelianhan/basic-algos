package org.ict.algorithm.leetcode.tree;

import org.ict.algorithm.leetcode.breadthfirstsearch.BinaryTreeLevelOrderTraversal;

import java.util.*;

/**
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node.
 * If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes.
 * More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 *
 * Given such a binary tree,
 * you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Input: root = [2,2,5,null,null,5,7]
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 * Input: root = [2,2,2]
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 25].
 * 1 <= Node.val <= 2^31 - 1
 * root.val == min(root.left.val, root.right.val) for each internal node of the tree.
 *
 * @author sniper
 * @date 2021/12/1
 * LC671
 */
public class SecondMinimumNodeInBinaryTree {

    public int findSecondMinimumValue(TreeNode root) {
        if (null == root) {
            return -1;
        }
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            /* queue size indicates number of nodes at each level */
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                treeMap.put(cur.val, cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        // Get entry set of the TreeMap
        // using entrySet method
        Set<Map.Entry<Integer, Integer>> entrySet = treeMap.entrySet();

        // Converting entrySet to ArrayList
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(entrySet);

        return (treeMap.size() > 1? entryList.get(1).getKey() : -1);
    }
}
