package org.ict.algorithm.leetcode.tree;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
 * @author sniper
 * @date 04 Jan, 2023
 * LC297
 */
public class SerializeAndDeserializeBinaryTree {

    /**
     * Your Codec object will be instantiated and called as such:
     * Codec ser = new Codec();
     * Codec deser = new Codec();
     * TreeNode ans = deser.deserialize(ser.serialize(root));
     */


    /**
     * Time Cost 63ms
     * Breadth-First-Search Solution.
     * Time Complexity
     */
    public class CodecV1 {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (null == root) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    sb.append(cur.val + ",");
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else {
                    sb.append("#,");
                }
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (null == data) {
                return null;
            }
            String[] arr = data.split(",");
            if ("#".equals(arr[0])) {
                return null;
            }
            TreeNode root = new TreeNode();
            root.val = Integer.valueOf(arr[0]);

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            for (int i = 1; i < arr.length; i++) {
                TreeNode cur = queue.poll();
                if (!"#".equals(arr[i])) {
                    TreeNode left = new TreeNode(Integer.valueOf(arr[i]));
                    cur.left = left;
                    queue.offer(left);
                }
                i++;
                if (!"#".equals(arr[i])) {
                    TreeNode right = new TreeNode(Integer.valueOf(arr[i]));
                    cur.right = right;
                    queue.offer(right);
                }
            }
            return root;
        }
    }

    /**
     * Time Cost 68ms
     *
     * PreOrder-Recursion Solution
     * Time Complexity
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (null == root) {
                return "#";
            }
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return preOrder(queue);
        }

        private TreeNode preOrder(Queue<String> queue) {
            String s = queue.poll();
            if ("#".equals(s)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(s));
            root.left = preOrder(queue);
            root.right = preOrder(queue);
            return root;
        }

    }


}
