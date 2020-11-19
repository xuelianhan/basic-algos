package org.ict.algorithm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;




/**
 * https://stackoverflow.com/questions/17840483/how-to-have-java-method-return-generic-list-of-any-type
 * https://github.com/FasterXML/jackson-core/issues/295
 * https://stackoverflow.com/questions/22635945/adding-up-bigdecimals-using-streams
 * https://stackoverflow.com/questions/28821715/java-lang-classcastexception-java-util-linkedhashmap-cannot-be-cast-to-com-test
 * https://stackoverflow.com/questions/32884195/filter-values-only-if-not-null-using-lambda-in-java8
 * https://stackoverflow.com/questions/26744885/jackson-objectmapper-upper-lower-case-issues
 * @author hanxuelian
 *
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static <T> List<T> readToList(String jsonStr, Class<T> clazz) {
        List<T> result = null;
        try {
            result = mapper.readValue(jsonStr, mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return result;
    }
    
    public static <T> T readToBean(String json, Class<T> clazz) {
        T result = null;
        try {
            result = (T)mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        String s1 = "[{\"name\":\"Amazon\",\"value\":\"10\"},{\"name\":\"Google\",\"value\":\"10\"}]";
        String s2 = "{\"name\":\"Amazon\",\"value\":\"10\"}";
        System.out.println(JsonUtil.readToList(s1, NameValue.class));
        System.out.println(JsonUtil.readToBean(s2, NameValue.class));
    }
    
    public static class NameValue {
        private String name;
        private String value;
        @Override public String toString() {
            return String.format("%s,%s", name, value);
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}
