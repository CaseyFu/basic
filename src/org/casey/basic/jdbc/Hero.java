package org.casey.basic.jdbc;

public class Hero {
	private int id;
	private String name;
	private int age;
	public Hero(int id,String name,int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public void getInformation() {
		System.out.println("id:"+id+"name:"+name+"age:"+age);
	}
}
