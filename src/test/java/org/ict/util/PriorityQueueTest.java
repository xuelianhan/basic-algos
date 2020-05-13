package org.ict.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


//import org.ict.algorithm.util.RandomGenUtil;

public class PriorityQueueTest {

	public static void main(String[] args) {
		testQueueWithAscendComparator();
		//testQueueWithDefaultComparator();
		
	}
	
	public static void testQueueWithDescendComparator() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			//int randomNum = RandomGenUtil.getRandomNumberInRangeV4(1, 11);
			list.add(i);
		}
		System.out.println("list:" + list);
		
		// Creates a PriorityQueue with the specified initial capacity that orders its elements according to their descend ordering.
		PriorityQueue<Integer> queue = new PriorityQueue<>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
			
		});
		for (int v : list ) {
			queue.offer(v);
		}
		
		while (!queue.isEmpty()) {
			int head = queue.poll();
			System.out.println("head:" + head);
		}
	}
	
	public static void testQueueWithAscendComparator() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			//int randomNum = RandomGenUtil.getRandomNumberInRangeV4(1, 11);
			list.add(i);
		}
		System.out.println("list:" + list);
		
		// Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.
		PriorityQueue<Integer> queue = new PriorityQueue<>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
			
		});
		for (int v : list ) {
			queue.offer(v);
		}
		
		while (!queue.isEmpty()) {
			int head = queue.poll();
			System.out.println("head:" + head);
		}
	}
	
	public static void testQueueWithDefaultComparator() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			//int randomNum = RandomGenUtil.getRandomNumberInRangeV4(1, 11);
			list.add(i);
		}
		System.out.println("list:" + list);
		
		// Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.
		PriorityQueue<Integer> queue = new PriorityQueue<>(10);
		for (int v : list ) {
			queue.offer(v);
		}
		
		while (!queue.isEmpty()) {
			int head = queue.poll();
			System.out.println("head:" + head);
		}
	}
}
