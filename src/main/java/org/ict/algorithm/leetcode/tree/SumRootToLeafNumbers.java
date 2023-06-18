package org.ict.algorithm.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 *
 * Each root-to-leaf path in the tree represents a number.
 *
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 * Test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A leaf node is a node with no children.
 *
 *
 *
 * Example 1:
 *        1
 *      /  \
 *     2    3
 *
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 *          4
 *         / \
 *        9   0
 *       / \
 *      5   1
 *
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 * @author sniper
 * @date 17 Jun 2023
 * LC129, Medium
 */
public class SumRootToLeafNumbers {

    /**
     * Iterative Pre-Order BFS Traversal Solution
     * Time Cost 0ms
     * @param root
     * @return
     */
    public int sumNumbersV3(TreeNode root) {
        int res = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                cur.left.val += cur.val * 10;
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                cur.right.val += cur.val * 10;
                queue.offer(cur.right);
            }
            if (cur.left == null && cur.right == null) {
                res += cur.val;
            }
        }
        return res;
    }

    /**
     * Iterative Pre-Order DFS Traversal Solution
     * Time Cost 0ms
     * @param root
     * @return
     */
    public int sumNumbersV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                res += cur.val;
            }
            if (cur.right != null) {
                cur.right.val += cur.val * 10;
                stack.push(cur.right);
            }
            if (cur.left != null) {
                cur.left.val += cur.val * 10;
                stack.push(cur.left);
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Recursive Depth-First-Search + Preorder
     * Time Cost 0ms
     * -------------------------------
     * class Solution:
     *     def sumNumbers(self, root: Optional[TreeNode]) -> int:
     *         res = 0
     *
     *         def dfs(root: Optional[TreeNode], path: int) -> None:
     *             nonlocal res
     *             if not root:
     *                 return
     *             if not root.left and not root.right:
     *                 res += path * 10 + root.val
     *                 return
     *
     *             dfs(root.left, path * 10 + root.val)
     *             dfs(root.right, path * 10 + root.val)
     *
     *         dfs(root, 0)
     *         return res
     * --------------------------------
     * class Solution {
     * public:
     *     int sumNumbers(TreeNode* root) {
     *         int res = 0;
     *         dfs(root, 0, res);
     *         return res;
     *     }
     *
     *     private:
     *         void dfs(TreeNode* root, int path, int& res) {
     *             if (root == nullptr){
     *                 return;
     *             }
     *             if (root->left == nullptr && root->right == nullptr) {
     *                 res += path * 10 + root->val;
     *                 return;
     *             }
     *
     *             dfs(root->left, path * 10 + root->val, res);
     *             dfs(root->right, path * 10 + root->val, res);
     *         }
     * };
     * @param root
     * @return
     */
    public int sumNumbersV1(TreeNode root) {
        int[] res = new int[1];
        dfsV1(root, 0, res);
        return res[0];
    }

    private void dfsV1(TreeNode root, int path, int[] res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res[0] += path * 10 + root.val;
            return;
        }
        dfsV1(root.left, path * 10 + root.val, res);
        dfsV1(root.right, path * 10 + root.val, res);
    }

    /**
     * Understanding the following solution
     * Recursive Depth-First-Search + Preorder
     * Time Cost 0ms
     * Time Complexity O(N), where N is the number of nodes in the tree.
     * We are doing a standard DFS traversal which takes O(N) time
     * Space Complexity O(H), where H is the maximum depth of tree.
     * This space is required for implicit recursive stack space.
     * In the worst case, the tree maybe skewed and H = N in which case space required is equal to O(N).
     * -------------------------------
     * class Solution:
     *     def sumNumbers(self, root: Optional[TreeNode]) -> int:
     *       def dfs(root, res):
     *         if root is None:
     *           return 0
     *         res = res * 10 + root.val
     *         if root.left is None and root.right is None:
     *           return res
     *         return dfs(root.left, res) + dfs(root.right, res)
     *       return dfs(root, 0)
     * -------------------------------
     * class Solution {
     * public:
     *     int sumNumbers(TreeNode* root) {
     *         return dfs(root, 0);
     *     }
     *
     *     int dfs(TreeNode* root, int sum) {
     *         if (!root) {
     *             return 0;
     *         }
     *         sum = sum * 10 + root->val;
     *         if (!root->left && !root->right) {
     *             return sum;
     *         }
     *         return dfs(root->left, sum) + dfs(root->right, sum);
     *     }
     * };
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}
