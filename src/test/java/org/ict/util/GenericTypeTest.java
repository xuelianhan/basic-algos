package org.ict.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @see https://stackoverflow.com/questions/6474784/java-using-generics-with-lists-and-interfaces
 * 
 *
 */
public class GenericTypeTest {
	interface Record {}
    interface SubRecord extends Record {}

    public static void main(String[] args) {
        List<? extends Record> l = new ArrayList<Record>();
        List<SubRecord> l2 = new ArrayList<SubRecord>();
        Record i = new Record(){};
        SubRecord j = new SubRecord(){};

        //l = l2;
        Record a = l.get( 0 );
       ((List<Record>)l).add( i );       //<--will fail at run time,see below
        ((List<SubRecord>)l).add( j );    //<--will be ok at run time

    }
}
