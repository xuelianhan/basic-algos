package org.ict.algorithm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorOutputStream;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

/**
 * @see https://github.com/lz4/lz4/blob/master/doc/lz4_Block_format.md
 * @see https://github.com/lz4/lz4/blob/master/doc/lz4_Frame_format.md
 * @see https://commons.apache.org/proper/commons-compress/examples.html
 * @see https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
 * @see https://www.baeldung.com/java-byte-arrays-hex-strings
 *
 */
public class LZ4Util {
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static void bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    String str = new String(hexChars);
	    System.out.println("hex:" + str);
	}
	
	public static void main(String[] args) {
		String fileFrom = "D:\\workspace\\imagebase.zip";
		String fileTo = "D:\\workspace\\imagebase.zip.lz4";
		try {
			compress(fileFrom, fileTo);
			String lzFileFrom = fileTo;
			String unlzFileTo = "D:\\workspace\\imagebase-unzip-test.zip";
			unCompress(lzFileFrom,  unlzFileTo);
		} catch (IOException e) {
			e.printStackTrace();
		}	
        byte[] data = new byte[] {'a','b','c','d',' ',' ',' ',' ',' ',' ','a','b','c','d','e','f','g','h','i','j'};
        System.out.println("raw:"+Arrays.toString(data));
        byte[] arr = null;
		try {
			arr = compress(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("compressed:" + Arrays.toString(arr));
        byte[] result = null;
		try {
			result = unCompress(arr, data.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("uncompressed:" + Arrays.toString(result));
    }
	
	public static byte[] compress(final byte[] original) throws IOException {
		LZ4Factory factory = LZ4Factory.fastestInstance();
		LZ4Compressor compressor = factory.fastCompressor();
		int maxCompressedLength = compressor.maxCompressedLength(original.length);
		byte[] compressed = new byte[maxCompressedLength];
		int compressedLength = compressor.compress(original, 0, original.length, compressed, 0, maxCompressedLength);
		return Arrays.copyOf(compressed, compressedLength);
	}
	
	public static byte[] unCompress(final byte[] compressed, int lengthOforiginal) throws IOException {
		LZ4Factory factory = LZ4Factory.fastestInstance();
		LZ4FastDecompressor decompressor = factory.fastDecompressor();
		byte[] restored = new byte[lengthOforiginal];
		decompressor.decompress(compressed, 0, restored, 0, lengthOforiginal);
		return restored;
	}
	
	public static void compress(String fileFrom, String fileTo) throws IOException {
		InputStream in = Files.newInputStream(Paths.get(fileFrom));
		OutputStream fout = Files.newOutputStream(Paths.get(fileTo));
		BufferedOutputStream out = new BufferedOutputStream(fout);
		FramedLZ4CompressorOutputStream lzOut = new FramedLZ4CompressorOutputStream(out);
		final byte[] buffer = new byte[1024];
		int n = 0;
		while (-1 != (n = in.read(buffer))) {
		    lzOut.write(buffer, 0, n);
		}
		lzOut.close();
		in.close();
	}
	
	public static void unCompress(String fileFrom, String fileTo) throws IOException {
		InputStream fin = Files.newInputStream(Paths.get(fileFrom));
		BufferedInputStream in = new BufferedInputStream(fin);
		OutputStream out = Files.newOutputStream(Paths.get(fileTo));
		FramedLZ4CompressorInputStream zIn = new FramedLZ4CompressorInputStream(in);
		final byte[] buffer = new byte[1024];
		int n = 0;
		while (-1 != (n = zIn.read(buffer))) {
		    out.write(buffer, 0, n);
		}
		out.close();
		zIn.close();
	}
}
