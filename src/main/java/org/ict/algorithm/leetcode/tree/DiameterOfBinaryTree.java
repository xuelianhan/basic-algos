package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
        System.out.println(depth);


        /*TreeNode root = new TreeNode();
        TreeNode two = new TreeNode();
        two.val = 2;
        root.val = 1;
        root.right = two;
        int result = diameterOfBinaryTree(root);
        System.out.println("result:"+result);*/
    }

    /**
     * Iterative solution with post-order traversal
     * The idea is to use Post order traversal which means make sure the node is there
     * till the left and right children are processed
     * That's the reason you use peek method in the stack to not pop it off without being done with the left and right child nodes.
     * Then for each node calculate the max of the left and right sub trees depth and simultaneously
     * calculate the overall max of the left and right subtrees count.
     * @param root
     * @return
     */
    public static int diameterOfBinaryTreeIterative(TreeNode root) {
        if (null == root) {
            return 0;
        }

        return 0;
    }



    /**
     * Global variables may cause issue when running test cases.
     */
    //private static int max = 0;

    /**
     * It took me a while to figure this out.
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
        List<Integer> list = new ArrayList<>();
        list.add(0);
        maxDepth(root, list);
        return list.get(0);
    }

    /**
     * The question can be solved by small modification to program of Height of tree.
     * The idea is quite simple.
     * Max value of Height(leftSubtree)+Height(rightSubtree) (at any node ) is the diameter.
     * Keep track of the maxmium diameter during traversal and find the height of the tree.
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root, List<Integer> list) {
        if (null == root) {
            return 0;
        }
        int left = maxDepth(root.left, list);
        int right = maxDepth(root.right, list);
        int max = list.get(0);
        int newMax = Math.max(max, left + right);
        list.add(0, newMax);
        // plus 1 due to current root node
        return Math.max(left, right) + 1;
    }

}
