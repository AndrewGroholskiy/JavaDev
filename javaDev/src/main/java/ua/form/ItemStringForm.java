package ua.form;

import java.util.List;

import ua.entity.Item;
import ua.entity.StringProperty;
import ua.entity.StringValue;

public class ItemStringForm {

	private int id;
	private int stringPropertyId;
	private List<StringValue> stringValues;
	private StringProperty stringProperty;
	private String valueNane;
	private Item item;
	private int categoryId;
	
	
	

	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getStringPropertyId() {
		return stringPropertyId;
	}
	public void setStringPropertyId(int stringPropertyId) {
		this.stringPropertyId = stringPropertyId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getValueNane() {
		return valueNane;
	}
	public void setValueNane(String valueNane) {
		this.valueNane = valueNane;
	}
	public List<StringValue> getStringValues() {
		return stringValues;
	}
	public void setStringValues(List<StringValue> stringValues) {
		this.stringValues = stringValues;
	}
	
	
	public StringProperty getStringProperty() {
		return stringProperty;
	}
	public void setStringProperty(StringProperty stringProperty) {
		this.stringProperty = stringProperty;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	
}
