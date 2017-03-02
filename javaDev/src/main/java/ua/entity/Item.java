package ua.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable
	private List<DigitValue> digitValues;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable
	private List<StringValue> stringValues;
	
	private int categoryId;
	private String brend;
	private String model;
	private float price; 
	private int reviews = 0;
	
	private int version;
	
	private String path;
	
	public int getReviews() {
		return reviews;
	}
	public void setReviews(int reviews) {
		this.reviews = reviews;
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
	public List<StringValue> getStringValues() {
		return stringValues;
	}
	public void setStringValues(List<StringValue> stringValues) {
		this.stringValues = stringValues;
	}

	
	
}
