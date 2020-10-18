package org.ict.algorithm.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/***
 * 
 * @see http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
 *
 */
public class MapUtil {

	/**
	 * Returns a map where each entry is an item of {@code list} mapped by the
	 * key produced by applying {@code mapper} to the item.
	 *
	 * @param list the list to map
	 * @param mapper the function to produce the key from a list item
	 * @return the resulting map
	 * @throws IllegalStateException on duplicate key
	 */
	public static <K, T> Map<K, T> toMapBy(List<T> list,
										   Function<? super T, ? extends K> mapper) {
		return list.stream().collect(Collectors.toMap(mapper, Function.identity()));
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
            }
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescendOrder(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
            }
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
