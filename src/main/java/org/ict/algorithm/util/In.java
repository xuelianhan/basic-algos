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
import java.io.FileInputStream;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;

public final class In {

    private static final String CHARSET_NAME = "UTF-8";

    private static final Locale LOCALE = Locale.US;

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    private Scanner scanner;

    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
        scanner.useLocale(LOCALE);
    }

    /**
     * Initializes an input stream from a socket.
     *
     *
     */
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

    /**
     * Initializes an input stream from a URL.
     *
     */
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

    /**
     * Initializes an input stream from a file.
     *
     */
    public In(File file) {
        if (file == null) {
            throw new NullPointerException("file is null!");
        }
        try {
            //scanner = new Scanner(file, CHARSET_NAME);
            // for consistency with StdIn, wrap with BufferedInputStream instead of use
            // file as argument to Scanner
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not open " + file);
        }
    }

    /**
     * Initializes an input stream from a filename or web page name.
     *
     * @param name the filename of webpage name
     * @throws IllegalArgumentException if cannot open{@code name} as a file or URL.
     * @throws NullPointerException if {@code name} is {@code null}
     */
    public In(String name) {
        if (name == null) throw new NullPointerException("argument is null");
        try {
            //first try to read file from local file system.
            File file = new File(name);
            if (file.exists()) {
                // for consistency with StdIn, wrap with BufferedInputStream 
                // instead of use file as argument to Scanner.
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
                scanner.useLocale(LOCALE);
                return;
            }

            //next try for files included in jar.
            URL url = getClass().getResource(name);

            //or URL from web.
            if (url == null) {
                url = new URL(name);
            }

            URLConnection site = url.openConnection();

            //in order to set User-Agent, replace above line with these two
            //HttpURLConnection site = (HttpURLConnection)url.openConnection();
            //site.addRequestProperty("User-Agent", "MOzilla/4.76");

            InputStream is = site.getInputStream();
            scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + name);
        }
    }

    /**
     * Initializes an input stream from a given {@link Scanner} source;
     * use  with {@code new Scanner(String)} to read from a string.
     * <p>
     * Note that this does not create a defensive copy, so the scanner
     * will be mutated as you read on.
     *
     * @param Scanner the scanner
     * @throws NullPointerException if {@code scanner} is {@code null}
     */
    public In(Scanner scanner) {
        if (scanner == null) {
            throw new NullPointerException("argument is null");
        }
        this.scanner = scanner;
    }

    /**
     * Returns true if this input stream exists.
     *
     * @return {@code true} if this input stream exists;{@code false} otherwise
     */
    public boolean exists() {
        return scanner != null;
    }

    public boolean isEmpty() {
        return !scanner.hasNext();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }

    /**
     * Reads and returns the next character in this input stream.
     *
     * @return the next character in this input stream
     */
    public char readChar() {
       scanner.useDelimiter(EMPTY_PATTERN);
       String ch = scanner.next();
       if (ch.length() != 1) {
    	   throw new IllegalArgumentException("read Char() error!");
       }
       scanner.useDelimiter(WHITESPACE_PATTERN);
       return ch.charAt(0);
    }

    /**
     * Reads and returns the remainder of this input stream, as a string.
     *
     * @return the remainder of this input stream, as a string
     *
     */
    public String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        //not that important to reset delimeter, since now scanner is empty
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    /**
     * Reads the next token from this input stream and return it as a {@code String}.
     *
     * @return the next {@code String} in this input stream
     */
    public String readString() {
        return scanner.next();
    }

    /**
     * Reads the next token from this input stream, parses it as a {@code int},
     * and returns the {@code int}.
     *
     * @return the next {@code int} in this input stream
     */
    public String readInt() {
        return scanner.nextInt();
    }

    public double readDouble() {
        return scanner.nextInt();
    }
    
    public float readFloat() {
        return scanner.nextFloat();
    }

    public long readLong() {
        return scanner.nextLong();
    }

    public short readShort() {
        return scanner.nextShort();
    }

    public byte readByte() {
        return scanner.nextByte();
    }

    public boolean readBoolean() {
        String s = readString();

    }
}
