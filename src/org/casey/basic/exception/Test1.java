package org.casey.basic.exception;
//自定义的异常


class HeroIsDeadException extends Exception {
    public HeroIsDeadException() {

    }

    public HeroIsDeadException(String s) {
        super(s);
    }
}

class Hero {
    String name;
    double hp;

    public Hero() {

    }

    public Hero(String name) {
        this.name = name;
    }

}

public class Test1 {
    public static void main(String[] args) {
        Hero timo = new Hero("提莫");
        timo.hp = -1;
        Hero gailun = new Hero("盖伦");
        gailun.hp = 3000;
        Test1.isdead(timo);
    }

    public static void isdead(Hero h) {
        if (h.hp <= 0)
            try {
                throw new HeroIsDeadException(h.name + "的血量是" + h.hp + "所以不会死");
            } catch (HeroIsDeadException e) {
                // TODO 自动生成的 catch 块
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
    }

}
