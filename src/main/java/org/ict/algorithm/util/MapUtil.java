package org.ict.algorithm.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/***
 * @see <a href="http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java"></a>
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
	 * @see <a href="https://stackoverflow.com/questions/4138364/java-how-to-convert-list-to-map"></a>
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


	/**
	 * @see <a href="https://stackoverflow.com/questions/40772997/how-to-convert-listv-into-mapk-listv-with-java-8-streams-and-custom-list"></a>
	 * @param list
	 * @param classifier
	 * @param mapSupplier
	 * @param collectionSupplier
	 * @param <K>
	 * @param <V>
	 * @param <C>
	 * @param <M>
	 * @return
	 */
	public static <K, V, C extends Collection<V>, M extends Map<K, C>> M getMap(List<V> list, Function<? super V, ? extends K> classifier, Supplier<M> mapSupplier, Supplier<C> collectionSupplier) {
		return list.stream().collect(
				Collectors.groupingBy(classifier, mapSupplier, Collectors.toCollection(collectionSupplier))
		);
	}
}
