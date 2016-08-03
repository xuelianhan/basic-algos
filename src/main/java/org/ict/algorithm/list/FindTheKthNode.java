package org.ict.algorithm.list;

import java.util.Iterator;
import java.util.LinkedList;


public class FindTheKthNode<Item> {
	
	public static String nthTolastV2(LinkedList<String> list, int k) {
		String result = "";
		if (k <= 0) {
			return result;
		}
		
    	Iterator<String> iter = list.descendingIterator();
    	int i = 1;
    	while (iter.hasNext()) {
    		result = iter.next();
    		if (i == k) {
    			break;
    		}
    		i++;
    	}
    	
		return result;
	}

   /* public static Node nthToLast(SingleLinkedList list, int k) {
        if (k <= 0) return null; 

        Node p1 = list.getFirst();
        Node p2 = list.getFirst();

        for (int i = 0; i < k - 1; i++) {
            if (p2 == null) {
                return null;
            }
            p2 = p2.next;
        }
        
        if (p2 == null) {
            return null;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next; 
        }
        return p1;
    }*/

    public static void main(String[] args) {
    	LinkedList<String> list = new LinkedList<String>();
    	for (char i = 65; i < 69; i++) {
    		list.add(Character.toString(i));
    	}
    	
    	System.out.println(list);
    	
    	for (int i = 1; i <= list.size(); i++) {
    		String s = nthTolastV2(list, i);
        	System.out.println("The " + i + "th data to last is " + s);
    	}
    }
}
