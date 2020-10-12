package org.ict.algorithm.util;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformUtil {

    /**
     * @see <a href="https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent"></a>
     * @see <a href="https://www.techiedelight.com/convert-set-string-set-integer-java-8/"></a>
     */
    public static <T, U> Set<U> transform(Set<T> set, Function<T, U> function) {
        return set.stream().map(function).collect(Collectors.toSet());
    }
}
