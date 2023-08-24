package org.ict.algorithm.company.coupang;

/**
 * This code first creates a head node with a value of Integer.MIN_VALUE.
 * The head node is used as a sentinel node.
 * The code then initializes the maxLevel variable to 1.
 *
 * The add() method takes an integer value as input and inserts a new node with that value into the skip list.
 * The code first creates a new node with the given value and the current maximum level.
 * The code then iterates over the levels of the skip list, starting from the highest level.
 * For each level, the code finds the first node whose value is greater than or equal to the given value.
 * The code then sets the new node's forward pointer for that level to the node that it found.
 *
 * If the random number generated is less than 0.5, then the code increments the maxLevel variable.
 * This ensures that the skip list is probabilistically balanced.
 *
 * The contains() method takes an integer value as input and checks if the skip list contains a node with that value.
 * The code first iterates over the levels of the skip list, starting from the highest level.
 * For each level, the code finds the first node whose value is greater than or equal to the given value.
 * If the code finds a node with the given value, then the method returns true.
 * Otherwise, the method returns false.
 * @author sniper
 * @date 17 Aug 2023
 */
public class SkipList {
    private Node head;
    private int maxLevel;

    public SkipList() {
        head = new Node(Integer.MIN_VALUE, maxLevel);
        maxLevel = 1;
    }

    public void add(int value) {
        Node node = new Node(value, maxLevel);
        Node current = head;
        for (int i = maxLevel - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }

            node.forward[i] = current.forward[i];
            current.forward[i] = node;
        }

        if (Math.random() < 0.5) {
            maxLevel++;
        }
    }

    public boolean contains(int value) {
        Node current = head;
        for (int i = maxLevel - 1; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }

        return current.forward[0] != null && current.forward[0].value == value;
    }

    static class Node {
        int value;
        Node[] forward;

        public Node(int value, int maxLevel) {
            this.value = value;
            forward = new Node[maxLevel];
        }
    }
}
