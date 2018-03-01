package org.ict.algorithm.fundamentals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * file input content:
 * 
 * ahoaeoieow  urwiri3w2qqoi2qru b  if aaggg
 * euiiru3wuri keyword keywordkeyword wordkey key
 * wordkey
 * word
 * 
 * hahahhahah 
 *
 * expect result: 5
 * actual result: 5
 * 
 * Solutions:
 * Use regex match and Remove line breaks and spaces before match the keyword.
 * This way is recommended.
 */
public class KeyWordsCountDemo {
    
    private static Pattern time_pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}");

    private static String replaceBackRegex = "\\d{4}-\\d{2}-\\d{2}\\d{2}:\\d{2}:\\d{2}";

    /**
     * @see https://stackoverflow.com/questions/3903302/regex-matching-with-line-breaks
     * @see http://www.tutorialspoint.com/java/java_regular_expressions.htm
     * @see https://stackoverflow.com/questions/13979317/how-to-count-the-number-of-occurrences-of-words-in-a-text
     * @param path
     * @param keyWord
     * @return
     */
    public static int countKeyWord(String path, String keyWord) {
        int count = 0;
        File f = new File(path);
        if (!f.exists()) {
            return count;
        }
       
        List<String> matches = new LinkedList<String>();
        InputStream is = null;
        try {
            //read file into a string
            is = new FileInputStream(f);
            byte[] data = new byte[is.available()];
            is.read(data);
            String text = new String(data);
            
            Pattern p = Pattern.compile(keyWord);
            //remove all whitespace(including line). Equivalent to [\t\n\r\f].
            Matcher m = p.matcher(text.replaceAll("\\s+", ""));
            while (m.find()) {
                matches.add(m.group());
                count++;
            }
            System.out.println(matches);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        String input =  "/home/hanxuelian/Desktop/keyword.log";
        String keyWord = "keyword";
        int count = countKeyWord(input, keyWord);
        System.out.println(count);
        
        String content = "2018-02-24     17:58:00 2018-02-2418:00:00 2018-02-24 19:00:00";
        String result = testReplaceBack(content);
        System.out.println(result);
    }
    
    public static String testReplaceBack(String content) {
        List<String> replaceList = matchRegex(content, time_pattern);
        String result = replaceBack(content, replaceBackRegex, replaceList);
        return result;
    }
    
    private static List<String> matchRegex(String input,  Pattern pattern) {
        List<String> matches = new LinkedList<String>();
        if (StringUtils.isBlank(input)) {
            return matches;
        }
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }
    
    private static String replaceBack(String input, String regex, List<String> replaceList) {
        if (CollectionUtils.isEmpty(replaceList)) {
            return input;
        }
        Iterator<String> iter = replaceList.iterator();
        String result = input;
        while (iter.hasNext()) {
            result = result.replaceFirst(regex, iter.next());
        }
        return result;
    }
}
