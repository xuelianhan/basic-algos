package org.ict.algorithm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
 * @see https://github.com/lz4/lz4/issues/276
 *
 */
public class LZ4Util {
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	private static final int[] LZ4_FRAME_FORMAT_MAGIC_NUMBER = {0x04, 0x22, 0x4d, 0x18};
	
	public static boolean isLZ4Stream(byte[] bytes) {
		if ((bytes == null) || (bytes.length < 4)) {
             return false;
        }
        return ((bytes[0] == (byte) LZ4_FRAME_FORMAT_MAGIC_NUMBER[0]) && 
        	    (bytes[1] == (byte) LZ4_FRAME_FORMAT_MAGIC_NUMBER[1]) &&
        	    (bytes[2] == (byte) LZ4_FRAME_FORMAT_MAGIC_NUMBER[2]) &&
        	    (bytes[3] == (byte) LZ4_FRAME_FORMAT_MAGIC_NUMBER[3])
        	   );
	}
	
	
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
	
	public static byte[] compressWithFrameFormat(byte[] original) throws IOException {
		InputStream in = new ByteArrayInputStream(original);
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		FramedLZ4CompressorOutputStream lzOut = new FramedLZ4CompressorOutputStream(byteOutputStream);
		final byte[] buffer = new byte[1024];
		int n = 0;
		while (-1 != (n = in.read(buffer))) {
		    lzOut.write(buffer, 0, n);
		}
		lzOut.flush();
		//lzOut should be closed before byteOutputStream to invoke toByteArray method
		//Otherwise the compressed data may be not integrated.
		lzOut.close();
		byteOutputStream.flush();
		byte[] result = byteOutputStream.toByteArray();
		bytesToHex(result);
		
		byteOutputStream.close();
		in.close();
		return result;
	}
	/**
	 * Decompress data with LZ4 frame format
	 * @param compressed
	 * @return
	 * @throws IOException
	 */
	public static byte[] unCompressWithFrameFormat(byte[] compressed) throws IOException {
		InputStream in = new ByteArrayInputStream(compressed);
		FramedLZ4CompressorInputStream zIn = new FramedLZ4CompressorInputStream(in);
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		final byte[] buffer = new byte[1024];
		int n = 0;
		while (-1 != (n = zIn.read(buffer))) {
		    byteOutputStream.write(buffer, 0, n);
		}
		byte[] result = byteOutputStream.toByteArray();
		byteOutputStream.close();
		zIn.close();
		return result;
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
        
        byte[] original = new byte[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};	
        System.out.println("raw:"+Arrays.toString(original));
        System.out.println("raw length:" + original.length);
        byte[] compressed = null;
		try {
			compressed = compressWithFrameFormat(original);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("compressed:" + Arrays.toString(compressed));
		System.out.println("compressed length:" + compressed.length);
        byte[] uncompressed = null;
		try {
			uncompressed = unCompressWithFrameFormat(compressed);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        System.out.println("uncompressed:" + Arrays.toString(uncompressed));
        System.out.println("uncompressed length:" + uncompressed.length);
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
