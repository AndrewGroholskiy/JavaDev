package ua.dto;

import java.util.List;

public class FilterJson {

	private int categoryId;
	
	private String name;
	
	private List<String> filterValues;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFilterValues() {
		return filterValues;
	}

	public void setFilterValues(List<String> filterValues) {
		this.filterValues = filterValues;
	}
	
	
	
}
