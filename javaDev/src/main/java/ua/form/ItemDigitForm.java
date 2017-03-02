package ua.form;

import java.util.List;





import ua.entity.DigitProperty;
import ua.entity.DigitValue;
import ua.entity.Item;
;

public class ItemDigitForm {

	private int id;
	private int digitPropertyId;
	private List<DigitValue> digitValues;
	private DigitProperty digitProperty;
	private String valueNane;
	private Item item;
	
	
	public int getDigitPropertyId() {
		return digitPropertyId;
	}
	public void setDigitPropertyId(int digitPropertyId) {
		this.digitPropertyId = digitPropertyId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public DigitProperty getDigitProperty() {
		return digitProperty;
	}
	public void setDigitProperty(DigitProperty digitProperty) {
		this.digitProperty = digitProperty;
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<DigitValue> getDigitValues() {
		return digitValues;
	}
	public void setDigitValues(List<DigitValue> digitValues) {
		this.digitValues = digitValues;
	}
	public String getValueNane() {
		return valueNane;
	}
	public void setValueNane(String valueNane) {
		this.valueNane = valueNane;
	}
	

	
}
