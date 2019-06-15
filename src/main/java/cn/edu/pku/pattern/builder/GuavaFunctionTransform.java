package cn.edu.pku.pattern.builder;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @see https://www.baeldung.com/guava-functions-predicates
 * @author hanxuelian001
 *
 */
public class GuavaFunctionTransform {

	public static void main(String[] args) {
		//test1();
		test2();
	}
	
	/**
	 * filter a collection by a condition (custom Predicate)
	 */
	public static void test1() {
		List<Integer> numbers = Lists.newArrayList(1, 2, 3, 6, 10, 34, 57, 89);
		Predicate<Integer> acceptEven = new Predicate<Integer>() {
		    @Override
		    public boolean apply(Integer number) {
		        return (number % 2) == 0;
		    }
		};
		List<Integer> evenNumbers = Lists.newArrayList(Collections2.filter(numbers, acceptEven));
		System.out.println(evenNumbers);
		Integer found = Collections.binarySearch(evenNumbers, 57);
		System.out.println(found);
	}
	
	/**
	 * filter out nulls from a collection
	 */
	public static void test2() {
		List<String> withNulls = Lists.newArrayList("a", "bc", null, "def");
		Iterable<String> withoutNuls = Iterables.filter(withNulls, Predicates.notNull());
		System.out.println(withoutNuls);
		assertTrue(Iterables.all(withoutNuls, Predicates.notNull()));
		
	}
	
	/**
	 * check condition for all elements of a collection
	 */
	public static void test3() {
		List<Integer> evenNumbers = Lists.newArrayList(2, 6, 8, 10, 34, 90);
		Predicate<Integer> acceptEven = new Predicate<Integer>() {
		    @Override
		    public boolean apply(Integer number) {
		        return (number % 2) == 0;
		    }
		};
		System.out.println(evenNumbers);
		assertTrue(Iterables.all(evenNumbers, acceptEven));
	}
	
	/**
	 * negate a predicate
	 */
	public static void test4() {
		List<Integer> evenNumbers = Lists.newArrayList(2, 6, 8, 10, 34, 90);
		Predicate<Integer> acceptOdd = new Predicate<Integer>() {
		    @Override
		    public boolean apply(Integer number) {
		        return (number % 2) != 0;
		    }
		};
		assertTrue(Iterables.all(evenNumbers, Predicates.not(acceptOdd)));
	}
	
	/**
	 * apply a simple function
	 */
	public static void test5() {
		List<Integer> numbers = Lists.newArrayList(1, 2, 3);
		List<String> asStrings = Lists.transform(numbers, Functions.toStringFunction());
		System.out.println(asStrings);
	}
}
