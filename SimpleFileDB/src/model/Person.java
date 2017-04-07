package model;

import java.util.Comparator;

public class Person  {

	private int id;
	private int age;
	private String name;


	public Person(int id, int age, String name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public static class ComperingByAge implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			if(o1.getAge() > o2.getAge())return -1;
			else if(o1.getAge() < o2.getAge())return 1;
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", age=" + age + ", name=" + name + "]";
	}
	
	
}
