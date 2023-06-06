package org.ict.algorithm.leetcode.binarysearchtree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given the root node of a binary search tree and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 * Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 * Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 2 * 10^4].
 * 1 <= Node.val <= 10^5
 * 1 <= low <= high <= 10^5
 * All Node.val are unique.
 *
 * @author sniper
 * @date 2021/12/1
 * LC938, Easy, frequency=149
 */
public class RangeSumOfBST {


    /**
     * Understanding the following solution
     *
     * Time cost 0ms
     * ----------------------------------------
     * class Solution {
     * public:
     *     int rangeSumBST(TreeNode* root, int low, int high) {
     *         if (!root) {
     *             return 0;
     *         }
     *         if (root-> val < low) {
     *             return rangeSumBST(root->right, low, high);
     *         }
     *         if (root->val > high) {
     *             return rangeSumBST(root->left, low, high);
     *         }
     *         return root->val + rangeSumBST(root->left, low, high) + rangeSumBST(root->right, low, high);
     *     }
     * };
     * ----------------------------------------------
     * class Solution:
     *     def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
     *         if root is None:
     *             return 0
     *         if root.val < low:
     *             return self.rangeSumBST(root.right, low, high)
     *         if root.val > high:
     *             return self.rangeSumBST(root.left, low, high)
     *         return root.val + self.rangeSumBST(root.left, low, high) + self.rangeSumBST(root.right, low, high)
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV5(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        /**
         * Three situations:
         * 1.root.val < low < high, find in right part of the bst.
         * 2.low < high < root.val, find in the left part of the bst.
         * 3.low <= root.val <= high, find in both left and right part of the bst.
         */
        if (root.val < low) {
            return rangeSumBSTV5(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBSTV5(root.left, low, high);
        }
        return root.val + rangeSumBSTV5(root.left, low, high) + rangeSumBSTV3(root.right, low, high);
    }

    /**
     * Understanding the following solution
     *
     * Depth First Solution
     * Time Cost 1ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV4(TreeNode root, int low, int high) {
        int[] res = new int[1];
        dfs(root, low, high, res);
        return res[0];
    }

    private void dfs(TreeNode root, int low, int high, int[] res) {
        if (root == null) {
            return;
        }
        if (root.val >= low && root.val <= high) {
            res[0] += root.val;
        }
        dfs(root.left, low, high, res);
        dfs(root.right, low, high, res);
    }


    /**
     * Understanding the following solution
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV3(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        List<Integer> res = inOrderV3(root, low, high);
        if (res.size() == 0) {
            return 0;
        }
        return res.stream().mapToInt(i -> i).sum();
    }

    private List<Integer> inOrderV3(TreeNode root, int low, int high) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.val >= low && cur.val <= high) {
                    res.add(cur.val);
                }
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * Time Cost 5ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV2(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        if (root.val < low) {
            return inOrderV2(root.right, low, high);
        }
        if (root.val > high) {
            return inOrderV2(root.left, low, high);
        }
        return root.val + inOrderV2(root.left, low, high) + inOrderV2(root.right, low, high);
    }

    public int inOrderV2(TreeNode root, int low, int high) {
        int result = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                // Add after all left children
                if (node.val >= low && node.val <= high) {
                    result += node.val;
                }
                cur = node.right;
            }
        }
        return result;
    }

    /**
     * Time cost 26ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTV1(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        if (root.val < low) {
            List<Integer> result = inOrder(root.right, low, high);
            return sumList(result);
        }
        if (root.val > high) {
            List<Integer> result = inOrder(root.left, low, high);
            return sumList(result);
        }
        List<Integer> list1 = inOrder(root.left, low, high);
        List<Integer> list2 = inOrder(root.right, low, high);
        return root.val + sumList(list1) + sumList(list2);
    }

    /**
     * Iterative In-Order traversal Solution
     * Time cost 15ms
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (null == root || low > high) {
            return 0;
        }
        List<Integer> result = inOrder(root, low, high);
        return sumList(result);
    }

    public List<Integer> inOrder(TreeNode root, int low, int high) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                // Add after all left children
                if (node.val >= low && node.val <= high) {
                    result.add(node.val);
                }
                cur = node.right;
            }
        }
        return result;
    }

    public int sumList(List<Integer> result) {
        if (null == result) {
            return 0;
        }
        return result.stream().mapToInt(i -> i).sum();
    }

}
