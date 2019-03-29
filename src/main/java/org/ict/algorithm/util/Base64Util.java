package org.ict.algorithm.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.StringUtils;

public class Base64Util {
	
	public static void testJava8Base64() {
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "Java核心";
		try {
			final byte[] textByte = text.getBytes("UTF-8");
			final String encodedText = encoder.encodeToString(textByte);
			System.out.println("encodedText:" + encodedText);
			String decodedText = new String(decoder.decode(encodedText), "UTF-8");
			System.out.println("decodedText:" + decodedText);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String decodeWithDC(String s) {
		byte[] b = DatatypeConverter.parseBase64Binary(s);
		String result = DatatypeConverter.printBase64Binary(b);
		return result;
	}

	public static String decodeWithAcc(String s) {
		return StringUtils.newStringUtf8(org.apache.commons.codec.binary.Base64.decodeBase64(s));
	}

	public static String encodeWithAcc(String s) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
	}

	public static void main(String[] args) {
		String s = "eHVlbGlhbi5oYW46ZGFuZ2xlMTIz";
		String r1 = decodeWithDC(s);
		String r2 = decodeWithAcc(s);
		StdOut.println(r1);
		StdOut.println(r2);
		testJava8Base64();
	}
}
