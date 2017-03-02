package ua.form;

import java.util.List;

import ua.entity.Item;
import ua.entity.StringProperty;

public class StringValueForm{

    private int id;
    private String name;
    private StringProperty stringProperty;
    private List<Item> items;
	
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
	public StringProperty getStringProperty() {
		return stringProperty;
	}
	public void setStringProperty(StringProperty stringProperty) {
		this.stringProperty = stringProperty;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
