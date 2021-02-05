package org.casey.basic;

import java.util.*;

/**
 * @ClassName Sort
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description Comparator与Comparable
 * @Date 2020/8/30
 */

public class Sort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(7);
        System.out.println(list);
        // type1(list);
        type2(list);
        System.out.println(list);
    }

    public static void type1(List<Integer> list) {
        list.sort(Integer::compareTo);
    }

    public static void type2(List<Integer> list) {
        list.sort(Comparator.reverseOrder());
    }

    /**
     * 类似type1写法
     *
     * @param list
     */
    public static void type3(List<Integer> list) {
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * 类似type2写法
     *
     * @param list
     */
    public static void type4(List<Integer> list) {
        list.sort(Comparator.reverseOrder());
    }
}

class CompareToTest implements Comparable<CompareToTest> {

    Integer age;

    public CompareToTest(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(CompareToTest o) {
        if (this.age > o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        }
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompareToTest that = (CompareToTest) o;
        return Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return "CompareToTest{" +
                "age=" + age +
                '}';
    }

    public static void main(String[] args) {
        TreeMap<CompareToTest, String> map = new TreeMap<>();
        map.put(new CompareToTest(1), "aaa");
        map.put(new CompareToTest(9), "bbb");
        map.put(new CompareToTest(4), "ccc");
        map.put(new CompareToTest(7), "ddd");
        Set<CompareToTest> keys = map.keySet();
        for (CompareToTest each : keys) {
            System.out.println(each.toString());
        }
    }
}