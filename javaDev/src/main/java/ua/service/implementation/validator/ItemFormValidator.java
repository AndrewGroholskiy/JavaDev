package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.ItemForm;
import ua.service.ItemService;

public class ItemFormValidator implements Validator{

	private final ItemService itemService;
	
	private final Pattern pattern = Pattern.compile("^[-0-9.0-9]{1,8}$");
	
	//private final Pattern pattern2 = Pattern.compile("^[a-zA-Z]$");
	public ItemFormValidator(ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemForm itemForm = (ItemForm)target;
		if(itemForm.getId() > 0){
		if(itemService.findById(itemForm.getId()) != null){
			errors.rejectValue("id","", "item with id " + itemForm.getId() +" alredy exist");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brend", "", "cen't be empty");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", "", "cen't be empty");	
		
		Matcher m = pattern.matcher(itemForm.getPrice());
			if(itemForm.getPrice().length()==0){
				errors.rejectValue("price", "", "cen't be empty");
			}else{
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "cen't be empty");				
				if(!m.matches()){
					errors.rejectValue("price", "" , "required numerical values");
				}	
			}	
		}else{
			errors.rejectValue("id", "", "can't be zero");
		}
	}
	

	
}
