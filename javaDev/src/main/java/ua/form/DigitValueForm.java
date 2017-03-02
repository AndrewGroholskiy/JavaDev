package ua.form;

import java.util.List;


import ua.entity.DigitProperty;
import ua.entity.Item;

public class DigitValueForm {

	private int id;
	
	private String value;
	
	private DigitProperty digitProperty;

	private List<Item> items;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public DigitProperty getDigitProperty() {
		return digitProperty;
	}

	public void setDigitProperty(DigitProperty digitProperty) {
		this.digitProperty = digitProperty;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
}
