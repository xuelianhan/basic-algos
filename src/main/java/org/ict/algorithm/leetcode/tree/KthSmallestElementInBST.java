package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * @author sniper
 * @date 21 Sep, 2022
 * LC230
 */
public class KthSmallestElementInBST {

    public int kthSmallestV2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int result = -1;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                /**
                 * push node into the stack and go left.
                 */
                stack.push(cur);
                cur = cur.left;
            } else {
                /**
                 * if left has no children, pop root from the stack,
                 * collect root value, check the k times chance.
                 * then decide break loop or go right.
                 */
                TreeNode node = stack.pop();
                // Add after all left children
                result = node.val;
                k--;
                if (k == 0) {
                    break;
                }
                cur = node.right;
            }
        }
        return result;
    }

    /**
     * In-Order traversal.
     * When come across the kth smallest element, stop the loop and return the result immediately.
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallestV1(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int cnt = 1;
        int result = 0;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                // Add after all left children
                result = node.val;
                if (cnt == k) {
                    break;
                }
                cnt++;
                cur = node.right;
            }
        }
        return result;
    }


    /**
     * In-Order traversal.
     * Access all the nodes and get the kth smallest element in the list.
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                /**
                 * push into the stack and go left.
                 */
                stack.push(cur);
                cur = cur.left;
            } else {
                /**
                 * if left node has no children, pop root from the stack,
                 * collect root value and go right.
                 */
                TreeNode node = stack.pop();
                // Add after all left children
                result.add(node.val);
                cur = node.right;
            }
        }
        return result.get(k-1);
    }
}
