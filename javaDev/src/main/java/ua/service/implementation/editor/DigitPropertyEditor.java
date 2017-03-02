package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.DigitProperty;
import ua.service.DigitPropertyService;

public class DigitPropertyEditor extends PropertyEditorSupport { 
 
	private final DigitPropertyService digitPropertyService;


	public DigitPropertyEditor(DigitPropertyService digitPropertyService) {
		this.digitPropertyService = digitPropertyService;
	}


	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	DigitProperty digitProperty = digitPropertyService.findOne(Integer.valueOf(text));
	setValue(digitProperty);
	}
	
	
	
}
