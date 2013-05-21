package org.ict.nio;



public class NullObjectTest {
	private int x;
	private int y;

	public static void main (String[] args) {
		System.out.println("new NullObjectTest()  size="+ObjectSizeFetcher.getObjectSize(new NullObjectTest()));
		System.out.println("new String() size="+ObjectSizeFetcher.getObjectSize(new String()));
		System.out.println("new Object() size="+ObjectSizeFetcher.getObjectSize(new Object()));
		System.out.println("new String[]{\"a\",\"b\"} size="+ObjectSizeFetcher.getObjectSize(new String[]{"a", "b"}));
	}
}
