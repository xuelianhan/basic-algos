package org.ict.algorithm.leetcode.tree;

import java.util.*;

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
 *
 * LC543, Easy
 * Similar Question
 * {@link org.ict.algorithm.leetcode.graph.LongestPathWithDifferentAdjacentCharacters }
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
        int globalDiameter = 0;
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> visited = new HashMap<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !visited.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !visited.containsKey(node.right)) {
                stack.push(node.right);
            } else {
                // cur is the start node of post order
                TreeNode cur = stack.pop();
                int leftDepth  = visited.getOrDefault(cur.left, 0);
                int rightDepth = visited.getOrDefault(cur.right, 0);
                // the layer height (depth) of node cur.
                // leaf node's layer height is 1(leaf itself).
                // non-leaf node's layer height as following:
                // Max(sub_left, sub_right) + 1
                int curNodeDepth = Math.max(leftDepth, rightDepth) + 1;
                visited.put(cur, curNodeDepth);
                globalDiameter = Math.max(globalDiameter, leftDepth + rightDepth);
            }
        }
        return globalDiameter;
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
