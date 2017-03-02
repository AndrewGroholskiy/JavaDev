package ua.dto;

public class StringValueJson {

	private int id;
	private String name;
	private String stringProperty;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStringProperty() {
		return stringProperty;
	}
	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}
	@Override
	public String toString() {
		return "StringValueJson [name=" + name + ", stringProperty="
				+ stringProperty + "]";
	}
	
}
