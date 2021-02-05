
//练习,随机生成10个Hero对象,加入到树中,对血量进行降序排序
package org.casey.basic.tree;

import java.util.LinkedList;
import java.util.Iterator;

public class Tree_Test0 {

    public Tree_Test0 LeftNode;
    public Tree_Test0 RightNode;
    public Hero Value;

    public void add(Hero x) {
        if (this.Value == null) {
            this.Value = x;
        } else {
            if (x.GetHp() <= this.Value.GetHp()) {
                if (LeftNode == null) {
                    LeftNode = new Tree_Test0();
                }
                LeftNode.add(x);
            } else {
                if (RightNode == null) {
                    RightNode = new Tree_Test0();
                }
                RightNode.add(x);
            }
        }
    }

    public LinkedList<Hero> Traverse_In() {
        //中序遍历
        LinkedList<Hero> L = new LinkedList<Hero>();

        if (this.RightNode != null) {
            L.addAll(this.RightNode.Traverse_In());
        }
        L.add(this.Value);
        if (this.LeftNode != null) {
            L.addAll(this.LeftNode.Traverse_In());
        }
        return L;
    }


    public static void main(String[] args) {
        LinkedList<Hero> L = new LinkedList<Hero>();
        for (int i = 0; i < 10; i++) {
            L.add(new Hero("Hero-" + i, (double) (Math.random() * 900 + 100)));
        }
        Iterator<Hero> C = L.iterator();

        Tree_Test0 T = new Tree_Test0();
        while (C.hasNext()) {
            Hero H = (Hero) C.next();
            T.add(H);
        }
        LinkedList<Hero> Temp = new LinkedList<Hero>();
        Temp = T.Traverse_In();

        C = Temp.iterator();
        while (C.hasNext()) {
            Hero H = (Hero) C.next();
            System.out.format("%s %.2f %n", H.name, H.hp);
        }

    }
}
