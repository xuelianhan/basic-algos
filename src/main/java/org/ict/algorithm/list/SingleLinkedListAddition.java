package org.ict.algorithm.list;

import java.util.Iterator;

/**
 * http://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
 * @author hanxuelian
 *
 */
public class SingleLinkedListAddition {

	public Result addLinkedlistV1(SingleLinkedList<Integer> l1, SingleLinkedList<Integer> l2, int value) {
		Result r = new Result();
		r.data = value % 10;
		if (l1 != null && l2 != null) {
			Iterator<Integer> iter1 = l1.iterator();
			Iterator<Integer> iter2 = l2.iterator();
			while (iter1.hasNext() && iter2.hasNext()) {
				r.carry = (iter1.next() + iter2.next()) > 10 ? 1 :0;
			}
		}
		return r;
	}
	
	class Result {
		private Integer data;
		
		private Integer carry;

		public Integer getData() {
			return data;
		}

		public void setData(Integer data) {
			this.data = data;
		}

		public Integer getCarry() {
			return carry;
		}

		public void setCarry(Integer carry) {
			this.carry = carry;
		}
	}
	
}
