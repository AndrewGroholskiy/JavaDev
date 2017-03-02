package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.StringPropertyJson;
import ua.entity.StringProperty;
import ua.form.filter.StringPropertyFormFilter;


public interface StringPropertyService {

	
	void save(StringProperty property);
	
	StringProperty findByName(String name);
	
	StringProperty findOne(int id);
	
	List<StringProperty> findAll();

	void delete(int id);
	
	StringProperty findForForm(int id);

	StringProperty findOneWithValue(int id);

	Page<StringProperty> findAll(Pageable pageable);

	Page<StringProperty> findAll(Pageable pageable, StringPropertyFormFilter form);
	
	List<StringProperty> allWithValues();
	
	StringProperty findByNameAndCategory(String name, int categoryId);
	
	List<StringPropertyJson> findByCategoryId(int id);
	
}
