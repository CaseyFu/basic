package org.casey.basic.common;

/**
 * @ClassName A
 * @Author Casey Fu
 * @Description
 * 多态, 继承的经典案例
 * 继承链的4个优先级:
 * this.show(object),          在引用变量类型方法中, 找这个方法
 * super.show(object),         在超类中的方法中, 找这个方法
 * this.show((super)object),   在引用变量类型方法中, 找这个方法, 参数是object的超类
 * super.show((super)object),
 * A a1 = new B();
 * A为引用变量类型, a1为引用变量, a1==this关键字
 * (super)object表示object的祖先类, 有爸爸和爷爷就要爸爸, 选亲近的.
 * new B()为引用对象类型, 在这里决定用B类中的方法, 但前提是B类的方法是@Override的
 * @Date 2020/7/6 15:41
 */

public class A {
    int i=0;
    public String show(D object) {
        return ("A and D");
    }

    public String show(A object) {
        return ("A and A");
    }
}

class B extends A{
    int i=1;
    public String show(B object){
        return ("B and B");
    }

    @Override
    public String show(A object){
        return ("B and A");
    }
}

class C extends B{

}

class D extends B{

}

class Test {
    public static void main(String[] args) {
        // 用的是实例变量里面的方法
        A a1 = new A(); // a1使用A类中的方法
        A a2 = new B(); // a2优先使用B类中的方法，再使用A类中的方法
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));

        System.out.println("4--" + a2.show(b));
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));

        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));

        System.out.println(a1.i);
        System.out.println(a2.i);

    }
}
