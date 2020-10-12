package org.ict.algorithm.util;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformUtil {

    /**
     * @see <a href="https://www.techiedelight.com/convert-set-string-set-integer-java-8/"></a>
     */
    public static <T, U> Set<U> transform(Set<T> set, Function<T, U> function) {
        return set.stream().map(function).collect(Collectors.toSet());
    }
}
