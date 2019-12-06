package org.ict.algorithm.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @see https://stackoverflow.com/questions/23480346/compare-and-swap
 * @see http://tutorials.jenkov.com/java-concurrency/compare-and-swap.html
 *
 */
public class ReferenceCASTest {

	private AtomicReference<Node> ref = new AtomicReference<>();
	
	public static void main(String[] args) {
		ReferenceCASTest test = new ReferenceCASTest();
		boolean flag1 = test.cmp(new Node(1, 2, 3), 1, 2, 3, 4, 5, 6);
		boolean flag2 = test.cmp(new Node(1, 2, 4), 1, 2, 3, 4, 5, 6);
		System.out.println("flag1:" + flag1 + ", flag2:" + flag2);
	}
	
	public boolean cmp(Node foo, Integer exp1, Integer exp2, Integer exp3, Integer new1, Integer new2, Integer new3) {
		Node current = ref.get();
		if (foo.getVal1() == exp1 && foo.getVal2() == exp2 && foo.getVal3() == exp3) {
		  return ref.compareAndSet(current, new Node(new1, new2, new3));
		} else {
		  return false;
		}
	}
}
