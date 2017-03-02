package ua.service.implementation.validator;



import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import ua.form.ItemStringForm;
import ua.service.ItemService;


public class ItemStringFormValidator implements Validator{

	private final ItemService itemService;
	
	public ItemStringFormValidator(ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemStringForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		ItemStringForm itemStringForm = (ItemStringForm)target;
		
		if(itemStringForm.getStringPropertyId() == 0){
			if(itemStringForm.getValueNane()==""){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valueNane", "", "cen't be empty");			
			}else{
				if(itemService.findById(itemStringForm.getId()) == null){
					errors.rejectValue("id", "", "item with id = " +itemStringForm.getId() + " does not exist");
				}else{
					if(itemService.findByStringProperty(itemStringForm.getId(), itemStringForm.getStringProperty().getName()) != null){
						errors.rejectValue("stringProperty", "", "string property alredy exist");
					}
				}
			}
		}
	}

}
