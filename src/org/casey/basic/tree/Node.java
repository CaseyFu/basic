
//树,左结点<=右结点
package org.casey.basic.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Node {

    //
    public Node LeftNode;    //左结点
    public Node RightNode;    //右结点
    public Object Value;    //值

    public void add(Object O) {
        if (Value == null)    //如果父节点不存在,就创建这个父节点
        {
            this.Value = O;
        } else {
            if ((Integer) O <= (Integer) this.Value)    //如果插入结点值小于等于父节点值,放在左边
            {
                if (LeftNode == null) {
                    LeftNode = new Node();
                }
                LeftNode.add(O);
            } else    //放在右边
            {
                if (RightNode == null) {
                    RightNode = new Node();
                }
                RightNode.add(O);
            }
        }
    }

    public ArrayList<Object> Traverse_Last()    //先序
    {
        ArrayList<Object> Arr = new ArrayList<Object>();

        if (this.LeftNode != null) {
            Arr.addAll(this.LeftNode.Traverse_Last());    //递归左子树,返回的是个容器,所以addAll
        }
        if (this.RightNode != null) {
            Arr.addAll(this.RightNode.Traverse_Last());
        }
        Arr.add(this.Value);    //父节点为一个对象,用add
        return Arr;
    }

    public ArrayList<Object> Traverse_In()    //中序
    {
        ArrayList<Object> Arr = new ArrayList<Object>();
        if (this.LeftNode != null) {
            Arr.addAll(LeftNode.Traverse_In());
        }
        Arr.add(this.Value);
        if (this.RightNode != null) {
            Arr.addAll(RightNode.Traverse_In());
        }
        return Arr;
    }


    public Object GetValue() {
        return this.Value;    //根节点
    }

    public static void main(String[] args) {
        Node Tree = new Node();
        int[] temp = {67, 7, 73, 40};
        for (int i : temp) {
            Tree.add(i);
        }
        //ArrayList<Object> C= Tree.Traverse_In();
        //Iterator<Object> d = C.iterator();
        //while(d.hasNext())
        //{
        //	int i = (int)d.next();
        //	System.out.print(i+"\t");
        //}
        System.out.println(Tree.GetValue());
    }


}
