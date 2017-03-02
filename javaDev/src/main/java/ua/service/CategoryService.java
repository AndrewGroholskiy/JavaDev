package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import ua.dto.CategoryJson;
import ua.entity.Category;
import ua.form.CategoryForm;
import ua.form.filter.CategoryFormFilter;


public interface CategoryService {

	void save(CategoryForm categoryForm);
	
	List<Category> findAllMainCategory();	
	
	List<Category> findAllCategory();	
	
	List<Category> findWithoutChildren();
	
	void changeParent(int catId, int newParentId);
	
	Category findById(int id);
	
	Category findOne(int id);
	
	Category findByCategoryName(String name);
	
	CategoryForm findForForm(int id);
	
	void delete(int id);

	Category findOneWithParent(int id);

	Category findOneWithChilds(int id);
	 
	List<Category> findChilds(int ParentId);
	
	Page<Category> findAll(Pageable pageable);
	
	Page<Category> findAll(Pageable pageable, CategoryFormFilter form);
	
	Category findWithStringProperty(int id);
	
	List<CategoryJson> findAllCategoryJson();

	List<CategoryJson> findMainCategoryJson();
	
	List<CategoryJson> findSubCategoryJson(int id);
	
	
}
