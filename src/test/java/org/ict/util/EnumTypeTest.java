package org.ict.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @see https://stackoverflow.com/questions/27703119/convert-from-string-to-a-java-enum-with-large-amount-of-values/27703839#27703839
 * @see https://stackoverflow.com/questions/27807232/finding-enum-value-with-java-8-stream-api
 */
public class EnumTypeTest {

    enum Type{
        X("S1"),
        Y("S2");

        private static class Holder {
            static Map<String, Type> MAP = new HashMap<>();
        }

        private Type(String s) {
            Holder.MAP.put(s, this);
        }

        public static Type find(String val) {
            Type t = Holder.MAP.get(val);
            if(t == null) {
                throw new IllegalStateException(String.format("Unsupported type %s.", val));
            }
            return t;
        }
    }
    
    public enum Code {
        CODE_1("string1"),
        CODE_2("string2"),
        CODE_3("string3"),
        // etc
        ;

        private static class Holder {
            static Map<String, Code> CODE_MAP = new HashMap<>();
        }

        private final String code;

        private Code(String code) {
            this.code = code;
            Holder.CODE_MAP.put(code, this);
        }

        public String getCode() {
            return this.code;
        }

        public Code convertFromString(String code) {
            return Holder.CODE_MAP.get(code);
        }
    }
    
	@Test
	public void testIntRange() {
		int x = 10000;
		System.out.println(x);
	}
}
