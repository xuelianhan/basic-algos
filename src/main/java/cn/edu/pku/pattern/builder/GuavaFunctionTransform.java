package cn.edu.pku.pattern.builder;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.*;

import java.util.*;


/**
 * @see https://www.baeldung.com/guava-functions-predicates
 * @see https://github.com/google/guava/wiki/FunctionalExplained
 * @author sniper
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
		boolean flag = (Iterables.all(withoutNuls, Predicates.notNull()));
		
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
	}
	
	/**
	 * apply a simple function
	 */
	public static void test5() {
		List<Integer> numbers = Lists.newArrayList(1, 2, 3);
		List<String> asStrings = Lists.transform(numbers, Functions.toStringFunction());
		System.out.println(asStrings);
	}
	
	/**
	 * sort collection by first applying an intermediary function
	 */
	public static void test6() {
		List<Integer> numbers = Arrays.asList(2, 1, 11, 100, 8, 14);
		Ordering<Object> ordering = Ordering.natural().onResultOf(Functions.toStringFunction());
		List<Integer> inAlphabeticalOrder = ordering.sortedCopy(numbers);
		List<Integer> correctAlphabeticalOrder = Lists.newArrayList(1, 100, 11, 14, 2, 8);
		System.out.println(inAlphabeticalOrder);
		System.out.println(correctAlphabeticalOrder);
	}
	
	/**
	 * complex example – chaining predicates and functions
	 */
	public static void test7() {
		List<Integer> numbers = Arrays.asList(2, 1, 11, 100, 8, 14);
		Predicate<Integer> acceptEvenNumber = new Predicate<Integer>() {
		    @Override
		    public boolean apply(Integer number) {
		        return (number % 2) == 0;
		    }
		};
		Function<Integer, Integer> powerOfTwo = new Function<Integer, Integer>() {
		    @Override
		    public Integer apply(Integer input) {
		        return (int) Math.pow(input, 2);
		    }
		};
		 
		FluentIterable<Integer> powerOfTwoOnlyForEvenNumbers = 
		FluentIterable.from(numbers).filter(acceptEvenNumber).transform(powerOfTwo);
		System.out.println(powerOfTwoOnlyForEvenNumbers);
	}
	
	/**
	 * compose two functions
	 */
	public static void test8() {
		List<Integer> numbers = Arrays.asList(2, 3);
		Function<Integer, Integer> powerOfTwo = new Function<Integer, Integer>() {
		    @Override
		    public Integer apply(Integer input) {
		        return (int) Math.pow(input, 2);
		    }
		};
		List<Integer> result = Lists.transform(numbers, Functions.compose(powerOfTwo, powerOfTwo));
		System.out.println(result);
	}
	
	/**
	 * create a Map backed by a Set and a Function
	 */
	public static void test9() {
		Function<Integer, Integer> powerOfTwo = new Function<Integer, Integer>() {
		    @Override
		    public Integer apply(Integer input) {
		        return (int) Math.pow(input, 2);
		    }
		};
		Set<Integer> lowNumbers = Sets.newHashSet(2, 3, 4);
		 
		Map<Integer, Integer> numberToPowerOfTwoMuttable = Maps.asMap(lowNumbers, powerOfTwo);
		Map<Integer, Integer> numberToPowerOfTwoImuttable = Maps.toMap(lowNumbers, powerOfTwo);
		System.out.println(numberToPowerOfTwoMuttable);
		System.out.println(numberToPowerOfTwoImuttable);
	}
	
	
	/**
	 * create a Function out of a Predicate
	 */
	public static void test10() {
		List<Integer> numbers = Lists.newArrayList(1, 2, 3, 6);
		Predicate<Integer> acceptEvenNumber = new Predicate<Integer>() {
		    @Override
		    public boolean apply(Integer number) {
		        return (number % 2) == 0;
		    }
		};
		Function<Integer, Boolean> isEventNumberFunction = Functions.forPredicate(acceptEvenNumber);
		List<Boolean> areNumbersEven = Lists.transform(numbers, isEventNumberFunction);
		System.out.println(areNumbersEven);
	}
}
