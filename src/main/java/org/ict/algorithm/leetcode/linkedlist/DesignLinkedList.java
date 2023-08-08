package org.ict.algorithm.leetcode.linkedlist;

/**
 * Design your implementation of the linked list.
 * You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next.val is the value of the current node,
 * and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list,
 * you will need one more attribute prev to indicate the previous node in the linked list.
 * Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the index-th node in the linked list.
 * If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list.
 * After the insertion, the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the index-th node in the linked list.
 * If index equals the length of the linked list, the node will be appended to the end of the linked list.
 * If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the index-th node in the linked list, if the index is valid.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 *
 * Constraints:
 *
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 * @author sniper
 * @date 08 Aug 2023
 * LC707, Medium
 */
public class DesignLinkedList {

    /**
     * Doubly Linked Node Solution
     * Time Cost 11ms
     */
    class MyLinkedListV1 {
        /**
         * Two dummy Nodes: head and tail
         */
        private DoublyListNode<Integer> head;
        private DoublyListNode<Integer> tail;
        private int size;

        public MyLinkedListV1() {
            this.head = new DoublyListNode<>(0);
            this.tail = new DoublyListNode<>(0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            DoublyListNode<Integer> cur = head;
            /**
             * Due to head is a dummy node, so we use i <= index instead of i < index.
             */
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) {
                return;
            }
            DoublyListNode<Integer> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            DoublyListNode<Integer> node = new DoublyListNode<>(val);
            node.next = cur.next;
            node.next.prev = node;
            cur.next = node;
            node.prev = cur;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            DoublyListNode<Integer> cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
            // delete cur node
            cur.next.prev = cur.prev;
            cur.prev.next = cur.next;
            size--;
        }
    }

    static class DoublyListNode<Item> {
        Item val;
        DoublyListNode next;
        DoublyListNode prev;

        DoublyListNode(Item val) {
            this.val = val;
        }
    }

    /**
     * Single Linked Node Solution
     * Time Cost 7ms
     */
    class MyLinkedList {

        private SingleListNode<Integer> head;
        private SingleListNode<Integer> tail;

        private int size;

        public MyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        public int get(int index) {
            /**
             * Notice index >= size instead of index > size,
             * otherwise NPE occurs at cur.val line.
             */
            if (index < 0 || index >= size) {
                return -1;
            }
            SingleListNode<Integer> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            SingleListNode<Integer> node = new SingleListNode<>(val, head);
            head = node;
            if (size == 0) {
                tail = node;
            }
            size++;
        }

        public void addAtTail(int val) {
            SingleListNode<Integer> node = new SingleListNode<>(val, null);
            if (size == 0) {
                head = node;
                tail = node;
            }
            tail.next = node;
            tail = node;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                // If index is greater than the length, the node will not be inserted.
                return;
            }
            if (index == size) {
                // If index equals the length of the linked list, the node will be appended to the end of the linked list.
                addAtTail(val);
                return;
            }
            if (index <= 0) {
                addAtHead(val);
                return;
            }
            SingleListNode<Integer> cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            SingleListNode<Integer> node = new SingleListNode<>(val, cur.next);
            cur.next = node;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            if (index == 0) {
                head = head.next;
                size--;
                return;
            }
            SingleListNode<Integer> cur = head;
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            if (index == size - 1) {
                tail = cur;
            }
            size--;
        }
    }

    static class SingleListNode<Item> {
        Item val;
        SingleListNode next;

        SingleListNode(Item x) {
            this.val = x;
            this.next = null;
        }

        SingleListNode(Item x, SingleListNode next) {
            this.val = x;
            this.next = next;
        }
    }
    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */

}
