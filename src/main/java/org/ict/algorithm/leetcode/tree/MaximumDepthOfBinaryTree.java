package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = Math.max(0, maxDepth(root.left));
        int right = Math.max(0, maxDepth(root.right));
        return Math.max(left, right) + 1;
    }

    public int maxDepthBFS(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // here control the save level element push into the queue, can be replaced with while(size-- > 0) loop.
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            count++;
        }
        return count;
    }
}
