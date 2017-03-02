package ua.dto;

public class DigitValueJson {
	
	private int id;
	private int value;
	private String digitProperty;
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDigitProperty() {
		return digitProperty;
	}
	public void setDigitProperty(String digitProperty) {
		this.digitProperty = digitProperty;
	}
	@Override
	public String toString() {
		return "DigitValueJson [value=" + value + ", digitProperty="
				+ digitProperty + "]";
	}
	
	
}
