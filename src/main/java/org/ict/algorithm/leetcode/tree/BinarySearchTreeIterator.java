package org.ict.algorithm.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class.
 * The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number,
 * the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid.
 * That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^5].
 * 0 <= Node.val <= 10^6
 * At most 105 calls will be made to hasNext, and next.
 *
 *
 * Follow up:
 *
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 * @author sniper
 * @date 18 Jun 2023
 * LC173, Medium,
 */
public class BinarySearchTreeIterator {

    /**
     * Understanding the following solution
     * -------------------------------------
     * class BSTIterator {
     * public:
     *     BSTIterator(TreeNode* root) {
     *         inOrder(root);
     *     }
     *
     *     int next() {
     *         return vals[i++];
     *     }
     *
     *     bool hasNext() {
     *         return i < vals.size();
     *     }
     *
     * private:
     *     int i = 0;
     *     vector<int> vals;
     *
     *     void inOrder(TreeNode* root) {
     *         if (root == nullptr) {
     *             return;
     *         }
     *         inOrder(root->left);
     *         vals.push_back(root->val);
     *         inOrder(root->right);
     *     }
     * };
     * ----------------------------------
     * class BSTIterator:
     *
     *     def __init__(self, root: Optional[TreeNode]):
     *       def inorder(root):
     *         if root is None:
     *           return
     *         inorder(root.left)
     *         self.vals.append(root.val)
     *         inorder(root.right)
     *       self.vals = []
     *       self.i = 0
     *       inorder(root)
     *
     *     def next(self) -> int:
     *       res = self.vals[self.i]
     *       self.i += 1
     *       return res
     *
     *
     *     def hasNext(self) -> bool:
     *       return self.i < len(self.vals)
     */
    class BSTIteratorV1 {

        private int i;

        private List<Integer> vals = new ArrayList<>();

        /**
         * Time Complexity O(N)
         * @param root
         */
        public BSTIteratorV1(TreeNode root) {
            inOrder(root);
        }

        /**
         * Time Complexity O(1)
         * @return
         */
        public int next() {
            int res = vals.get(i);
            i++;
            return res;
        }

        /**
         * Time Complexity O(1)
         * @return
         */
        public boolean hasNext() {
            return i < vals.size();
        }

        private void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            vals.add(root.val);
            inOrder(root.right);
        }
    }

    /**
     * Time Cost 18ms
     * Understanding the following solution
     * --------------------------------------
     * class BSTIterator:
     *
     *     def __init__(self, root: Optional[TreeNode]):
     *         self.stack = []
     *         self._pushLeftUntilNull(root)
     *
     *
     *     def next(self) -> int:
     *         cur = self.stack.pop()
     *         self._pushLeftUntilNull(cur.right)
     *         return cur.val
     *
     *     def hasNext(self) -> bool:
     *         return self.stack
     *
     *     def _pushLeftUntilNull(self, root: Optional[TreeNode]) -> None:
     *         while root:
     *             self.stack.append(root)
     *             root = root.left
     * -------------------------------------------
     * class BSTIterator {
     * public:
     *     BSTIterator(TreeNode* root) {
     *         pushLeftUntilNull(root);
     *     }
     *
     *     int next() {
     *         TreeNode* root = stack.top();
     *         stack.pop();
     *         pushLeftUntilNull(root->right);
     *         return root->val;
     *     }
     *
     *     bool hasNext() {
     *          return !stack.empty();
     *     }
     *
     * private:
     *     stack<TreeNode*> stack;
     *
     *     void pushLeftUntilNull(TreeNode* root) {
     *         while (root) {
     *             stack.push(root);
     *             root = root->left;
     *         }
     *     }
     * };
     */
    class BSTIterator {

        private Deque<TreeNode> stack = new ArrayDeque<>();

        /**
         * Time Complexity O(H)
         * @param root
         */
        public BSTIterator(TreeNode root) {
            pushLeftUntilNull(root);
        }

        /**
         *  Time Complexity O(H)
         * @return
         */
        public int next() {
            TreeNode cur = stack.pop();
            int res = cur.val;
            pushLeftUntilNull(cur.right);
            return res;
        }

        /**
         *  Time Complexity O(1)
         * @return
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushLeftUntilNull(TreeNode root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }
}
