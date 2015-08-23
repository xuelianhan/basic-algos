package org.ict.algorithm.util;

import javax.xml.bind.DatatypeConverter;
import org.ict.algorithm.util.StdOut;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class Base64Util {
  
  public static String decodeWithDC(String s) {
    byte[] b = DatatypeConverter.parseBase64Binary(s);
    String result = DatatypeConverter.printBase64Binary(b);
    return result;
  }

  public static String decodeWithAcc(String s) {
    return StringUtils.newStringUtf8(Base64.decodeBase64(s));    
  }

  public static String encodeWithAcc(String s) {
    return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
  }

  public static void main(String[] args) {
    String s = "eHVlbGlhbi5oYW46ZGFuZ2xlMTIz";
    String r1 = decodeWithDC(s);
    String r2 = decodeWithAcc(s);
    StdOut.println(r1);
    StdOut.println(r2);
  }
}
