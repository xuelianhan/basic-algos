package org.ict.algorithm.list;

import java.util.HashSet;
import java.util.Iterator;
import org.ict.algorithm.util.StdOut;

public class DelDuplicatedNodes {

    public static void delDupNodes(SingleLinkedList<String> list) {
       HashSet<String> set = new HashSet<String>(); 
       Iterator<String> iter = list.iterator();
       while (iter.hasNext()) {
            String s = iter.next();
            if (set.contains(s)) {
                list.remove(s);
            } else {
                set.add(s);
            }
       }
    }

    public static void main(String[] args) {
        SingleLinkedList<String> list = new SingleLinkedList<String>();
        list.add("A");
        list.add("B");
        list.add("B");
        list.add("C");
        list.add("C");
        list.add("D");
        list.add("D");
        
        StdOut.println("Before remove duplicated nodes:" + list.toString());

        delDupNodes(list);

        StdOut.println("After remove duplicated nodes:" + list);
    }

}
