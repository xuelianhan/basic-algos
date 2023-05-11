package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.*;

/**
 * A transformation sequence from the word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 *
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Constraints:
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * 
 * Solution:
 * The idea is to use BFS. 
 * We start from the given start word, 
 * traverse all words that adjacent (differ by one character) to it 
 * and keep doing so until we find the target word or we have traversed all words.
 * 
 * @see <a href="https://algs4.cs.princeton.edu/41graph/WordLadder.java.html"></a>
 * @see <a href="https://bradfieldcs.com/algos/graphs/word-ladder/"></a>
 * @see <a href="https://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/"></a>
 * @see <a href="https://www.cs.cmu.edu/~adamchik/15-121/labs/HW-4%20Word%20Ladder/lab.html"></a>
 * @see <a href="https://www.cs.cmu.edu/~adamchik/15-121/"></a>
 * @see <a href="http://www.learn4master.com/algorithms/bread-first-search/leetcode-word-ladder-solution-in-java"></a>
 *
 * This problem is same as MinimumGeneticMutation of LC433
 *
 * LC127, Hard, frequency=55
 *
 */
public class WordLadder {
	
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		String[] wordArr = {"hot","dot","dog","lot","log","cog"};
		int res = ladderLengthV3(beginWord, endWord, new ArrayList<>(Arrays.asList(wordArr)));
		System.out.println(res);
	}


	/**
	 * Understanding the following Double-Direction Breadth First Solution
	 *
	 * Solution in ladderLengthV2 starts only from one-direction beginWord.
	 * We may also start from the beginWord and the endWord simultaneously.
	 * Once we meet the same word, we are done.
	 * The following code provides such a double-direction search solution.
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public static int ladderLengthV3(String beginWord, String endWord, List<String> wordList) {
		Set<String> words = new HashSet<String>(wordList);
		if (!words.contains(endWord)) {
			return 0;
		}
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		Set<String> visited = new HashSet<>();
		beginSet.add(beginWord);
		endSet.add(endWord);
		int len = 1;
		while(!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
			}
			Set<String> middle = new HashSet<>();
			for (String word : beginSet) {
				char[] chr = word.toCharArray();
				for (int i = 0; i < chr.length; i++) {
					char old = chr[i];
					for (char c = 'a'; c <= 'z'; c++) {
						if (chr[i] == c) {
							continue;
						}
						chr[i] = c;
						String target = String.valueOf(chr);
						if (endSet.contains(target)) {
							return len + 1;
						}
						if (!visited.contains(target) && words.contains(target)) {
							middle.add(target);
							visited.add(target);
							//for save memory
							words.remove(target);
						}
					}
					chr[i] = old;
				}
			}//one-level end
			beginSet = middle;
			len++;//search tree depth increment
		}
		return 0;
	}

	/**
	 * Understanding the following Breadth First Solution
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public static int ladderLengthV2(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<>(wordList);
		if (!wordSet.contains(endWord)) {
			return 0;
		}

		/**
		 * beginWord is the first level.
		 */
		int level = 1;
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();

		queue.add(beginWord);
		visited.add(beginWord);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (cur.equals(endWord)) {
					return level;
				}
				/**
				 * Replace each character in the cur array,
				 * check the newly generated string whether in wordSet and has not been visited,
				 * then we add the newly generated string to the tail of the queue.
				 */
				char[] curArr = cur.toCharArray();
				for (int j = 0; j < curArr.length; j++) {
					char temp = curArr[j];
					/**
					 * generate new string and push into the queue if it matches the condition.
					 * This step has many invalid attempts which can be cut off.
					 */
					for (char ch = 'a'; ch <= 'z'; ch++) {
						curArr[j] = ch;
						String generatedStr = new String(curArr);
						if (!visited.contains(generatedStr) && wordSet.contains(generatedStr)) {
							queue.offer(generatedStr);
							/**
							 * marked generatedStr as visited is very important.
							 */
							visited.add(generatedStr);
						}
					}
					curArr[j] = temp;
				}
			}
			level++;
		}

		/**
		 * Not found such a sequence from beginWord to endWord.
		 */
		return 0;
	}


	
	/**
	 * This version is very slow when input huge data
	 * Returns length of the shortest chain to reach 'target' from 'start'
	 * using minimum number of adjacent moves. 
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
    public int ladderLengthV1(String beginWord, String endWord, List<String> wordList) {
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

        //Create a queue for BFS and insert begin word as source vertex 
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Map<String, Boolean> visited = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        visited.put(beginWord, true);
        // Chain length for begin word is 1 
        countMap.put(beginWord, 1);

        for(String word : words) {
            if (!beginWord.equalsIgnoreCase(word)) {
                visited.put(word, false);
                countMap.put(word, 0);
            }
        }

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            System.out.println("cur:" + cur);
            for (String word : words) {
                System.out.println("word:" + word);
                // Process a dictionary word if it is adjacent to current word (or vertex) of BFS 
                if (isDiffOne(cur, word) && (visited.get(word) == Boolean.FALSE)) {
                    int count = countMap.get(cur);//(countMap.get(cur) == null ? 0 : countMap.get(cur));
                    countMap.put(word, count + 1);
                    queue.add(word);
                    // Mark the word visited, so it will not be processed again.
                    visited.put(word, true);
                    // If the endWord is found, return current chain length directly.
                    if (word.equalsIgnoreCase(endWord)) {
                        return countMap.get(word);
                    }
                }
            }
        }
        return 0;
    }
	
	/**
	 * To check if two strings differ by exactly one character.
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isDiffOne(String a, String b) {
		if (a == null || b == null) {
			return false;
		}
		if (a.length() != b.length()) {
			return false;
		}
		// to store count of differences 
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


	/**
	 * Time Limit Exceeded
	 * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
	 * "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long
	 *
	 * --------hot--dot--dog--lot--log--cog--hit
	 * ---------0----1----2----3----4----5----6
	 * ----------------------------------------
	 * hot-0|---0----1----2----1----2----2----1
	 * dot-1|---1----0----1----1----2----2----2
	 * dog-2|---2----1----0----2----1----1----3
	 * lot-3|---1----1----2----0----1----2----2
	 * log-4|---2----2----1----1----0----1----3
	 * cog-5|---2----2----1----2----1----0----3
	 * hit-6|---1----2----3----2----3----3----0
	 *
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<>(wordList);
		if (!wordSet.contains(endWord)) {
			return 0;
		}

		/**
		 * Add beginWord to the end of wordList.
		 */
		wordList.add(beginWord);
		/**
		 * Build Distance Field for all the words in the wordList.
		 */
		int n = wordList.size();
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = countDistance(wordList.get(i), wordList.get(j));
			}
		}
		System.out.println(Arrays.deepToString(dist));
		/**
		 * beginWord is the first level.
		 */
		int level = 1;
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();

		queue.add(n-1);
		visited.add(n-1);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				if (wordList.get(cur).equals(endWord)) {
					return level;
				}
				for (int j = 0; j < n; j++) {
					if ((dist[cur][j] != 1 && dist[j][cur] != 1) || visited.contains(j)) {
						continue;
					}
					queue.offer(j);
					visited.add(j);
				}
			}
			level++;
		}
		return 0;
	}

	public static int countDistance(String w1, String w2) {
		int cnt = 0, n = w1.length();
		for (int i = 0; i < n; i++) {
			if (w1.charAt(i) != w2.charAt(i)) {
				cnt++;
			}
		}
		return cnt;
	}
}
