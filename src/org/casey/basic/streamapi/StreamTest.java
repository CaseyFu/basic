package org.casey.basic.streamapi;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StreamMemo
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description stream流操作 备忘录
 * @Date 2020/7/20
 */

public class StreamTest {

    /**
     * 使用流进行过滤操作
     * 把List转化为Stream, 利用filter对流进行过滤, 只保留表达式为true的结果
     */
    @Test
    public void filterTest() {
        List<String> strings = CollUtil.newArrayList();
        strings.add("string 1");
        strings.add(null);
        strings.add("string 3");
        strings.add("string 4");
        strings.add(null);
        strings.add("string 6");
        strings.stream()
                .filter(each -> !ObjectUtil.isNull(each))
                .forEach(System.out::println);
    }

    /**
     * 使用map进行过滤, map方法的用途是将旧数据转换后变为新数据，是一种 1:1 的映射
     * 回调函数中, 多条语句用花括号扩上并写return语句, 单条结果直接写, 不用写花括号和return
     */
    @Test
    public void mapTest() {
        Stream.of("a", "b", "c").map(i -> {
            i = i + 1;
            return i;
        }).forEach(System.out::println);
    }

    /**
     * flatMap的使用, 给定单词列表，返回其中不同字母
     * flatMap的作用是将String[] 转化为 String
     */
    @Test
    public void flatMapTest() {
        List<String> strings = Arrays.asList("Hello", "World");
        strings.stream()
                .map(each -> each.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * distinct的使用
     */
    @Test
    public void distinctTest() {
        List<Integer> integers = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 5, 3, 1);
        integers.stream()
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * limit的使用, 只限制流的前3个元素
     */
    @Test
    public void limitTest() {
        List<Integer> integers = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 5, 3, 1);
        integers.stream()
                .limit(3)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    /**
     * skip的使用，跳过流的前3个元素
     */
    @Test
    public void skipTest() {
        List<Integer> integers = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 5, 3, 1);
        integers.stream()
                .skip(3)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }


    /**
     * forEach参数是执行的具体代码
     * 调用forEach之后会关闭流
     * parallel()对流进行并行操作, 程序会失去封闭性和再现性
     */
    @Test
    public void forEachTest() {
        Stream.of(1, 2, 3).parallel()
                .forEach(i -> {
                    i = i * 2;
                    System.out.println(i);
                });
    }

    /**
     * 对流进行排序, 不能使用parallel()对流进行并行操作, 会导致排序混乱
     */
    @Test
    public void sortedTest() {
        Stream.of(5, 3, 8, 1).sorted()
                .forEach(System.out::println);
    }

    /**
     * 把流转化为List或String
     */
    @Test
    public void toListTest() {
        List<Integer> integers = Stream.of(1, 5, 7, 4).parallel().collect(Collectors.toList());
        integers.forEach(i -> System.out.print(i + ", "));
        System.out.println();
        System.err.println("------------------");
        System.out.println(Arrays.toString(Stream.of(1, 5, 7, 4).parallel().toArray()));
        System.err.println("------------------");
        // 转化为String
        Stream<String> strings = Stream.of("abc", "de", "", "hi", null, "jk");
        String mergedStr = strings.filter(i -> null != i && !"".equals(i)).collect(Collectors.joining(", "));
        System.out.println(mergedStr);
    }

    /**
     * 查找和匹配，allMatch, anyMatch, noneMatch, findFirst, findAny
     * 查找整数集合中第一个 平方能被3整除的数
     */
    @Test
    public void matchTest() {
        List<Integer> integers = Stream.of(1, 2, 4, 4, 5, 7).collect(Collectors.toList());
        Optional<Integer> firstSquareDividedByThree = integers.stream()
                .map(each -> each * each)
                .filter(each -> each % 3 == 0)
                .findFirst();
        firstSquareDividedByThree.ifPresent(System.out::println);
        System.out.println(firstSquareDividedByThree.orElse(null));
    }


    /**
     * reduce,
     */
    @Test
    public void reduceTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        int num = integers.stream().reduce(1, (x, y) -> x * y);
        System.out.println(num);
        Optional<Integer> i = integers.stream().reduce(Integer::sum);
        System.out.println(i.get());
    }
}
