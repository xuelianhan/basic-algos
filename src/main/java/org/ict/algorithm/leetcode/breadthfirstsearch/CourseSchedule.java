package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Queue;
import java.util.LinkedList;

/**
 * here are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * Example 1:
 * Input: 2, [[1,0]] 
 * Output: true
 * Explanation: There are a total of 2 courses to take. 
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take. 
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * LC207
 * 
 * According to the wiki about what Topological sorting:
 * @see https://en.wikipedia.org/wiki/Topological_sorting
 * and the Kahn's algorithm as shown below:
 *
 * L: Emply list that will contain the sorted elements
 * S: Set of all nodes with no incoming edges
 * while S is non-empty do
 *     remove a node n from S
 *     add n to tail of L
 *     for each node m with an edge e from n to m do
 *         remove edge e from the graph
 *         if m has no other incoming edges then
 *             insert m into S
 * if grpah has edges then
 *     return error(graph has at least one cycle)
 * else 
 *     return L(a topologically sorted order)
 */
public class CourseSchedule {
	
    /**
     * Uses Adjacent Matrix to represent the graph.
     *
     */
	public boolean canFinishV1(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses];  // i -> j means i is dependent on j
		int[] indegree = new int[numCourses];

	    // Initializes the indegree array and adjacent matrix graph	
		for (int i = 0; i < prerequisites.length; i++) {
		    int ready = prerequisites[i][0];
            int pre = prerequisites[i][1]; //ready -> pre means ready is dependent on pre
            if (matrix[pre][ready] == 0) { //[pre][ready] == 0 means pre not point to ready.
                indegree[ready]++;
            }    
            matrix[pre][ready] = 1;
		}

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();// queue is set of all nodes with no incoming edges
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
		return count == numCourses;
    }
}
