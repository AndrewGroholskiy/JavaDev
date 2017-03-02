package ua.dto;

import java.util.List;

public class CheckOutFormJson {

	private String name;
	private String lastName;
	private String phone;
	private String addres;
	private String email;
	private List<CheckOutFormItemsJson> items;

	
	public List<CheckOutFormItemsJson> getItems() {
		return items;
	}
	public void setItems(List<CheckOutFormItemsJson> items) {
		this.items = items;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
