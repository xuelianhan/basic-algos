package org.ict.algorithm.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class ListUtil {

    
    public static String listToCommaSeperatedStr(List<String> list) {
        return list.stream()
                   .map(String::toString)
                   .collect(Collectors.joining(","));
    }
    
    
    public static void main(String[] args) {
        String switchs = "1,1,1,0,0,0,0,0,0,0,0,1,1,1,1";
        List<String> items = Arrays.asList(switchs.split(","));
        List<String> conditions = Lists.newArrayList();
        for (int j = 0; j < items.size(); j++) {
            conditions.add(j+1+"");
        }
        System.out.println("items size:" + items.size());
        System.out.println("conditions size:" + conditions.size());
        System.out.println("conditions before remove:" + conditions);
        Iterator<String> iter = conditions.iterator();
        Iterator<String> iter1 = items.iterator();
        int i = 0;
        while (iter.hasNext() && iter1.hasNext()) {
            String item = iter1.next();
            String e = iter.next();
            if ("0".equals(item)) {
                System.out.println("remove:" + i);
                iter.remove();
            }
            i++;
        }
        
        System.out.println("conditions size after remove:" + conditions.size());
        System.out.println("conditions after remove:" + conditions);
    }
}
