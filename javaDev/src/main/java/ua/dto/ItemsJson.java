package ua.dto;

import java.util.List;

public class ItemsJson {

	private List<ItemJson> items;

	private int pageNamber;
	
	private int carentPage;
	
	private int pageSize;
	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCarentPage() {
		return carentPage;
	}

	public void setCarentPage(int carentPage) {
		this.carentPage = carentPage;
	}

	public List<ItemJson> getItems() {
		return items;
	}

	public void setItems(List<ItemJson> items) {
		this.items = items;
	}

	public int getPageNamber() {
		return pageNamber;
	}

	public void setPageNamber(int pageNamber) {
		this.pageNamber = pageNamber;
	}
	
	
}
