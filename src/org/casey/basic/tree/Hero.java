package org.casey.basic.tree;

public class Hero {

    public String name;
    public double hp;

    public Hero(String name, double hp) {
        this.name = name;
        this.hp = hp;
    }

    public double GetHp() {
        return this.hp;
    }
}
