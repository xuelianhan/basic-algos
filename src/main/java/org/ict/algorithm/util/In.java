package org.ict.algorithm.util;

import java.util.Scanner;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URL;
import java.util.Locale;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.File;

public final class In {

    private static final String CHARSET_NAME = "UTF-8";

    private static final Locale LOCALE = Locale.US;

    private Scanner scanner;

    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
        scanner.useLocale(LOCALE);
    }

    public In(Socket socket) {
        if (socket == null) {
            throw new NullPointerException("socket is null!");
        }
        try {
            InputStream is = socket.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE); 
        } catch(IOException e) {
            throw new IllegalArgumentException("Could not open " + socket);
        }
    }

    public In(URL url) {
        if (url == null) {
            throw new NullPointerException("url is null!");
        }
        try {
            URLConnection urlConn = url.openConnection();
            InputStream is = urlConn.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE); 
        } catch(IOException e) {
            throw new IllegalArgumentException("Could not open " + url); 
        }
    }

    public In(File file) {
        if (file == null) {
            throw new NullPointerException("file is null!");
        }
        try {
            scanner = new Scanner(file, CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not open " + file);
        }
    }

}
