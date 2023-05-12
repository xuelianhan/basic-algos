package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * Description
 * Given the root of a binary search tree, a target value,
 * and an integer k, return the k values in the BST that are closest to the target.
 * You may return the answer in any order.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * Example 1:
 *          4
 *        /   \
 *       2     5
 *     /  \
 *    1   3
 *
 *
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 *
 * Example 2:
 * Input: root = [1], target = 0.000000, k = 1
 * Output: [1]

 * Constraints:
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 10^4.
 * 0 <= Node.val <= 10^9
 * -10^9 <= target <= 10^9
 *
 *
 * Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
 * @author sniper
 * @date 11 May 2023
 * LC272, Medium, frequency=26
 */
public class ClosestBinarySearchTreeValueII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        two.left = one;
        two.right = three;
        root.left = two;
        root.right = five;
        double target = 3.714286;
        int k = 2;
        ClosestBinarySearchTreeValueII instance = new ClosestBinarySearchTreeValueII();
        List<Integer> res = instance.closestKValuesV2(root, target, k);
        res.forEach(i -> System.out.println(i));
    }


    /**
     * Max-Heap Solution
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValuesV3(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        return res;
    }

    /**
     * Iterative Solution with Stack
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValuesV2(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if (res.size() < k) {
                res.add(p.val);
            } else if (Math.abs(p.val- target) < Math.abs(res.get(0) - target)){
                res.remove(0);
                res.add(p.val);
            } else {
                break;
            }
            p = p.right;
        }
        return res;
    }



    /**
     * Depth-First-Search(In-Order Traversal and collect result while traversing)
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> closestKValuesV1(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        inOrderV1(root, target, k, res);
        return res;
    }

    /**
     * # Definition for a binary tree node.
     * # class TreeNode:
     * #     def __init__(self, val=0, left=None, right=None):
     * #         self.val = val
     * #         self.left = left
     * #         self.right = right
     * class Solution:
     *     def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
     *         def dfs(root):
     *             if root is None:
     *                 return
     *             dfs(root.left)
     *             if len(q) < k:
     *                 q.append(root.val)
     *             else:
     *                 if abs(root.val - target) >= abs(q[0] - target):
     *                     return
     *                 q.popleft()
     *                 q.append(root.val)
     *             dfs(root.right)
     *
     *         q = deque()
     *         dfs(root)
     *         return list(q)
     * @param root
     * @param target
     * @param k
     * @param res
     */
    private void inOrderV2(TreeNode root, double target, int k, List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrderV2(root.left, target, k, res);
        if (res.size() < k) {
            res.add(root.val);
        } else {
            if (Math.abs(root.val - target) >= Math.abs(res.get(0) - target)) {
                return;
            }
            res.remove(0);
            res.add(root.val);
        }
        inOrderV2(root.right, target, k, res);
    }

    /**
     * class Solution {
     * public:
     *     vector<int> closestKValues(TreeNode* root, double target, int k) {
     *         vector<int> res;
     *         inorder(root, target, k, res);
     *         return res;
     *     }
     *     void inorder(TreeNode *root, double target, int k, vector<int> &res) {
     *         if (!root) return;
     *         inorder(root->left, target, k, res);
     *         if (res.size() < k) res.push_back(root->val);
     *         else if (abs(root->val - target) < abs(res[0] - target)) {
     *             res.erase(res.begin());
     *             res.push_back(root->val);
     *         } else return;
     *         inorder(root->right, target, k, res);
     *     }
     * };
     * @param root
     * @param target
     * @param k
     * @param res
     */
    private void inOrderV1(TreeNode root, double target, int k, List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrderV1(root.left, target, k, res);
        if (res.size() < k) {
            res.add(root.val);
        } else if (Math.abs(root.val - target) < Math.abs(res.get(0) - target)) {
            res.remove(0);
            res.add(root.val);
        } else {
            return;
        }
        inOrderV1(root.right, target, k, res);
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        /**
         * In-Order-Traversal at first and store the results into a queue.
         */
        inOrder(root, target,k, queue);

        /**
         * Iterate values from the queue and store them into a list.
         */
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    /**
     * Similar with inOrderV1, but using queue collect the value at first.
     * @param root
     * @param target
     * @param k
     * @param queue
     */
    private void inOrder(TreeNode root, double target, int k, Queue<Integer> queue) {
        if (root == null) {
            return;
        }
        /**
         * Go left
         */
        inOrder(root.left, target, k, queue);
        /**
         * Process root
         */
        if (queue.size() < k) {
            queue.offer(root.val);
        } else {
            if (Math.abs(root.val - target) >= Math.abs(queue.peek() - target)) {
                return;
            }
            queue.poll();
            queue.offer(root.val);
        }
        /**
         * Go right
         */
        inOrder(root.right, target, k, queue);
    }


}
