package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FiltersValue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String value;
	
	@ManyToOne
	private Filters filtersValue;

	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Filters getFiltersValue() {
		return filtersValue;
	}

	public void setFiltersValue(Filters filtersValue) {
		this.filtersValue = filtersValue;
	}

	

	
	
}
