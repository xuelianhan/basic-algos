package org.ict.algorithm.leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 *
 * The tilt of a tree node is the absolute difference between the sum of all left subtree node values and all right subtree node values.
 * If a node does not have a left child,
 * then the sum of the left subtree node values is treated as 0.
 * The rule is similar if there the node does not have a right child.
 * Input: root = [1,2,3]
 * Output: 1
 * Explanation:
 * Tilt of node 2 : |0-0| = 0 (no children)
 * Tilt of node 3 : |0-0| = 0 (no children)
 * Tilt of node 1 : |2-3| = 1 (left subtree is just left child, so sum is 2; right subtree is just right child, so sum is 3)
 * Sum of every tilt : 0 + 0 + 1 = 1
 *
 * Input: root = [4,2,9,3,5,null,7]
 * Output: 15
 * Explanation:
 * Tilt of node 3 : |0-0| = 0 (no children)
 * Tilt of node 5 : |0-0| = 0 (no children)
 * Tilt of node 7 : |0-0| = 0 (no children)
 * Tilt of node 2 : |3-5| = 2 (left subtree is just left child, so sum is 3; right subtree is just right child, so sum is 5)
 * Tilt of node 9 : |0-7| = 7 (no left child, so sum is 0; right subtree is just right child, so sum is 7)
 * Tilt of node 4 : |(3+5+2)-(9+7)| = |10-16| = 6 (left subtree values are 3, 5, and 2, which sums to 10; right subtree values are 9 and 7, which sums to 16)
 * Sum of every tilt : 0 + 0 + 0 + 2 + 7 + 6 = 15
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 *
 * LC563
 */
public class BinaryTreeTilt {

    public static void main(String[] args) {
        // [4,2,9,3,5,null,7]
        TreeNode three = new TreeNode(3, null, null);
        TreeNode five = new TreeNode(5, null, null);
        TreeNode seven = new TreeNode(7, null, null);
        TreeNode two = new TreeNode(2, three, five);
        TreeNode nine = new TreeNode(9, null, seven);
        TreeNode root = new TreeNode(4, two, nine);
        int result = findTilt(root);
        System.out.println("result:" + result);
    }


    /**
     * This solution inspired by the diameter of binary tree with Post-Order Traversal
     * @param root
     * @return
     */
    public static int findTilt(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int globalTilt = 0;
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
                int leftVal  = visited.getOrDefault(cur.left, 0);
                int rightVal = visited.getOrDefault(cur.right, 0);
                //cur node sum = cur_left_val + cur_right_val + cur_itself_val
                visited.put(cur, leftVal + rightVal + cur.val);
                int curNodeTilt = Math.abs(leftVal - rightVal);
                globalTilt +=  curNodeTilt;
            }
        }
        return globalTilt;
    }


}
