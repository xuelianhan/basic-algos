package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * LC207
 *
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
 * --------------------------------------
 * LC207, Medium, frequency=7
 * Tag: TikTok, Amazon
 */
public class CourseSchedule {
	
    /**
     * Uses Adjacent Matrix to represent the graph.
     * According to the wiki about what Topological sorting:
     * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting"></a>
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
     * if graph has edges then
     *     return error(graph has at least one cycle)
     * else
     *     return L(a topologically sorted order)
     *
     * @author justjiayu
     *
     */
	public boolean canFinishV1(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses];  
		int[] indegree = new int[numCourses];

	    // Initializes the indegree array and adjacent matrix graph	
		for (int i = 0; i < prerequisites.length; i++) {
		    int ready = prerequisites[i][0];
            int pre = prerequisites[i][1]; //pre -> ready means ready is dependent on pre
            // Why indegree increase of vertex ready should be placed in the condition of matrix[pre][ready] == 0?
            // Consider the graph has cycle. The indegree count of vertex ready will be duplicated if lacking of this condition(matrix[pre][ready] == 0)
            if (matrix[pre][ready] == 0) { //[pre][ready] == 0 means pre not point to ready at this moment due to the initialized zero value.
                indegree[ready]++;// this step must be put before changing the matrix[pre][ready] from 0 to 1.
            }    
            matrix[pre][ready] = 1;// mark the value to 1 means pre -> ready.
		}

        // Initializes the S set of nodes with no incoming edges.
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
                if (matrix[course][i] != 0) { // vertex course has path to i if matrix[course][i] not equals to 0. course -> i for short.
                    if (--indegree[i] == 0) { // --indegree[i] means removing the edge of course -> i in graph. So the indegree of i should be decreased one.
                        queue.offer(i); // if the i has no other incoming edges, then put i into queue( S to represent).
                    }
                }
            }
        }
		return count == numCourses;
    }

    /**
     * Uses Adjacent List to represent the graph.
     */
    public boolean canFinishV2(int numCourses, int[][] prerequisites) {
        int[] incomingEdges = new int[numCourses];
        List<Integer>[] goCourses  = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            goCourses[i] = new LinkedList<Integer>();
        }

        // Initializes the graph
        for (int[] pair : prerequisites) {
            incomingEdges[pair[0]]++;
            goCourses[pair[1]].add(pair[0]);
        }

        // Initializes the Set of nodes with no incoming edges.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                queue.offer(i);
            }
        }

        // the core steps of this algorithm as following
        int edgeCnt = prerequisites.length;
        while (!queue.isEmpty()) {
            int from  = queue.poll();
            //iteration here hide a pitfall. Removing ele should be replaced with iterator 
            for (int to : goCourses[from]) {
                edgeCnt--;// remove edge from -> to of the graph.
                if (--incomingEdges[to] == 0) {
                    queue.offer(to);
                } 
            } 
        } 

        return edgeCnt == 0;
    }
}
