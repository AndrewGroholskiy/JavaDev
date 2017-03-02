package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.PropertyJson;
import ua.entity.Category;
import ua.entity.Filters;
import ua.form.filter.FilterFormFilter;

public interface FilterService {

	void save(String json);
	
	Filters findByName(String name);
	
	Filters findOne(int id);
	
	List<Filters> findAll();

	void delete(int id);
	
	Filters findOneByCategoryIdAndNameWhithValue(Category category, String name);
	
	List<PropertyJson> findStringAndDigitPropertyByCategoryId(int id);
	
	Page<Filters> findAll(Pageable pageable, FilterFormFilter form);
	
	List<Filters> findByCategoryId(Category category);
}
