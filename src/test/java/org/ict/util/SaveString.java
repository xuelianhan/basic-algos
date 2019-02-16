package org.ict.util;

import java.math.BigDecimal;
import java.util.Map;



/**
 * 
 * @see https://crunchify.com/hashmap-vs-concurrenthashmap-vs-synchronizedmap-how-a-hashmap-can-be-synchronized-in-java/
 *
 */
public class SaveString extends Thread {
	private String threadName;
	
	private Map<Long, BigDecimal> map;
	
	public SaveString(String threadName, Map<Long, BigDecimal> map) {
		this.threadName = threadName;
		this.map = map;
	}
	
	@Override
	public void run() {
		try {
			for (long i = 0; i < 100; i++) {
				BigDecimal val = compute(i);
				Thread.sleep(1000);
				if (BigDecimal.valueOf(i).compareTo(val) != 0) {
					System.err.println("ThreadName:" + Thread.currentThread().getName() + ", input i:" + i + ", compute get val:" + val);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BigDecimal compute(Long key) {
		BigDecimal val = map.get(key);
		System.out.println("ThreadName:" + Thread.currentThread().getName() + ", get key:" + key + ",val:" + val);
		if (val == null) {
			synchronized(SaveString.class) {
				val = map.get(key);
				if (val == null) {
					//val = computer.compute(key);
					val = BigDecimal.valueOf(key);
					System.out.println("ThreadName:" + Thread.currentThread().getName() + ", put key:" + key + ",val:" + val);
					map.put(key, val);
				}
			}
		}
		return val;
	}
}
