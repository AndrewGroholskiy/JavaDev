package ua.service.implementation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.CategoryForm;
import ua.service.CategoryService;

public class CategoryValidator implements Validator {

	private final CategoryService categoryService;
	
	
	public CategoryValidator(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CategoryForm category = (CategoryForm)target;
		
		if(category.getId() == 0){
		if(categoryService.findByCategoryName(category.getName()) != null){
			errors.rejectValue("name", "",  "categoty alredy exist");
			
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		
		if(categoryService.findByCategoryName(category.getName()) == null && category.getParent() != null){
			if(!categoryService.findWithStringProperty(category.getParent().getId()).getStringProperties().isEmpty()){
				errors.rejectValue("parent", "", "category alredy has property");
			}
		}
		
		}
		else{
			if(categoryService.findByCategoryName(category.getName()) != null){
				errors.rejectValue("name", "",  " can't update, categoty alredy exist");
				
			}
		}
	}

}
