package org.ict.rpc;

import java.lang.reflect.Type;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * 
 * @see https://coderanch.com/t/383648/java/java-reflection-element-type-List
 * As you see, you can get info about the declared type of instance variable fooList. 
 * You can learn that it's a List<? extends Foo>. 
 * However, if you try to examine an actual instance that is referenced by that variable, 
 * the most you can learn is that it's (in this case) an ArrayList. 
 * You can't find out whether it's an ArrayList<Foo> or an ArrayList<Bar> or an ArrayList<? extends Bar>. 
 * That specific info doesn't exist for runtime instances of a generic class. 
 * It exists for the declared type of a field, or of a method parameter or return value - 
 * but not for actual instances of the objects that are represented or referenced by those fields, 
 * parameters or return values.
 * 
 * execution results:
 * 
 * type: java.util.List<? extends org.ict.rpc.GenericTypeInfo$Foo>
 * raw type: interface java.util.List
 * owner type: null
 * actual type args:
 *    ? extends org.ict.rpc.GenericTypeInfo$Foo
 *    
 *    obj: []
 *    obj class: class java.util.ArrayList
 *
 */
public class GenericTypeInfo {
    java.util.List<? extends Foo> fooList = new ArrayList<Bar>();
    
    public static void main(String[] args) throws Exception {
        Field field = GenericTypeInfo.class.getDeclaredField("fooList");
  
        Type type = field.getGenericType();
        System.out.println("type: " + type);
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            System.out.println("raw type: " + pt.getRawType());
            System.out.println("owner type: " + pt.getOwnerType());
            System.out.println("actual type args:");
            for (Type t : pt.getActualTypeArguments()) {
                System.out.println("    " + t);
            }
        }
  
        System.out.println();
  
        Object obj = field.get(new GenericTypeInfo());
        System.out.println("obj: " + obj);
        System.out.println("obj class: " + obj.getClass());
    }
  
    static class Foo {}
  
    static class Bar extends Foo {}
}
