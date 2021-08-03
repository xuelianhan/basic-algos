package org.ict.algorithm.leetcode.tree;

import java.util.HashSet;
import java.util.Stack;

/**
 * Given the root of a binary tree,
 * construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way,
 * and return it.
 *
 * Omit all the empty parenthesis pairs that do not affect the one-to-one
 * mapping relationship between the string and the original binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4]
 * Output: "1(2(4))(3)"
 * Explanation: Originallay it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)"
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4]
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example,
 * except we cannot omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -1000 <= Node.val <= 1000
 *
 * LC606
 */
public class ConstructStringFromBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * DFS solution
     * @param root
     * @return
     */
    public static String tree2str(TreeNode root) {
        if (null == root) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (visited.contains(cur)) {
                stack.pop();
                sb.append(")");
            } else {
                visited.add(cur);
                sb.append("(").append(cur.val);
                if (cur.left == null && cur.right != null) {
                    sb.append("()");
                }
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return sb.substring(1, sb.length() - 1);
    }

    public static String tree2strRecursive(TreeNode root) {
        if (null == root) {
            return "";
        }
        String res = root.val + "";
        String left = tree2strRecursive(root.left);
        String right = tree2strRecursive(root.right);
        if (left == "" && right == "") {
            return res;
        }
        if (left == "") {
            return res + "()" + "(" + right + ")";
        }
        if (right == "") {
            return res + "(" + left + ")";
        }
        return res + "(" + left + ")" + "(" + right + ")";
    }
}
