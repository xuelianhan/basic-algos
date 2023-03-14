package org.ict.algorithm.leetcode.backtrack;


import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * mapping relations like the following:
 *
 * 2---abc
 * 3---def
 * 4---ghi
 * 5---jkl
 * 6---mno
 * 7--pqrs
 * 8---tuv
 * 9--wxyz
 *
 *
 * Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 *
 * Constraints:
 *
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 * @author sniper
 * @date 01 Nov, 2022
 * LC17
 */
public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber instance = new LetterCombinationsOfPhoneNumber();
        instance.letterCombinations("23");
    }

    /**
     * Time Cost 6ms
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV3(String digits) {
        List<String> result = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String prefix = new String();
        String[] map = new String[] {"",   "",   "abc",  "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrackV3(result, digits, prefix, map, 0);
        return result;
    }


    public void backtrackV3(List<String> result, String digits, String track, String[] map, int pos) {
        if (pos == digits.length()) {
            result.add(track.toString());
            return;
        }
        int idx = digits.charAt(pos) - '0';
        String possibleLetters = map[idx];
        for (char ch : possibleLetters.toCharArray()) {
            backtrackV3(result, digits, track + ch, map, pos + 1);
        }
    }


    /**
     * Understanding the following Breadth-First-Search Solution
     * Same as letterCombinationsV1 but without outer-for-loop
     *
     * Time Cost 8ms
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV2(String digits) {
        LinkedList<String> queue = new LinkedList<>();
        if (digits.isEmpty()) {
            return queue;
        }

        /**
         * Add "", "" here for sake of the array index from 0 to 9.
         */
        String[] map = new String[] {"",   "",   "abc",  "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        queue.add("");
        while (queue.peek().length() != digits.length()) {
            String head = queue.poll();
            int idx = digits.charAt(head.length()) - '0';
            for (char ch : map[idx].toCharArray()) {
                queue.offer(head + ch);
            }
        }
        return queue;
    }


    /**
     * Understanding the following Breadth-First-Search Solution
     *
     * Time Cost 11 ms
     *
     *
     * @see <a href="https://afteracademy.com/blog/letter-combinations-of-a-phone-number"></a>
     *
     * e.g. digits = "23"
     * i:0, digit = 2, queue:"", queue.peek.length == 0, head:"", for-loop:"abc", queue:"a", "b", "c"
     * i:1, digit = 3, queue:"a", "b", "c",
     *                 queue.peek.length == 1, head:"a", queue:"b", "c",
     *                                        for-loop:"def", queue:"b", "c", "ad", "ae", "af"
     *                 queue.peek.length == 1, head:"b", queue:"c", "ad", "ae", "af",
     *                                        for-loop:"def", queue: "c", "ad", "ae", "af", "bd", "be", "bf"
     *                 queue.peek.length == 1, head:"c", queue: "ad", "ae", "af", "bd", "be", "bf",
     *                                        for-loop:"def", queue: "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV1(String digits) {
        LinkedList<String> queue = new LinkedList<>();
        if (digits.isEmpty()) {
            return queue;
        }
        /**
         * Add "0", "1" here for sake of the array index from 2 to 9.
         */
        String[] map = new String[] {"0",   "1",   "abc",  "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        queue.add("");
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            while (queue.peek().length() == i) {
                /**
                 * Both remove and poll operations retrieve and remove the head (first element) of this list.
                 * So remove or poll are both satisfied here.
                 */
                //queue.remove();
                String head = queue.poll();
                for (char ch : map[digit].toCharArray()) {
                    /**
                     * Don't use push, push operation inserts the element at the front of this list.
                     * push equals addFirst.
                     * queue.push(head + ch);
                     *
                     * add and offer operation appends the specified element to the end of this list.
                     * add equals addLast, also equal offer and offerLast.
                     */
                    //queue.add(head + ch);
                    queue.offer(head + ch);
                }
            }
        }

        return queue;
    }


    /**
     * Understanding the following Backtracking Solution
     *
     * Time Cost 1ms
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        /**
         * Corner case digits = "", return expected []
         */
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        StringBuilder track = new StringBuilder();
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backtrack(result, digits, track, map, 0);
        return result;
    }

    /**
     * digits = "23"
     * 2 --> abc, 3 --> def
     *          root
     *        /  |  \      pos:0, select 2
     *       a   b   c
     *       /   |    \    pos:1, select 3
     *    (def) (def) (def)
     *
     *    ad,ae,af
     *    bd,be,bf
     *    cd,ce,cf
     *
     * output:
     * a
     * ad
     * ae
     * af
     * b
     * bd
     * be
     * bf
     * c
     * cd
     * ce
     * cf
     *
     * @see <a href="https://www.interviewbit.com/blog/letter-combinations-of-a-phone-number/"></a>
     * @param result
     * @param digits
     * @param track
     * @param map
     * @param pos
     */
    public void backtrack(List<String> result, String digits, StringBuilder track, Map<Character, String> map, int pos) {
        System.out.println(track.toString());
        if (track.length() == digits.length()) {
            result.add(track.toString());
            return;
        }
        String possibleLetters = map.get(digits.charAt(pos));
        for (char ch : possibleLetters.toCharArray()) {
            track.append(ch);
            backtrack(result, digits, track, map, pos + 1);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
