package ua.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import ua.dto.StringPropertyJson;
import ua.entity.StringProperty;
import ua.form.filter.StringPropertyFormFilter;
import ua.repository.CategoryRepository;
import ua.repository.StringPropertyRepository;
import ua.service.StringPropertyService;
import ua.service.implementation.specification.StringPropertyFilterAdapter;


@Service
public class StringPropertyServiceImpl implements StringPropertyService{

	@Autowired
	private StringPropertyRepository stringPropertyRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	


	@Override
	public StringProperty findByName(String name) {
		return stringPropertyRepository.findByName(name);
	}

	@Override
	public List<StringProperty> findAll() {
		return stringPropertyRepository.findAll();
	}
	@Transactional
	@Override
	public void delete(int id) {
		if(stringPropertyRepository.findOne(id).getValues().size()==0)
		stringPropertyRepository.delete(id);
		
	}

	@Override
	public StringProperty findOne(int id) {
		return stringPropertyRepository.findOne(id);
	}

	@Override
	public StringProperty findForForm(int id) {
		return stringPropertyRepository.findWithCategory(id);
	}

	@Transactional
	@Override
	public void save(StringProperty property) {
		stringPropertyRepository.save(property);
	}

	@Override
	public StringProperty findOneWithValue(int id) {
		return stringPropertyRepository.findWithValue(id);
	}

	@Override
	public Page<StringProperty> findAll(Pageable pageable) {
		return stringPropertyRepository.findAll(pageable);
	}

	@Override
	public Page<StringProperty> findAll(Pageable pageable,
			StringPropertyFormFilter form) {
		return stringPropertyRepository.findAll(new StringPropertyFilterAdapter(form), pageable);
	}

	@Override
	public List<StringProperty> allWithValues() {
		return stringPropertyRepository.findAllWhithValues();
	}

	@Override
	public StringProperty findByNameAndCategory(String name, int categoryId) {
	
		return stringPropertyRepository.findByNameAndCategory(name, categoryId);
	}

	@Override
	public List<StringPropertyJson> findByCategoryId(int id) {
		List<StringPropertyJson> jsons = new ArrayList<StringPropertyJson>();
		for (StringProperty stringProperty : stringPropertyRepository.findByCategory(id)) {
			StringPropertyJson json = new StringPropertyJson();
			json.setId(stringProperty.getId());
			json.setName(stringProperty.getName());
			jsons.add(json);
		}
		return jsons;
	}

}
