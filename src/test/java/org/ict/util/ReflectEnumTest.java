package org.ict.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @see https://stackoverflow.com/questions/1893349/dynamically-converting-java-object-of-object-class-to-a-given-class-when-class-n
 * @see https://stackoverflow.com/questions/24260011/get-value-of-enum-by-reflection
 * @author hanxuelian001
 *
 */
public class ReflectEnumTest {
	
	public static Object codeOf(String code, Class clazz) {
		Class<?> c = null;
		try {
			c = clazz;
			Object[] objects = c.getEnumConstants();
			for (Object obj : objects) {
				Method method = c.getDeclaredMethod("getKey");
			    String val = (String) method.invoke(obj);
			    if (val.equals(code)) {
			    	Object newObj = clazz.cast(obj);
			    	return newObj;
			    }
			}
			return null;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Object codeOf(String code, String className) {
		Class<?> c = null;
		try {
			c = Class.forName(className);
			Object[] objects = c.getEnumConstants();
			for (Object obj : objects) {
				Method method = c.getDeclaredMethod("getKey");
			    String val = (String) method.invoke(obj);
			    if (val.equals(code)) {
			    	Object newObj = Class.forName(className).cast(obj);
			    	return newObj;
			    }
			}
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Object obj1 = codeOf("SytemRunning", "org.ict.util.Mode");
		Object obj2 = codeOf("SytemRunning", Mode.class);
		System.out.println(obj1);
		System.out.println(obj2);
	}
}
