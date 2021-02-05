
//根据TreeSet中的public TreeSet(Comparator c),构造降序排序的值
package org.casey.basic.hash;

import java.util.Set;
import java.util.TreeSet;
import java.util.Random;
import java.util.Comparator;

public class TreeSet_Test {
    public static void main(String[] args) {
        Comparator<Integer> c = new Comparator<Integer>() {
            public int compare(Integer A, Integer B) {
                if (A > B)    //把A看成前面一个B看成后面一个,if(A>B)那么就交换A与B的值,即为升序
                    return 1;
                else
                    return -1;
            }


        };

        Random r = new Random();
        Set<Integer> S = new TreeSet<Integer>(c);
        for (int i = 0; i < 10; i++) {
            S.add(r.nextInt(100));
        }
        System.out.println(S);
    }
}
