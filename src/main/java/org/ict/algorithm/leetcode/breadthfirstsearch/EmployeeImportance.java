package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * You are given a data structure of employee information, 
 * which includes the employee's unique id, 
 * his importance value and his direct subordinates' id.
 * For example, employee 1 is the leader of employee 2, 
 * and employee 2 is the leader of employee 3. 
 * They have importance value 15, 10 and 5, respectively. 
 * Then employee 1 has a data structure like [1, 15, [2]], 
 * and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. 
 * Note that although employee 3 is also a subordinate of employee 1, 
 * the relationship is not direct.
 * Now given the employee information of a company, 
 * and an employee id, you need to return the total importance value of this employee and all his subordinates.
 * Example 1:
 * 
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: 
 * employee 2 and employee 3. 
 * They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * 
 * Note:
 * One employee has at most one direct leader and may have several subordinates.
 * The maximum number of employees won't exceed 2000.
 *
 */
public class EmployeeImportance {
	
	public static void main(String[] args) {
		EmployeeImportance instance = new EmployeeImportance();
		List<Employee> employees = new ArrayList<>();
		List<Integer> e1Sub = new ArrayList<Integer>();
		e1Sub.add(2);
		e1Sub.add(3);
		Employee e1 = instance.new Employee(1, 5, e1Sub);
		
		List<Integer> e2Sub = new ArrayList<Integer>();
		//e2Sub.add(2);
		//e2Sub.add(3);
		Employee e2 = instance.new Employee(2, 3, e2Sub);
		
		List<Integer> e3Sub = new ArrayList<Integer>();
		//e3Sub.add(2);
		//e3Sub.add(3);
		Employee e3 = instance.new Employee(3, 3, e3Sub);
		
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		
		int result = instance.getImportance(employees, 1);
		System.out.println(result);
	}
	
	public int getImportance(List<Employee> employees, int id) {
		if (employees == null || employees.size() == 0) {
			return 0;
		}
		Comparator<Employee> comparator = new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				if (o1.id == o2.id) {
					return 0;
				}
				if (o1.id < o2.id) {
					return -1;
				} else {
					return 1;
				}
			}
			
		};
		Employee[] a = new Employee[employees.size()];
		Arrays.sort(employees.toArray(a), comparator);
        int result = bfs(a, id, comparator);
        return result;
    }
	
	private int bfs(Employee[] a, int id, Comparator<Employee> comparator) {
		int result = 0;
		Queue<Employee> queue = new LinkedList<>();
		Employee key = new Employee();
		key.setId(id);
		int loc = Arrays.binarySearch(a, key, comparator);
		if (loc < 0) {
			return 0;
		}
		Employee first = a[0];
		if (first.subordinates == null || first.subordinates.size() == 0) {
			return 0;
		}
		queue.add(first);
		Map<Integer, Boolean> visited = new HashMap<>();
		visited.put(first.id, true);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Employee cur = queue.poll();
				result += cur.importance;
				if (cur.subordinates == null || cur.subordinates.size() == 0) {
					continue;
				}
				for (Integer sub : cur.subordinates) {
					if (visited.get(sub) == null) {
						Employee subE = new Employee(sub);
						int idx =  Arrays.binarySearch(a, subE, comparator);
						if (idx < 0) {
							continue;
						}
						queue.add(a[idx]);
						visited.put(sub, true);
					}
				}
			}
			
		}
		return result;
	}

	// Employee info
	class Employee {
	    // It's the unique id of each node;
	    // unique id of this employee
	    public int id;
	    // the importance value of this employee
	    public int importance;
	    // the id of direct subordinates
	    public List<Integer> subordinates;
	    
	    public Employee() {}
	    
	    public Employee(int id) {
	    	this.id = id;
	    }
	    
	    public Employee(int id, int importance, List<Integer> subordinates) {
	    	this.id = id;
	    	this.importance = importance;
	    	this.subordinates = subordinates;
	    }
	    
	    
	    
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getImportance() {
			return importance;
		}

		public void setImportance(int importance) {
			this.importance = importance;
		}

		public List<Integer> getSubordinates() {
			return subordinates;
		}

		public void setSubordinates(List<Integer> subordinates) {
			this.subordinates = subordinates;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + importance;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Employee other = (Employee) obj;
			if (id != other.id)
				return false;
			if (importance != other.importance)
				return false;
			return true;
		}

		@Override
	    public String toString() {
	    	return "["+ id + ", " + importance + ", " + subordinates.toString() +"]";
	    }
	}
}
