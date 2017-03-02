package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.DigitPropertyJson;
import ua.entity.DigitProperty;
import ua.form.filter.DigitPropertyFormFilter;

public interface DigitPropertyService {

	void save(DigitProperty digitProperty);
	
	DigitProperty findByName(String name);
	
	DigitProperty findOne(int id);

	DigitProperty findForForm(int id);
	
	List<DigitProperty> findAll();

	void delete(int id);

	DigitProperty findOneWithValue(int id);
	
	List<DigitProperty> allWithValues();
	
	Page<DigitProperty> findAll(Pageable pageable);

	Page<DigitProperty> findAll(Pageable pageable, DigitPropertyFormFilter form);
	
	DigitProperty findByNameAndCategory(String name, String categoryName);
	
	List<DigitPropertyJson> findByCategoryId(int id);
}
