package org.ict.algorithm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;

public class GzipUtil {
	/**
	 * A function used to compress the data using GZIP
	 * 
	 * @param data data to be compressed
	 * @return gziped data
	 */
	public static byte[] compress(final byte[] data) throws IOException {
		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(data.length)) {
			try (GZIPOutputStream gzip = new GZIPOutputStream(byteStream)) {
				gzip.write(data);
			}
			return byteStream.toByteArray();
		}
	}

	/**
	 * Decompress the data compressed using gzip
	 *
	 * @param data data to be decompressed
	 * @return uncompressed data
	 * @throws IOException exception if can't decompress
	 */
	public static byte[] unCompress(final byte[] data) throws IOException {
		try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
				GZIPInputStream gzip = new GZIPInputStream(inputStream)) {
			return IOUtils.toByteArray(gzip);
		}
	}

	/**
	 * Determines if a byte array is compressed. The java.util.zip GZip
	 * implementaiton does not expose the GZip header so it is difficult to
	 * determine if a string is compressed.
	 *
	 * @param bytes an array of bytes
	 * @return true if the array is compressed or false otherwise
	 * @throws java.io.IOException if the byte array couldn't be read
	 */
	public static boolean isGZIPStream(byte[] bytes) {
		if ((bytes == null) || (bytes.length < 2)) {
			return false;
		}
		return (bytes[0] == (byte) GZIPInputStream.GZIP_MAGIC)
				&& (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >>> 8));
	}
}
