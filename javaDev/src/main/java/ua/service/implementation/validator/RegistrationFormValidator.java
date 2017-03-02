package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import ua.form.UserForm;
import ua.service.UserServise;

public class RegistrationFormValidator implements org.springframework.validation.Validator{

	
	private final UserServise userService;
	
	public RegistrationFormValidator(UserServise userService) {
		this.userService = userService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	 
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm form = (UserForm) target;
		if(userService.findByMail(form.getMail()) != null){
			errors.rejectValue("mail", "", "this email alredy used");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNamber", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addres", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "", "Can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can`t be empty");
	}
}
