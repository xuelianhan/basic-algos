package org.ict.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import org.ict.algorithm.util.RandomGenUtil;


/**
 * Min-Heap Queue, the head is the smallest, if we only keep 3 elements in the queue,
 * we need to poll surplus ele from the queue. At last the largest 3 elements are in the queue
 * 
 * 
 * Max-Heap Queue, the head is the largest, if we only keep 3 elements in the queue,
 * We need to poll surplus ele from the queue. At last the smallest 3 elements are in the queue.
 * 
 * Both two cases above, you can imagine that the elements are forced out one by by.
 * 
 * ---------------
 * 321413524892
 * ---------------
 * 
 * 
 * @author hanxuelian001
 *
 */
public class PriorityQueueTest {

	public static void main(String[] args) {
		//testQueueWithDescendComparator(3);
		//testQueueWithAscendComparator(3);
		testQueueWithDefaultComparator();
		
	}
	
	/*
	 * The queue will remain the least k elements in it
	 */
	public static void testQueueWithDescendComparator(int k) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			int randomNum = RandomGenUtil.getRandomNumberInRangeV4(1, 11);
			list.add(randomNum);
		}
		System.out.println("list:" + list);
		
		// Creates a PriorityQueue with the specified initial capacity that orders its elements according to their descend ordering.
		// queue is a max-heap with least k elements in it.
		PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
			
		});
		for (int v : list ) {
			queue.offer(v);
			if (queue.size() > k) {
				queue.poll();
			}
			System.out.println("queue:" + queue);
		}
		
		while (!queue.isEmpty()) {
			int head = queue.poll();
			System.out.println("head:" + head);
		}
	}
	
	/**
	 * The queue will remain the largest k elements in it
	 * @param k
	 */
	public static void testQueueWithAscendComparator(int k) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			int randomNum = RandomGenUtil.getRandomNumberInRangeV4(1, 11);
			list.add(randomNum);
		}
		System.out.println("list:" + list);
		
		// Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.
		// queue is a min-heap with largest k elements in it.
		PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
			
		});
		for (int v : list ) {
			queue.offer(v);
			if (queue.size() > k) {
				queue.poll();
			}
			System.out.println("queue:" + queue);
		}
		
		while (!queue.isEmpty()) {
			int head = queue.poll();
			System.out.println("head:" + head);
		}
	}
	
	/**
	 * Queue size is increasing when elements are offered one by one.
	 * Queue poll sequence is increasing order
	 */
	public static void testQueueWithDefaultComparator() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			int randomNum = RandomGenUtil.getRandomNumberInRangeV4(1, 11);
			list.add(randomNum);
		}
		System.out.println("list:" + list);
		
		// Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.
		PriorityQueue<Integer> queue = new PriorityQueue<>(1);
		for (int v : list ) {
			queue.offer(v);
			System.out.println("queue:" + queue);
		}
		
		while (!queue.isEmpty()) {
			int head = queue.poll();
			System.out.println("head:" + head);
		}
	}
}
