package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * 
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * 
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 * 
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * Output: 0
 * 
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * 
 * Solution:
 * The idea is to use BFS. 
 * We start from the given start word, 
 * traverse all words that adjacent (differ by one character) to it 
 * and keep doing so until we find the target word or we have traversed all words.
 * 
 * @see https://algs4.cs.princeton.edu/41graph/WordLadder.java.html
 * @see https://bradfieldcs.com/algos/graphs/word-ladder/
 * @see https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/
 * @see https://www.cs.cmu.edu/~adamchik/15-121/labs/HW-4%20Word%20Ladder/lab.html
 * @see https://www.cs.cmu.edu/~adamchik/15-121/
 *
 * LC127
 *
 */
public class WordLadder {

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (wordList == null || wordList.size() == 0) {
			return 0;
		}
		if (beginWord == null || endWord == null) {
			return 0;
		}
		if (beginWord.length() != endWord.length()) {
			return 0;
		}
		Set<String> words = new HashSet<String>();
		words.addAll(wordList);
		if (!words.contains(endWord)) {
			return 0;
		}
		Queue<String> queue = new LinkedList<>();
		Map<String, Boolean> map = new HashMap<>();
		Iterator<String> iter = words.iterator();
		String first = iter.next();
		queue.add(first);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			
		}
        return 0;
    }
	
	public static boolean isNeighbor(String a, String b) {
		if (a == null || b == null) {
			return false;
		}
		if (a.length() != b.length()) {
			return false;
		}
		int differ = 0; 
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				differ++;
			}
			if (differ > 1) {
				return false;
			}
		}
		// If we assume no duplicates in the word list, then we can return true directly.
		// The following return statement is equivalent on above assumption.
		//return true
		return (differ== 1 ? true : false);
	}
}
