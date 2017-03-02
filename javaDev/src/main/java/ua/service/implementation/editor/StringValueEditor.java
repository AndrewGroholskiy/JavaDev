package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;



import ua.entity.StringValue;
import ua.service.StringValueService;

public class StringValueEditor extends PropertyEditorSupport{

private final StringValueService stringValueService;
	
	public StringValueEditor(StringValueService stringValueService) {
	this.stringValueService = stringValueService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	StringValue stringValue = stringValueService.findOne(Integer.valueOf(text));
	setValue(stringValue);
	}
	
	
	
}
