package org.ict.algorithm.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @see https://www.techiedelight.com/convert-set-string-set-integer-java-8/
 * @see https://www.baeldung.com/convert-array-to-set-and-set-to-array
 * @see https://stackoverflow.com/questions/3064423/how-to-convert-an-array-to-a-set-in-java
 * @see https://codereview.stackexchange.com/questions/38145/parse-strings-and-convert-the-value-to-java-types
 */
public class SetUtil {

	public static <T, U> Set<U> transform(Set<T> set, Function<T, U> function) {
		return set.stream()
				  .map(function)
				  .collect(Collectors.toSet());
	}
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<>(Arrays.asList("-1", "2", "-3", "4"));
		Set<Integer> ints = transform(set, Integer::parseInt);
		System.out.println(ints);
	}
}
