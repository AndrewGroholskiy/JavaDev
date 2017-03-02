package ua.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Filters {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy="filtersValue")
	private List<FiltersValue> filtersValue;

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

	public List<FiltersValue> getFiltersValue() {
		return filtersValue;
	}

	public void setFiltersValue(List<FiltersValue> filtersValue) {
		this.filtersValue = filtersValue;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
 	
	
	
	
}
