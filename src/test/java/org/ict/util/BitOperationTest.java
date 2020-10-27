package org.ict.util;

/**
 * X % 2^n = X & (2^n – 1)
 *
 * (n >>> 1) = (n / 2)
 * (n >>> 2) = (n / 4)
 */
public class BitOperationTest {

	static final int MAXIMUM_CAPACITY = 1 << 30;//2^30

	public static void main(String[] args) {
		int x = tableSizeFor(5);
		System.out.println(x);
		//testShift();
		//testAndAndRightShift();
	}

	/**
	 * @see <a href="https://github.com/AobingJava/JavaFamily"></a>
	 * @see <a href="https://mp.weixin.qq.com/s/ktre8-C-cP_2HZxVW5fomQ"></a>
	 * @param cap
	 * @return
	 */
	public static int tableSizeFor(int cap) {
		int n = cap - 1;// cap=5, cap - 1 = 4;
		System.out.println("n=" + n + ", n >>> 1 = " + (n >>> 1));
		n |= n >>> 1;// 4 >>> 1 = 2, 4 | 2 = (0100 | 0010) = (0110) = 6
		System.out.println("n=" + n + ", n >>> 2 = " + (n >>> 2));
		n |= n >>> 2;
		System.out.println("n=" + n + ", n >>> 4 = " + (n >>> 4));
		n |= n >>> 4;
		System.out.println("n=" + n + ", n >>> 8 = " + (n >>> 8));
		n |= n >>> 8;
		System.out.println("n=" + n + ", n >>> 16 = " + (n >>> 16));
		n |= n >>> 16;
		System.out.println("n=" + n);
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	/**
	 * bitMask0, 1
	 * bitMask1, 2
	 * bitMask2, 4
	 * bitMask3, 8
	 * bitMask4, 16
	 * bitMask5, 32
	 * bitMask6, 64
	 * bitMask7, 128
	 * bitMask8, 256
	 * bitMask9, 512
	 * bitMask10, 1024
	 * bitMask11, 2048
	 */
	public static void testShift() {
		for (int i = 0; i < 12; i++) {
			int bitMask = (1 << i);//2^i
			System.out.println("bitMask" + i + ", " + bitMask);
		}
	}
	public static void testAndAndRightShift() {
		int x1 = (16 % 4);
		int x2 = (16 & 3);
		int x3 = (16 >> 2);
		System.out.println("x1:" + x1);// 0
		System.out.println("x2:" + x2);// 0
		System.out.println("x3:" + x3);// 4

		int n = 16;
		System.out.println("n:" + n);//16,4,1,1
		while ((n & 3) == 0) { // n%4 == 0
			n >>= 2; // Division by 4=2^2
			System.out.println("n:" + n);
		}
		System.out.println("n:" + n);
	}
}
