package ua.dto;

import java.util.List;


public class ItemJson {
	
	private int itemId;
	private String brend;
	private String model;
	private float price;
	private int categoryId;
	private String image;
	
	
	private List<StringValueJson> stringValue;
	private List<DigitValueJson> digitValue;
	
	private int version;
	
	private String path;
	
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public List<DigitValueJson> getDigitValue() {
		return digitValue;
	}

	public void setDigitValue(List<DigitValueJson> digitValue) {
		this.digitValue = digitValue;
	}

	public String getBrend() {
		return brend;
	}

	public void setBrend(String brend) {
		this.brend = brend;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<StringValueJson> getStringValue() {
		return stringValue;
	}

	public void setStringValue(List<StringValueJson> stringValue) {
		this.stringValue = stringValue;
	}
	


	@Override
	public String toString() {
		return "ItemJson [brend=" + brend + ", model=" + model + ", price="
				+ price + ", stringValue=" + stringValue + ", digitValue="
				+ digitValue + "]";
	} 
	
	
}
