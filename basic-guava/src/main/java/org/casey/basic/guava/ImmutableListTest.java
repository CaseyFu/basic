package org.casey.basic.guava;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName ImutableListTest
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/2/6 20:15
 */

public class ImmutableListTest {

    @Test
    public void jdkUnmodifiedList(){
        List<String> stringList = Stream.of("111", "222", "333").collect(Collectors.toList());
        List<String> unmodifiedList = Collections.unmodifiableList(stringList);
        // unmodifiedList.add("444");

        System.out.println(unmodifiedList.toString());
        stringList.add("444");
        // unmodifiedList是stringList的一个引用, stringList.add "444" unmodifiedList也会有 "444"
        System.out.println(unmodifiedList);
    }

    @Test
    public void immutableListTest(){
        List<String> stringList = Stream.of("111", "222", "333").collect(Collectors.toList());
        ImmutableList<String> immutableList = ImmutableList.copyOf(stringList);
        System.out.println(immutableList.toString());
        stringList.add("444");
        System.out.println(immutableList.toString());
    }

}
