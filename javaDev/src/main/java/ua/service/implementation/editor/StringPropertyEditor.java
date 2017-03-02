package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.StringProperty;
import ua.service.StringPropertyService;

public class StringPropertyEditor extends PropertyEditorSupport{
	
	private final StringPropertyService stringPropertyService;
	
	public StringPropertyEditor(StringPropertyService stringPropertyService){
		this.stringPropertyService=stringPropertyService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {	
	StringProperty property = stringPropertyService.findOne(Integer.valueOf(text));
	setValue(property);
	}


	
	
}
