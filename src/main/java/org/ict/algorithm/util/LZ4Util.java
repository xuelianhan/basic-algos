package org.ict.algorithm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorOutputStream;

/**
 * @see https://github.com/lz4/lz4/blob/master/doc/lz4_Block_format.md
 * @see https://github.com/lz4/lz4/blob/master/doc/lz4_Frame_format.md
 * @see https://commons.apache.org/proper/commons-compress/examples.html
 *
 */
public class LZ4Util {
	
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

	public static void main(String[] args) {
		String fileFrom = "D:\\workspace\\imagebase.zip";
		String fileTo = "D:\\workspace\\imagebase.zip.lz4";
		try {
			//compress(fileFrom, fileTo);
			String lzFileFrom = fileTo;
			String unlzFileTo = "D:\\workspace\\imagebase-unzip-test.zip";
			unCompress(lzFileFrom,  unlzFileTo);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		/*
		InputStream originalInput;
		try {
			originalInput = new FileInputStream(new File("D:\\workspace\\basic-algos"));
			CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(originalInput);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CompressorException e) {
			e.printStackTrace();
		}*/
		
	}
}
