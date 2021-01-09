package org.ict.algorithm.leetcode.tree;

import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 *
 * LC257
 */
public class BinaryTreePaths {

    public List<String> bfsBinaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<PathNode> queue = new LinkedList<>();
        PathNode pathNode = new PathNode(root, "");
        queue.offer(pathNode);
        while (!queue.isEmpty()) {
            PathNode curPathNode = queue.poll();
            TreeNode cur = curPathNode.treeNode;
            if (null == cur.left && null == cur.right) {
                result.add(curPathNode.path + cur.val);
            }
            if (null != cur.left) {
                PathNode node = new PathNode(cur.left, curPathNode.path + cur.val + "->");
                queue.offer(node);
            }
            if (null != cur.right) {
                PathNode node = new PathNode(cur.right, curPathNode.path + cur.val + "->");
                queue.offer(node);
            }
        }
        return result;
    }

    private static class PathNode {
        private TreeNode treeNode;

        private String path;

        public PathNode(TreeNode treeNode, String path) {
            this.treeNode = treeNode;
            this.path = path;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (null != root) {
            helper(root, "", result);
        }
        return result;
    }

    private void helper(TreeNode root, String path, List<String> result) {
        if (null == root.left && null == root.right) {
            result.add(path  + root.val);
        }
        if (null != root.left) {
            helper(root.left, path + root.val + "->", result);
        }
        if (null != root.right) {
            helper(root.right, path + root.val + "->", result);
        }
    }
}
