package org.ict.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import com.google.common.collect.Lists;

public class HashMapTest {
	
	public static void main(String[] args) {
		Map<Long, BigDecimal> map = new HashMap<>();
		for (int i = 0 ; i < 10; i++) {
			new SaveString("Thread-"+(i+1), map).start();
		}
	}
	
	public void testShiftLeft() {
		System.out.println(1<<30);
	}
	
    @Test 
    public void testGroupBy() {
        List<User> users = Lists.newArrayList();
        for(int i = 0; i < 5; i++) {
            User user = new User();
            if (i % 2 == 0) {
                user.setType("Even");
                user.setName("user"+ i);
                user.setMoney(new BigDecimal(20+i));
            } else {
                user.setType("Odd");
                user.setName("user"+ i);
                user.setMoney(new BigDecimal(30+i));
            }
            users.add(user);
        }
        
        Map<String, List<User>> g = users.stream().collect(Collectors.groupingBy(User::getType));
        g.forEach((k,v)->{
            System.out.println("type: " + k + "User size : " + v.size());
            BigDecimal sum = v
                    .stream()
                    .map(User::getMoney)
                    .filter(b -> (b != BigDecimal.ZERO))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println("type: " + k + "sum : " + sum);
        });
    }
    
    class User {
        private String type;
        
        private String name;
        
        private BigDecimal money;

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

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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
    
	
}


