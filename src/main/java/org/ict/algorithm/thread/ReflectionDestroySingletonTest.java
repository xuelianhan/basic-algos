package org.ict.algorithm.thread;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionDestroySingletonTest {

	public static void main(String[] args) {
		DefaultThreadPool instanceOne = DefaultThreadPool.getInstance();
		DefaultThreadPool instanceTwo = null;
		
		Constructor[] constructors = DefaultThreadPool.class.getDeclaredConstructors();
		for (Constructor c : constructors) {
			//Below code will destory the singleton pattern
			c.setAccessible(true);
			try {
				instanceTwo = (DefaultThreadPool)c.newInstance();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			break;
		}
		
		System.out.println(instanceOne.hashCode());
		System.out.println(instanceTwo.hashCode());
	}
	

}
