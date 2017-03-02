package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.UserCheckInfoBeforConfirm;

public class ShopingCartFormValidator implements Validator{

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserCheckInfoBeforConfirm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserCheckInfoBeforConfirm form = (UserCheckInfoBeforConfirm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNamber", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addres", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "", "Can`t be empty");

	}

}
