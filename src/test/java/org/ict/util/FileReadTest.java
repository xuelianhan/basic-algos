package org.ict.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @see <a href="https://mkyong.com/java8/java-8-stream-read-a-file-line-by-line/"></a>
 */
public class FileReadTest {

    public static void main(String args[]) {

        String fileName = "c://lines.txt";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
