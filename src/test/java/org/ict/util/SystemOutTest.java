package org.ict.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SystemOutTest {

    public void test() throws Exception{
        File file = new File("xxxx/stdout.log");
        if (!file.exists()) {
            file.createNewFile();
        }
        System.setOut(new PrintStream(new FileOutputStream(file)));
        System.out.println("hello world");
    }
}
