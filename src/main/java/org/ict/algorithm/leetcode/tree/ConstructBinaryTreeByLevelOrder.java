package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author sniper
 * @date 13 Dec, 2022
 */
public class ConstructBinaryTreeByLevelOrder {

    public static void main(String[] args) {
        Integer[] nums = {1, -2, -3, 1, 3, -2, null, -1};
        TreeNode root = insertLevelOrder(nums, 0);
        printLevelOrder(root);
    }

    public static TreeNode insertLevelOrder(Integer[] arr, int i) {
        TreeNode root = null;
        if (i < arr.length) {
            if (arr[i] != null) {
                root = new TreeNode(arr[i]);
            } else {
                /**
                 * Use Integer.MIN_VALUE to represent null val
                 */
                root = new TreeNode(Integer.MIN_VALUE);
            }

            root.left = insertLevelOrder(arr, 2 * i + 1);
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }

    public static void printLevelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = size; i >= 0; i--) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    continue;
                }
                if (cur.val == Integer.MIN_VALUE) {
                    /**
                     * Convert Integer.MIN_VALUE back to null
                     */
                    result.add(null);
                } else {
                    result.add(cur.val);
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        result.forEach(item -> {
            System.out.print(item + ", ");
        });
    }
}
