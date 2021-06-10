package org.ict.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hxl
 * @date 2021/6/8 11:29 AM
 */
public class StreamApiTest {

    public static void main(String[] args) {
        testMergeMultipleLists();
    }

    /**
     * @see <a href="https://stackoverflow.com/questions/45281454/combine-multiple-lists-in-java"></a>
     */
    public static void testMergeMultipleLists() {
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);

        List<Long> list2 = new ArrayList<>();
        list2.add(2L);

        List<Long> list3 = null;//can not add stream of, due to npe

        List<Long> list4 = new ArrayList<>();
        List<Long> mergedList = Stream.of(list1, list2, list4)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(mergedList);
    }
}
