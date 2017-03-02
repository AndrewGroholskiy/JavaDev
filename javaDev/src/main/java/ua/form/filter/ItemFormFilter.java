package ua.form.filter;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;


public class ItemFormFilter {
	
	private Set<String> brend;
	
	private Map<String, Set<String>> filters;
	
	private String name;
	
	private int category;

	private String min = "";
	
	private String max = "";
	
	private int minInt = 0;
	
	private int maxInt = 0;
	
	
	private static final Pattern p = Pattern.compile("^[0-9]{1,9}$");

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Map<String, Set<String>> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Set<String>> filters) {
		this.filters = filters;
	}

	
	public Set<String> getBrend() {
		return brend;
	}

	public void setBrend(Set<String> brend) {
		this.brend = brend;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(p.matcher(min).matches()) minInt = Integer.valueOf(min);
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(p.matcher(max).matches()) maxInt = Integer.valueOf(max);
		this.max = max;
	}

	public int getMinInt() {
		return minInt;
	}

	public void setMinInt(int minInt) {
		this.minInt = minInt;
	}

	public int getMaxInt() {
		return maxInt;
	}

	public void setMaxInt(int maxInt) {
		this.maxInt = maxInt;
	}


	
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	
	
	
	
}
