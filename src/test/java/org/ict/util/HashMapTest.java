package org.ict.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

public class HashMapTest {
    
    @Test
    public void testGroupBy() {
        List<User> users = Lists.newArrayList();
        for(int i = 0; i < 5; i++) {
            User user = new User();
            if (i % 2 == 0) {
                user.setType("Even");
                user.setName("user"+ i);
            } else {
                user.setType("Odd");
                user.setName("user"+ i);
            }
            users.add(user);
        }
        
        Map<String, List<User>> g = users.stream().collect(Collectors.groupingBy(User::getType));
        g.forEach((k,v)->{
            System.out.println("type: " + k + "User : " + v);
        });
    }
    
    class User {
        private String type;
        
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [type=" + type + ", name=" + name + "]";
        }
        
        
    }
    
    public void testMap() {
        Map<String, String> names = new LinkedHashMap<>();
        names.put("John", "Doe");
        names.put("Fred", "Flintstone");
        names.put("Jane", "Doe");
        names.entrySet().stream()
        .filter(e -> e.getValue().equals("Donkey"))
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(null);
    }

    @Test
    public void testGetNull() {
        Map<Long, Long> map = new LinkedHashMap<Long, Long>();
        map.get(null);
    }
    
	public static void main(String[] args) {
		System.out.println(1<<30);
	}
}


