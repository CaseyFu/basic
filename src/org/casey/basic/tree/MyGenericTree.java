
//支持泛型的二叉树
package org.casey.basic.tree;

import java.util.List;
import java.util.ArrayList;

public class MyGenericTree<T> {

    private MyGenericTree<T> LeftNode;
    private MyGenericTree<T> RightNode;
    private T Value;

    public void add(T object) {
        if (this.Value == null) {
            this.Value = object;
        } else {
            if ((Integer) object <= (Integer) this.Value) {
                if (this.LeftNode == null) {
                    this.LeftNode = new MyGenericTree<T>();
                }
                this.LeftNode.add(object);
            } else {
                if (this.RightNode == null) {
                    this.RightNode = new MyGenericTree<T>();
                }
                this.RightNode.add(object);
            }
        }
    }

    public List<T> Traverse_In() {
        List<T> L = new ArrayList<T>();


        if (this.LeftNode != null) {
            L.addAll(this.LeftNode.Traverse_In());
        }

        if (this.RightNode != null) {
            L.addAll(this.RightNode.Traverse_In());
        }
        L.add(this.Value);
        return L;
    }


    public static void main(String[] args) {
        MyGenericTree<Integer> T = new MyGenericTree<Integer>();
        int[] a = {2, 1, 3, 5, 4, 8, 7, 6, 9, 0};
        for (int b : a)
            T.add(b);
        List<Integer> L = new ArrayList<Integer>();
        L = T.Traverse_In();
        for (Integer i : L) {
            System.out.println(i);
        }
    }
}
