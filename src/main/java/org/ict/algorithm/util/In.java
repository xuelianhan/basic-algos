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
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

/**
 * <i>Input</i>. This class provides methods for reading strings
 * and numbers from standard input, file input, URLs, and sockets.
 * <p>
 * The Locale used is:language = English, country = US. This is 
 * consistent with the formatting conventions with Java floating-point
 * lierals, command-line arguments (via {@link Double#parseDouble(String)})
 * and standard output.
 *
 * <p>
 * Like {@link Scanner}, reading a token also consumes preceding Java
 * whitespace, reading a full line consumes the following end-of-line
 * delimeter, while reading a character consumes nothing extra.
 * <p>
 * Whitespace is defined in {@link Character#isWhitespace(char)}.Newlines
 * consist of \n, \r, \r\n, and unicode hex code points 0x2028, 0x2029, 0x0085;
 * (NB: Java 6u23 and earlier uses only \r, \r, \r\n).
 *
 * @author David Pritchard
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public final class In {

    //// begin: section(1 of 2) of code duplicated from In to StdIn.

    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with System.out.
    private static final Locale LOCALE = Locale.US;

    // the default token separator; we maintain the invariant that this value 
    // is held by the scanner's delimiter between calls
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    // makes whitespace characters significant
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");

    // used to read the entire input. 
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    //// end: section(1 of 2) of code duplicated from In to StdIn.

    private Scanner scanner;

    /**
     * Initializes an input stream from standard input.
     */
    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
        scanner.useLocale(LOCALE);
    }

    /**
     * Initializes an input stream from a socket.
     * 
     * @param socket the socket
     * @throws IllegalArgumentException if cannot open {@code socket}
     * @throws IllegalArgumentException if {@code socket} is {@code null}
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
            throw new IllegalArgumentException("Could not open " + socket, e);
        }
    }

    /**
     * Initializes an input stream from a URL.
     * 
     * @param url the URL
     * @throws IllegalArgumentException if cannot open {@code url}
     * @throws IllegalArgumentException if {@code url} is {@code null}
     */
    public In(URL url) {
        if (url == null) {
            throw new NullPointerException("url is null!");
        }
        try {
            URLConnection urlConn = url.openConnection();
            InputStream is        = urlConn.getInputStream();
            scanner               = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE); 
        } catch(IOException e) {
            throw new IllegalArgumentException("Could not open " + url, e); 
        }
    }

    /**
     * Initializes an input stream from a file.
     * @param file the file
     * @throws IllegalArgumentException if cannot open {@code file}
     * @throws IllegalArgumentException if {@code file} is {@code null}
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
            throw new IllegalArgumentException("Could not open " + file, e);
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
            throw new IllegalArgumentException("Could not open " + name, ioe);
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

    /**
     * Returns true if input stream is empty (Except possibly whitespace).
     * Use this to know whether the next call to {@link #readString()},
     * {@link #readDouble()}, etc will succeed.
     *
     * @return {@code true} if this input stream is empty (except possibly whitespace);
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return !scanner.hasNext();
    }

    /**
     * Returns true if this input stream has a next line.
     *
     * Use this method to know whether the next call to
     * {@link #readLine()} will succeed.
     * This method is functionally equivalent to {@link #hasNextChar()}.
     *
     * @return {@code true} if this input stream has more input (including whitespace);
     *         {@code false} otherwise
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Returns true if this input stream has more input (including whitespace).
     *
     * Use this method to know whether the next call to
     * {@link #readChar()} will succeed.
     * This method is functionally equivalent to {@link #hasNextLine()}.
     *
     * @return {@code true} if this input stream has more input (including whitespace);
     *         {@code false} otherwise
     */
    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    /**
     * Reads and returns the next line in this input stream.
     *
     * @return the next line in this input stream;{@code null} if no such line
     */
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
       try {
           String ch = scanner.next();
           if (ch.length() != 1) {
               throw new IllegalArgumentException("read Char() error!");
           }
           scanner.useDelimiter(WHITESPACE_PATTERN);
           return ch.charAt(0);
       } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'char' value from the input stream," 
                    + "but no more tokens are avialable");
       }
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
    public int readInt() {
        return scanner.nextInt();
    }

    public double readDouble() {
        return scanner.nextDouble();
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
        if ("true".equalsIgnoreCase(s)) {
            return true;
        }
        if ("flalse".equalsIgnoreCase(s)) {
            return false;
        }
        if ("1".equals(s)) {
            return true;
        }
        if ("0".equals(s)) {
            return false;
        }
        throw new InputMismatchException();
    }
    
    /**
     * Reads all remaining tokens from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining tokens in this input stream, as an array of strings.
     */
    public String[] readAllStrings() {
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }
        String[] decapitokens = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++) {
            decapitokens[i] = tokens[i+1];
        }
        return decapitokens;
    }

    /**
     * Reads all remaining lines from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining lines in this input stream, as an array of strings
     */
    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[lines.size()]);
    }

    /**
     * Reads all remaining tokens from this input stream, parses them as integers,
     * and returns them as an array of integers.
     *
     * @return all remaining lines in this input stream, as an array of integers
     */
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Integer.parseInt(fields[i]);
        }
        return vals;
    }

    /**
     * Reads all remaining tokens from this input stream, parses them as longs,
     * and returns them as an array of longs.
     *
     * @return all remaining lines in this input stream, as an array of longs
     */
    public long[] readAllLongs() {
        String[] fields = readAllStrings();
        long[] vals = new long[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Long.parseLong(fields[i]);
        }
        return vals;
    }

    /**
     * Reads all remaining tokens from this input stream, parses them as doubles,
     * and returns them as an array of doubles.
     *
     * @return all remaining lines in this input stream, as an array of doubles
     */
    public double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Double.parseDouble(fields[i]);
        }
        return vals;
    }


    /**
     * Closes this input stream.
     */
    public void close() {
        scanner.close();
    }

}
