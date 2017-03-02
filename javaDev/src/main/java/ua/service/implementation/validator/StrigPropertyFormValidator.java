package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.StringProperty;
import ua.service.StringPropertyService;

public class StrigPropertyFormValidator implements Validator {

	private final StringPropertyService stringPropertyService;
	
	public StrigPropertyFormValidator(StringPropertyService stringPropertyService) {
		this.stringPropertyService = stringPropertyService;
	}
	

	@Override
	public boolean supports(Class<?> clazz) {
		return StringProperty.class.equals(clazz);
	}

	
	@Override
	public void validate(Object target, Errors errors) {
		StringProperty property = (StringProperty)target;
		if(property.getId() == 0){
		if(property.getCategory() == null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Can`t be empty");
		}
		else {
			if(stringPropertyService.findByNameAndCategory(property.getName(), property.getCategory().getId())!=null){
				errors.rejectValue("name", "", "property alredy exist");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		}
	}
}
