package org.casey.basic.gui;

public class Hero {
	private int id;
	private String name;
	private double hp;
	public Hero(int id, String name, double hp) {
		this.id = id;
		this.name = name;
		this.hp = hp;
	}
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public double getHp() {
		return this.hp;
	}
	
	public void getImformation() {
		System.out.println("此英雄的id是"+id+"名字是:"+name+"当前的血量是:"+hp);
	}
}
