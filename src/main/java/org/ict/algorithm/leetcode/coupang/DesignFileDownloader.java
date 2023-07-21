package org.ict.algorithm.leetcode.coupang;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @see <a href="https://www.baeldung.com/java-download-file"></a>
 * @author sniper
 * @date 21 Jul 2023
 */
public class DesignFileDownloader {




    /**
     * Using Java NIO
     * The Java NIO package offers the possibility to transfer bytes between two Channels
     * without buffering them into the application memory.
     * The transferTo() and transferFrom() methods are more efficient than simply reading from a stream using a buffer.
     * Depending on the underlying operating system,
     * the data can be transferred directly from the filesystem cache to our file
     * without copying any bytes into the application memory.
     *
     * On Linux and UNIX systems,
     * these methods use the zero-copy technique that reduces the number of context
     * switches between the kernel mode and user mode.
     */
    public void download2(String fileUrl, String fileName) {
        try {
            ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(fileUrl).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Using Java IO
     * Its main drawback is the fact that the bytes are buffered into memory.
     * Fortunately,
     * Java offers us the NIO package that has methods to transfer bytes directly between two Channels without buffering.
     * @param fileUrl
     * @param fileName
     */
    public void downloadV1(String fileUrl, String fileName) {
        try {
            InputStream in = new URL(fileUrl).openStream();
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Using Java IO to download file content from fileUrl, and
     * write the content into the file with fileName.
     * @param fileUrl
     * @param fileName
     */
    public void download(String fileUrl, String fileName) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }
    }
}
