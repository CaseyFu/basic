package org.casey.basic.generic;


import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

public class MyStack<T> {
    List<T> Stack = (List<T>) Collections.synchronizedCollection(new LinkedList<T>());

    public void push(T x) {
        Stack.add(x);
    }

    public T pop() {
        return Stack.remove(Stack.size() - 1);
    }


    public static void main(String[] args) {

    }
}
