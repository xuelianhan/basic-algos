package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is
 * the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 * LC543
 *
 * [4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]
 * expected:8
 */
public class DiameterOfBinaryTree {



    public static void main(String[] args) {
        /*
        TreeNode root = new TreeNode();
        TreeNode four = new TreeNode();
        TreeNode five = new TreeNode();
        four.val = 4;
        five.val = 5;
        TreeNode two = new TreeNode();
        two.val = 2;
        two.left = four;
        two.right = five;

        TreeNode three = new TreeNode();
        three.val = 3;

        root.val = 1;
        root.left = two;
        root.right = three;
        int depth = diameterOfBinaryTree(root);
        System.out.println(depth);*/


        TreeNode root = new TreeNode();
        TreeNode two = new TreeNode();
        two.val = 2;
        root.val = 1;
        root.right = two;
        int result = diameterOfBinaryTreeV1(root);
        System.out.println("result:"+result);
        System.out.println("max:" + max);
    }

    /**
     * It took me a while to figure this out.
     * The code is correct, but the explanation is clearly wrong.
     * So although the longest path doesn't have to go through the root node,
     * it has to pass the root node of some subtree of the tree
     * (because it has to be from one leaf node to another leaf node,
     * otherwise we can extend it for free).
     * The longest path that passes a given node as the ROOT node is T = left_height+right_height.
     * So you just calculate T for all nodes and output the max T.
     * @param root
     * @return
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        Integer maxPath = 0;
        maxDepth(root, maxPath);
        return maxPath;
    }

    /**
     * The question can be solved by small modification to program of Height of tree.
     * The idea is quite simple. Max value of Height(leftSubtree)+Height(rightSubtree) (at any node ) is the diameter.
     * Keep track of maxium diameter duing traversal and find the height of the tree.
     * @param root
     * @param maxPath
     * @return
     */
    public static int maxDepth(TreeNode root, Integer maxPath) {
        if (null == root) {
            return 0;
        }
        int left = maxDepth(root.left, maxPath);
        int right = maxDepth(root.right, maxPath);
        //This line maintains the max diameter.
        maxPath = Math.max(maxPath, left + right);
        return Math.max(left, right) + 1;
    }

    public static int maxDepthBFS(TreeNode root) {
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

    /**
     * Global variables may cause issue when running test cases.
     */
    private static int max = 0;

    public static int diameterOfBinaryTreeV1(TreeNode root) {
        maxDepthV1(root);
        return max;
    }

    private static int maxDepthV1(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = maxDepthV1(root.left);
        int right = maxDepthV1(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
