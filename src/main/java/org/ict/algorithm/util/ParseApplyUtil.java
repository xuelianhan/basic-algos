package org.ict.algorithm.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * @see https://stackoverflow.com/questions/36368235/java-get-valueof-for-generic-subclass-of-java-lang-number-or-primitive
 * @see https://ideone.com/WtNDN2
 */
public class ParseApplyUtil {
    
    private static HashMap<Class<?>, Function<String,?>> parser = new HashMap<>();
    
    static {
        parser.put(boolean.class       , Boolean::parseBoolean);
        parser.put(byte.class          , Byte::parseByte);
        parser.put(short.class         , Short::parseShort);
        parser.put(int.class           , Integer::parseInt);
        parser.put(long.class          , Long::parseLong);
        parser.put(double.class        , Double::parseDouble);
        parser.put(float.class         , Float::parseFloat);
        parser.put(Boolean.class       , Boolean::valueOf);
        parser.put(Byte.class          , Byte::valueOf);
        parser.put(Short.class         , Short::valueOf);
        parser.put(Integer.class       , Integer::valueOf);
        parser.put(Long.class          , Long::valueOf);
        parser.put(Double.class        , Double::valueOf);
        parser.put(Float.class         , Float::valueOf);
        parser.put(String.class        , String::valueOf);
        parser.put(BigDecimal.class    , BigDecimal::new);
        parser.put(BigInteger.class    , BigInteger::new);
        parser.put(LocalDate.class     , LocalDate::parse);
        parser.put(LocalDateTime.class , LocalDateTime::parse);
        parser.put(LocalTime.class     , LocalTime::parse);
        parser.put(MonthDay.class      , MonthDay::parse);
        parser.put(OffsetDateTime.class, OffsetDateTime::parse);
        parser.put(OffsetTime.class    , OffsetTime::parse);
        parser.put(Year.class          , Year::parse);
        parser.put(YearMonth.class     , YearMonth::parse);
        parser.put(ZonedDateTime.class , ZonedDateTime::parse);
        parser.put(ZoneId.class        , ZoneId::of);
        parser.put(ZoneOffset.class    , ZoneOffset::of);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object parse(String argString, Class param) {
        Function<String,?> func = parser.get(param);
        if (func != null)
            return func.apply(argString);
        if (param.isEnum())
            return Enum.valueOf(param, argString);
        throw new UnsupportedOperationException("Cannot parse string to " + param.getName());
    }
    
    public static List<Object> mapList(List<String> input, Class<?> param) {
        List<Object> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(input)) {
            result = input
                    .stream()
                    .map(s -> parse(s, param))
                    .collect(Collectors.toList());
        }
        return result;
    }
    
    private static void test(String argString, Class<?> param) {
        Object ret = parse(argString, param);
        System.out.printf("%-21s -> %-19s   [%-22s -> %s]%n",
                          '"' + argString + '"', ret, param.getName(), ret.getClass().getName());
    }
    
    public static void main(String[] args) {
        List<String> input = Lists.newArrayList();
        input.add("123");
        List<Integer> result = (List<Integer>)((List<?>)mapList(input, Integer.class));
        System.out.println("input:" + input +  ", result:" + result);
        test("true", boolean.class);
        test("123" , byte.class);
        test("123" , short.class);
        test("123" , int.class);
        test("123" , long.class);
        test("123" , double.class);
        test("123" , float.class);
        test("true", Boolean.class);
        test("123" , Byte.class);
        test("123" , Short.class);
        test("123" , Integer.class);
        test("123" , Long.class);
        test("123" , Double.class);
        test("123" , Float.class);
        test("123" , BigDecimal.class);
        test("123" , BigInteger.class);
        test("Hello World", String.class);       // String
        test("HALF_EVEN"  , RoundingMode.class); // enum
        test("2016"                                       , Year.class);
        test("2016-04"                                    , YearMonth.class);
        test("--04-01"                                    , MonthDay.class);
        test("2016-04-01"                                 , LocalDate.class);
        test("23:18:47"                                   , LocalTime.class);
        test("23:18:47-04:00"                             , OffsetTime.class);
        test("2016-04-01T23:18:47"                        , LocalDateTime.class);
        test("2016-04-01T23:18:47-04:00"                  , OffsetDateTime.class);
        test("2016-04-01T23:18:47-04:00[America/New_York]", ZonedDateTime.class);
        test("America/New_York"                           , ZoneId.class);
        test("-04:00"                                     , ZoneOffset.class);
        test("FRIDAY"                                     , DayOfWeek.class);     // enum
        test("APRIL"                                      , Month.class);         // enum
    }
 
}
