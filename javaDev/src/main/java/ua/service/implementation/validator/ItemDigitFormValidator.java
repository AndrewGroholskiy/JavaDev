package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.ItemDigitForm;
import ua.service.ItemService;

public class ItemDigitFormValidator implements Validator{

	private final ItemService itemService;
	
	private final Pattern pattern = Pattern.compile("[-0-9.0-9]{1,8}");
	
	public ItemDigitFormValidator(ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemDigitForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemDigitForm itemDigitForm = (ItemDigitForm)target;
		
		if(itemDigitForm.getDigitPropertyId() == 0){
		if(itemDigitForm.getValueNane()==""){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valueNane", "", "cen't be empty");			
		}else{
			Matcher m = pattern.matcher(itemDigitForm.getValueNane());
			if(!m.matches()){
				errors.rejectValue("valueNane", "" , "required numerical values");
			}else{
				if(itemService.findById(itemDigitForm.getId()) == null){
					errors.rejectValue("id", "", "item with id = " +itemDigitForm.getId() + " does not exist");
				}else{
					if(itemService.findByDigitProperty(itemDigitForm.getId(), itemDigitForm.getDigitProperty().getName()) !=null){
						errors.rejectValue("digitProperty","", "digit property alredy exist");
					}
				}				
			}
		}
	}	
}

}
