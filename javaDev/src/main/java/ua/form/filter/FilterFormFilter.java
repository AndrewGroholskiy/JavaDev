package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterFormFilter {

	
	private String serch ="";

	private List<Integer> categorys = new ArrayList<>();

	public String getSerch() {
		return serch;
	}

	public void setSerch(String serch) {
		this.serch = serch;
	}

	public List<Integer> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Integer> categorys) {
		this.categorys = categorys;
	}
	
	
	
}
