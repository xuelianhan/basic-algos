package org.ict.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://stackoverflow.com/questions/33552639/parsing-interval-notation-to-guava-range
 * @author hanxuelian001
 * ^[\\(|\\[](\\d+),(\\d+)[\\)|\\]]$
 */
public class RegexUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String u = "%24%7B%7D";
        String d = URLDecoder.decode(u, Charset.forName("utf-8").name());
        System.out.println("decode result:" + d);
        String str = "${a},${b},${c}";
        Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}|%24%7B(\\w+)%7D");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            int cnt = matcher.groupCount();
            System.out.println("group count:" + cnt);
            /**
             * Group zero denotes the entire pattern by convention. It is not included in this count
             */
            String x = matcher.group(0);
            /**
             * Any non-negative integer smaller than or equal to the value returned by this method
             * is guaranteed to be a valid group index for this matcher
             */
            String y = matcher.group(1);
            String z = matcher.group(2);
            System.out.println("x:" + x + ", y:" + y + ", z:" + z);
        }
    }
}
