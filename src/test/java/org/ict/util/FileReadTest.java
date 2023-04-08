package org.ict.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * @see <a href="https://mkyong.com/java8/java-8-stream-read-a-file-line-by-line/"></a>
 * @see <a href="https://howtodoinjava.com/java8/read-file-line-by-line//"></a>
 */
public class FileReadTest {

    public static void main(String args[]) {
        FileReadTest instance = new FileReadTest();
        instance.testReadByBuf();
    }

    public void testReadByBuf() {
        try {
            File f = new File("/Users/randyhubbard/Desktop/leetcode.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";
            Map<String, Integer> map = new HashMap<>();
            while ((line = b.readLine()) != null) {
                //String[] arr = line.split("\\s+");
                String[] arr = line.split("Easy|Medium|Hard");
                if (arr == null) {
                    continue;
                }
                String[] freqNameArr = arr[0].split("\\s+", 2   );
                if (freqNameArr.length == 0) {
                    continue;
                }
                if (freqNameArr.length == 2) {
                    System.out.println(freqNameArr[0] + ", " + freqNameArr[1]);
                    String key = freqNameArr[1].trim();
                    map.put(key, map.getOrDefault(key, 0) + Integer.valueOf(freqNameArr[0]));
                }
            }
            map.forEach((k, v) -> {
                System.out.println(k +", " + v);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testReadByStream() {
        String fileName = "/Users/randyhubbard/Desktop/leetcode.txt";
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
