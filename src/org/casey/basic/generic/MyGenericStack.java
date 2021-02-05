package org.casey.basic.generic;
//自己写的支持泛型的栈

//同时该类所有方法都有synchronized修饰就成了线程安全类,同一时刻只能一条线程修改数据


import java.util.List;
import java.util.ListIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class MyGenericStack<T> {

    private int count = 0;
    private List<T> L = new LinkedList<T>();

    //入栈
    public synchronized boolean push(T hero) {
        this.count++;
        return this.L.add(hero);
    }

    //出栈
    public synchronized T pop() {
        this.count--;
        return this.L.remove(this.L.size() - 1);
    }

    public synchronized int size() {
        return this.count;
    }

    public synchronized void the_print() {
        for (T temp : L)
            System.out.println(temp);
    }

    public static void main(String[] args) {

        MyGenericStack<Integer> S = new MyGenericStack<Integer>();
        S.push(1);
        S.push(2);
        S.push(3);
        S.push(4);
        S.push(5);
        while (S.size() != 0) {
            System.out.println(S.pop());
        }
        //S.the_print();
    }

}

