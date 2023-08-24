package org.ict.algorithm.company.coupang;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sniper
 * @date 17 Aug 2023
 */
public class BinaryTreeWidth {

    public int getWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int width = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            width = Math.max(width, size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return width;
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
