package org.ict.algorithm.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author sniper
 * @date 2021/11/22 10:29 AM
 */
public class BigDecimalUtil {
    public static BigDecimal average(List<BigDecimal> numbers) {
        return IntStream.range(0, numbers.size()).mapToObj(i -> numbers.get(i)).reduce((sum, next) -> sum.add(next))
                .map(sum -> sum.divide(BigDecimal.valueOf(numbers.size()), 2, RoundingMode.HALF_UP)).orElse(BigDecimal.ZERO);
    }
    public static BigDecimal average(final BigDecimal... numbers) {
        return IntStream.range(0, numbers.length).mapToObj(i -> numbers[i]).reduce((sum, next) -> sum.add(next))
                .map(sum -> sum.divide(BigDecimal.valueOf(numbers.length),2, RoundingMode.HALF_UP)).orElse(BigDecimal.ZERO);
    }

    public static BigDecimal divide(final BigDecimal amount, final BigDecimal divideBy, final int scale) {
        return amount.setScale(scale, RoundingMode.HALF_EVEN).divide(divideBy, 2, RoundingMode.HALF_EVEN);
    }
}
