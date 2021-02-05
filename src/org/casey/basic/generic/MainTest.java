package org.casey.basic.generic;

import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.casey.basic.common.Student;

public class MainTest {
    public static void main(String[] args) {
        MainTest.MainTest3();
    }

    public static void MainTest3() {
        //用Collections工具类把MyGenericStack转换为线程安全类
        List<Integer> Stack = new LinkedList<Integer>();
        Collections.synchronizedList(Stack);

        Stack.add(1);    //入栈
        Integer temp = Stack.remove(Stack.size() - 1);    //出栈
        //由于Stack容器已经被线程安全化,所以在Stack基础上add()和remove()不会混乱

    }

    public static void MainTest1() {
        //遍历3种泛型
        List<? extends Hero> L = new ArrayList<>();
        List<Hero> L0 = new ArrayList<>();
        L0.add(new APHero("hhh"));
        L0.add(new ADHero("ggg"));
        L0.add(new Hero("hg"));
        L = L0;
        for (Hero h : L)
            System.out.println(h.getName());
    }

    public static void MainTest2() {
        Hero h = new ADHero("xfk");

    }
}
