package org.ict.util;

public class BitOperationTest {

	public static void main(String[] args) {
		testAndAndRightShift();
	}

	public static void testAndAndRightShift() {
		int x1 = (16 % 4);
		int x2 = (16 & 3);
		int x3 = (16 >> 2);
		System.out.println("x1:" + x1);// 0
		System.out.println("x2:" + x2);// 0
		System.out.println("x3:" + x3);// 4

		int n = 16;
		System.out.println("n:" + n);
		while ((n & 3) == 0) { // n%4 == 0
			n >>= 2; // Division by 4=2^2
			System.out.println("n:" + n);
		}
		System.out.println("n:" + n);
	}
}
