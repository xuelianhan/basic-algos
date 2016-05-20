package org.ict.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @see http://stackoverflow.com/questions/567068/java-synchronized-block-vs-collections-synchronizedmap
 */
public class SynchronizedMapTest {

	private static Map<String, List<String>> synchronizedMap = Collections.synchronizedMap(new HashMap<String, List<String>>());
	
	public void doWork(String key) {
		List<String> values = null;
		while ((values = synchronizedMap.remove(key)) != null) {
			System.out.println(values);
		}
	}
	
	public void addToMap(String key, String value) {
		synchronized(synchronizedMap) {
			if (synchronizedMap.containsKey(key)) {
				synchronizedMap.get(key).add(value);
			} else {
				List<String> valuesList = new ArrayList<String>();
				valuesList.add(value);
				synchronizedMap.put(key, valuesList);
			}
		}
	}
	
	public static void main(String[] args) {
		SynchronizedMapTest smt = new SynchronizedMapTest();
		for (int i = 0; i < 10; i++) {
			SynchronizedMapTestThread1 t1 = new SynchronizedMapTestThread1("Thread-t1-" + i);
			t1.setSmt(smt);
			t1.setKey(i+"");
			
			SynchronizedMapTestThread2 t2 = new SynchronizedMapTestThread2("Thread-t2-" + i);
			t2.setSmt(smt);
			t2.setKey(i+"");
			t2.setValue(i+ "");
			

			Thread r = new Thread(t2);
			Thread t = new Thread(t1);
			
			t.start();
			r.start();
		}
	}
}

class SynchronizedMapTestThread1 implements Runnable {
	
	private String name;
	
	private SynchronizedMapTest smt;
	
	private String key;
			
	public void run() {
		System.out.println(name + " is running");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		smt.doWork(key);
	}

	public SynchronizedMapTestThread1(String name) {
		this.name = name;
	}
	
	public void setSmt(SynchronizedMapTest smt) {
		this.smt = smt;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}

class SynchronizedMapTestThread2 implements Runnable {
	
	private String name;
	
	private String key;
	
	private String value;
	
	private SynchronizedMapTest smt;
	
	public SynchronizedMapTestThread2(String name) {
		this.name = name;
	}
	
	public void run() {
		System.out.println(name + " is running");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		smt.addToMap(key, value);
	}
	
	public void setSmt(SynchronizedMapTest smt) {
		this.smt = smt;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
