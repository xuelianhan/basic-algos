package org.ict.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxl
 * @date 2021/5/24 11:51 AM
 */
public class GenericTest {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        //generic type is removed here
        System.out.println(list.getClass().toGenericString());
        System.out.println(list.getClass().toString());
    }
}
