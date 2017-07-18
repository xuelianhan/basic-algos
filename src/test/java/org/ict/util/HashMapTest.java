package org.ict.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapTest 
{

    @Test
    public void testGetNull() {
        Map<Long, Long> map = new LinkedHashMap<Long, Long>();
        map.get(null);
    }
    
	public static void main(String[] args) {
		System.out.println(1<<30);
	}
}


