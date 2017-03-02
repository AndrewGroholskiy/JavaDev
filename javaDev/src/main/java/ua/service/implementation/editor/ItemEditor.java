package ua.service.implementation.editor;


import java.beans.PropertyEditorSupport;

import ua.entity.Item;
import ua.service.ItemService;

public class ItemEditor extends PropertyEditorSupport {

	private final ItemService itemService;

	public ItemEditor(ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Item item = itemService.findById(Integer.valueOf(text));
		setValue(item);
		
	}
	
	
}
