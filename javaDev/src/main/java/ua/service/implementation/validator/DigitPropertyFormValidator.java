package ua.service.implementation.validator;





import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.DigitProperty;
import ua.service.DigitPropertyService;

public class DigitPropertyFormValidator  implements Validator{

	private final DigitPropertyService digitPropertyService;

	
	
	public DigitPropertyFormValidator(DigitPropertyService digitPropertyService) {
		this.digitPropertyService = digitPropertyService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return DigitProperty.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DigitProperty property = (DigitProperty)target;
		if(property.getId()==0){
			if(property.getCategory() == null){
			errors.rejectValue("category", "", "category can't be empty");
			}else{
				if(digitPropertyService.findByNameAndCategory(property.getName(), property.getCategory().getName()) != null){
				errors.rejectValue("name", "", "digit property alredy exist");	
				}
			}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		}
	}

}
