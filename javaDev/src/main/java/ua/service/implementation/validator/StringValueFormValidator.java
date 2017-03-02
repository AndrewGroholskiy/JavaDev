package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.StringValueForm;


public class StringValueFormValidator implements Validator{
	

	@Override
	public boolean supports(Class<?> clazz) {
		return StringValueForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StringValueForm stringValue = (StringValueForm)target;
		if(stringValue.getStringProperty() == null){
			errors.rejectValue("stringProperty", "", "can't be empty");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "cen't be empty");
		
	}

}
