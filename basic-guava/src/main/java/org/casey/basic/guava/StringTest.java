package org.casey.basic.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName StringTest
 * @Author Fu Kai
 * @Description 对字符串的处理
 * @Date 2021/2/6 23:52
 */

public class StringTest {
    Joiner joiner0 = Joiner.on("->").skipNulls();
    Joiner joiner1 = Joiner.on("->").useForNull("null");

    @Test
    public void joinerTest() {
        // Joiner不可变 线程安全, 可做静态变量使用
        System.out.println(joiner1.join("Harry", null, "Ron", "Hermione"));
    }

    @Test
    public void splitterTest() {
        String charSequence = ",a,,b,";
        System.out.println(Arrays.toString(charSequence.split(",")));

        List<String> stringList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(charSequence);
        System.out.println(stringList);

    }

    @Test
    public void charsetTest() {
        System.out.println(Charsets.UTF_8);
        System.out.println(StandardCharsets.UTF_8);
    }
}
