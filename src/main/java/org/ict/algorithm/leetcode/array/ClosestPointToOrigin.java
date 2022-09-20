package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a set of points in a cartesian plane, and a start point ,
 * find the k closest points to the starting point.
 * Points = [(1,2),(2,3),(4,6),(7,9)]
 * Start Point = (2,2)
 * Find 2 closest points to start point.
 * 
 * Find the TopM max, use the min-heap, 
 * construct min-heap with M value, 
 * adjust heap if new value is bigger than the root, 
 * at last we get the M max value in the min-heap
 * 
 * Find the TopM min, use the max-heap, 
 * construct max-heap with M value, 
 * adjust heap if new value is smaller than the root, 
 * at last we get the M min value in the max-heap
 * 
 * Please referer the Algorithms 4th Edition by Robert Sedgewick
 * @see <a href="https://leetcode.com/discuss/interview-question/124642/k-closest-points-to-a-starting-point-in-cartesian-plane"></a>
 * @see <a href="http://www.zrzahid.com/k-closest-points/"></a>
 *
 * This problem is one kind of amz oa
 */
public class ClosestPointToOrigin {
	
	public ClosestPointToOrigin() {}
	
	public static void main(String[] args) {
		ClosestPointToOrigin instance = new ClosestPointToOrigin();
		Point p1 = new Point(1, -1);
		Point p2 = new Point(1, 2);
		Point p3 = new Point(3, 4);
		Point p4 = new Point(1, 3);
		
		List<Point> points = new ArrayList<>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		
		List<Point> result = instance.closestPoints(points, 3);
		System.out.println(result);
		
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		List<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(-1);
		matrix.add(m1);
		
		List<Integer> m2 = new ArrayList<>();
		m2.add(1);
		m2.add(2);
		matrix.add(m2);
		
		List<Integer> m3 = new ArrayList<>();
		m3.add(3);
		m3.add(4);
		matrix.add(m3);
		
		List<List<Integer>> result2 = instance.closestPoints(matrix, 3, 2);
		System.out.println(result2);
	}


	/**
	 * Find top k minimum in points using max-heap
	 */
	public Point[] closestPoints(Point[] array, Point origin, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<Point> (k, new Comparator<Point> () {
            @Override
            public int compare(Point a, Point b) {
                return (int) (getDistance(a, origin) - getDistance(b, origin));
            }
        });
        
        for (int i = 0; i < array.length; i++) {
            pq.offer(array[i]);
            if (pq.size() > k) {
				pq.poll();
			}
        }
        
        int index = 0;
        Point[] rvalue = new Point[k];
        while (!pq.isEmpty()) {
			rvalue[index++] = pq.poll();
		}
        return rvalue;
    }

    private double getDistance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
    
	public List<List<Integer>> closestPoints(List<List<Integer>> matrix, int N, int k) {
		if (k > N) {
			return new ArrayList<List<Integer>>();
		}
		List<Point> points = closestPoints(convertToPoint(matrix), k);
		return convertToList(points);
	}
	
	private List<List<Integer>> convertToList(List<Point> points) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (Point p : points) {
			List<Integer> entry = new ArrayList<>();
			entry.add(p.getX());
			entry.add(p.getY());
			result.add(entry);
		}
		return result;
	}
	
	private List<Point> convertToPoint(List<List<Integer>> matrix) {
		List<Point> points = new ArrayList<>();
		for (List<Integer> entry : matrix) {
			Point p = new Point(entry.get(0), entry.get(1));
			points.add(p);
		}
		return points;
	}

	/**
	 * Find top k minimum in points.
	 * @param points
	 * @param k
	 * @return
	 */
	public List<Point> closestPoints(List<Point> points, int k) {
		List<Point> result = new ArrayList<>();
		// Find top k minimum in points using max-heap
		PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, (p1, p2) -> {
			double d1 = getDistance(p1);
			double d2 = getDistance(p2);

			if (d1 == d2) {
				return 0;
			}
			return (d1 < d2? 1 : -1);
		});
		
		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			if (i < k) {
				maxHeap.offer(p);
			} else if (getDistance(p) < getDistance(maxHeap.peek())) {
				maxHeap.poll();
				maxHeap.offer(p);
			}
		}
		
		for (Point p : maxHeap) {
			result.add(p);
		}
		return result;
	}
	
	/**
	 * return distance from point to origin
	 * @param p
	 * @return
	 */
	private double getDistance(Point p){
		return Math.sqrt(p.x * p.x + p.y * p.y);
	}
	
	private static class Point {
		private int x;
		private int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		
		@Override
		public String toString() {
			return ("(" + x + " ," + y + ")");
		}
	}
}
