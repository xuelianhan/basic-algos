package org.ict.algorithm.leetcode.tree;


import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree.
 * Apart from the left and right child pointers,
 * each node in the given binary tree points to a random node in the given binary tree.
 * You are supposed to return a clone of the binary tree.
 *
 * Cloning a binary tree means making a deep copy of the input binary tree.
 *
 * Note :
 * Two nodes may have the same value associated with them.
 * The root node will be fixed and will be provided in the function.
 * Input Format :
 * The first line contains an integer 'T' which denotes the number of test cases or queries to be run. Then the test cases follow.
 *
 * The first line of each test case contains elements in the level order form. The line consists of values of nodes separated by a single space. In case a node is null, we take -1 in its place. So -1 would not be a part of the tree nodes.
 *
 * The second line of each test case contains the values of the nodes of the tree in the random order ( -1 for NULL node), connecting the node values of the original tree with random nodes of the tree.
 *
 * For example, the input for the tree depicted in the below image will be:
 *
 * 1
 * 2 3
 * 4 -1 5 6
 * -1 7 -1 -1 -1 -1
 * -1 -1
 * Explanation :
 *
 * Level 1 :
 * The root node of the tree is 1
 *
 * Level 2 :
 * Left child of 1 = 2
 * Right child of 1 = 3
 *
 * Level 3 :
 * Left child of 2 = 4
 * Right child of 2 = null (-1)
 * Left child of 3 = 5
 * Right child of 3 = 6
 *
 * Level 4 :
 * Left child of 4 = null (-1)
 * Right child of 4 = 7
 * Left child of 5 = null (-1)
 * Right child of 5 = null (-1)
 * Left child of 6 = null (-1)
 * Right child of 6 = null (-1)
 *
 * Level 5 :
 * Left child of 7 = null (-1)
 * Right child of 7 = null (-1)
 *
 * The first not-null node(of the previous level) is treated as the parent of the first two nodes of the current level. The second not-null node (of the previous level) is treated as the parent node for the next two nodes of the current level and so on.
 * The input ends when all nodes at the last level are null(-1).
 * Note :
 * The above format was just to provide clarity on how the input is formed for a given tree.
 * The sequence will be put together in a single line separated by a single space. Hence, for the above-depicted tree, the input will be given as:
 *
 * 1 2 3 4 -1 5 6 -1 7 -1 -1 -1 -1 -1 -1
 * Output Format :
 * For each test case, print the inorder traversal of the cloned binary tree separated by a single space.
 * On the next line, the checker will output a ‘1’ if you have cloned the tree successfully; otherwise, it will print ‘0’.
 *
 * Print the output of each test case in a separate line.
 * Note :
 * You don’t need to print anything; It has already been taken care of.
 * Constraints :
 * 1 <= T <= 100
 * 0 <= N <= 3000
 * 1 <= data <= 10 ^ 5 and data!=-1
 *
 * Where ‘T’ is the number of test cases, and ‘N’ is the total number of nodes in the binary tree, and “data” is the value of the binary tree node
 *
 * Time Limit: 1 sec
 * @author sniper
 * @date 16 Sep, 2022
 * LC1485
 * Amazon Coding Interview Question
 */
public class CloneABinaryTreeWithRandomPointers {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.random = root.right;
        root.left.right.random = root;
        root.right.left.random = root.left.left;
        root.random = root.left;

        System.out.println("Preorder traversal of the original tree:");
        preorder(root);

        Node clone = cloneSpecialBinaryTree(root);

        System.out.println("\nPreorder traversal of the cloned tree:");
        preorder(clone);
    }

    /**
     * The intuitive solution to clone a binary tree with random pointers is to insert each node into a a hash table.
     * The idea is to traverse the binary tree in a preorder fashion,
     * and for each encountered node,
     * create a new node with the same data and create a mapping from the original node to the duplicate node in the hash table.
     * After creating the mapping, recursively set its left and right pointers.
     * Finally, traverse the original binary tree again and update the duplicate nodes’ random pointers using the hash table.
     *
     *
     *
     * The steps are as follows:
     * Let’s define a HashMap to map each node to it’s cloned node, let’s say “clonedNodes”.
     * Recursively traverse the tree, for each node create a new clone node, let’s say our current node is “curr”, and the cloned node is “clone”.
     * Copy the value, left pointer, and right pointer of the current node to “clone”.
     * Insert “curr” into the HashMap and map it to “clone”.
     * Recursion for the left, and the right subtrees.
     * Now recursively traverse the given tree and the cloned tree simultaneously,
     * and set the random pointer of the cloned tree using the values from the HashMap.
     *
     * The time complexity of this solution is O(n), where n is the total number of nodes in the binary tree.
     * The extra space used by the solution is O(n) for the map and call stack.
     * @param root
     * @return
     */
    public static Node cloneSpecialBinaryTree(Node root) {
        if (root == null) {
            return null;
        }

        /**
         * create a map to store mappings from a node to its clone.
         */
        Map<Node, Node> map = new HashMap<>();

        /**
         * clone data, left, and right children for each node of the original
         *  binary tree, and put references into the map.
         */
        cloneLeftRightPointers(root, map);

        /**
         * update random pointers from the original binary tree in the map
         */
        updateRandomPointers(root, map);

        /**
         * return the cloned root node
         */
        return map.get(root);
    }

    /**
     * Recursive function to clone the data, left, and right children for
     * each node of a binary tree into a given map.
     * @param root
     * @param map
     * @return
     */
    public static Node cloneLeftRightPointers(Node root, Map<Node, Node> map) {
        if (root == null) {
            return null;
        }

        /**
         *  clone all fields of the root node except the random pointer
         *  create a new node with the same data as the root node
         */
        map.put(root, new Node(root.data));

        /**
         * clone the left and right subtree
         */
        map.get(root).left = cloneLeftRightPointers(root.left, map);
        map.get(root).right = cloneLeftRightPointers(root.right, map);

        /**
         * return cloned root node
         */
        return map.get(root);
    }


    /**
     *  Recursive function to copy random pointers from the original binary tree
     *  into the cloned binary tree using the map.
     * @param root
     * @param map
     */
    public static void updateRandomPointers(Node root, Map<Node, Node> map) {
        if (map.get(root) == null) {
            return;
        }
        /**
         * update the random pointer of the cloned node
         */
        map.get(root).random = map.get(root.random);

        /**
         *  recursion for the left and right subtree
         */
        updateRandomPointers(root.left, map);
        updateRandomPointers(root.right, map);
    }

    /**
     * Recursive function to print the preorder traversal on a given binary tree
     * @param root
     */
    public static void preorder(Node root) {
        if (root == null) {
            return;
        }

        // print the current node's data
        System.out.print(root.data + " —> (");

        // print left child's data
        System.out.print((root.left != null ? root.left.data : "X") + ", ");

        // print the right child's data
        System.out.print((root.right != null ? root.right.data : "X") + ", ");

        // print the random child's data
        System.out.println((root.random != null ? root.random.data : "X") + ")");

        // recursion for the left and right subtree
        preorder(root.left);
        preorder(root.right);
    }

    private static class Node {
        int data;
        Node left;
        Node right;
        Node random;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.random = null;
        }
    }
}
