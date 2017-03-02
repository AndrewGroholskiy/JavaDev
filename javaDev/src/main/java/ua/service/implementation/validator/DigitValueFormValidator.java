package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.DigitValueForm;

public class DigitValueFormValidator implements Validator{

	
	private final Pattern pattern = Pattern.compile("[-0-9.0-9]{1,8}");
	

	@Override
	public boolean supports(Class<?> clazz) {
		return DigitValueForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DigitValueForm digitValueForm = (DigitValueForm)target;
		if(digitValueForm.getDigitProperty() == null){
			
			errors.rejectValue("digitProperty", "", "category can't be empty");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "", "Can`t be empty");
		Matcher m = pattern.matcher(digitValueForm.getValue());
		if(!m.matches()){
			errors.rejectValue("value", "" , "required numerical values");
		}
		
		
	}

}
