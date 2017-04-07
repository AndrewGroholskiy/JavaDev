package model;

public class Car {
	
	private int id;
	private String model;
	private String color;
	private int probig;
	private double time;
	public Car(int id, String model, String color, int probig, double time) {
		this.id = id;
		this.model = model;
		this.color = color;
		this.probig = probig;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getProbig() {
		return probig;
	}
	public void setProbig(int probig) {
		this.probig = probig;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	
	
}
