package ua.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.dto.DigitPropertyJson;
import ua.entity.DigitProperty;
import ua.form.filter.DigitPropertyFormFilter;
import ua.repository.CategoryRepository;
import ua.repository.DigitPropertyRepository;
import ua.service.DigitPropertyService;
import ua.service.implementation.specification.DigitPropertyAdapter;
@Service
public class DigitPropertyServiceImpl implements  DigitPropertyService{

	@Autowired
	private DigitPropertyRepository digitPropertyRepository; 
	@Autowired
	private CategoryRepository categoryRepository; 
	
	
	@Override
	public void save(DigitProperty property) {
		DigitProperty digitProperty = new DigitProperty();
		digitProperty.setCategory(property.getCategory());
		digitProperty.setId(property.getId());
		digitProperty.setName(property.getName());
		digitPropertyRepository.save(digitProperty);
	}
	
	@Override
	public DigitProperty findByName(String name) {
		return digitPropertyRepository.findByName(name);
	}

	@Override
	public List<DigitProperty> findAll() {	
		return digitPropertyRepository.findAll();
	}
	
	@Override
	public void delete(int id) {
		
		digitPropertyRepository.delete(id);
	}

	@Override
	public DigitProperty findOne(int id) {
		
		return digitPropertyRepository.findOne(id);
	}

	@Override
	public DigitProperty findForForm(int id) {
		return digitPropertyRepository.findWithCategory(id);
	}

	@Override
	public DigitProperty findOneWithValue(int id) {
		return digitPropertyRepository.findWithValue(id);
	}

	@Override
	public List<DigitProperty> allWithValues() {
		return digitPropertyRepository.findAllWithValues();
	}

	@Override
	public Page<DigitProperty> findAll(Pageable pageable) {
		return digitPropertyRepository.findAll(pageable);
	}

	@Override
	public Page<DigitProperty> findAll(Pageable pageable,
			DigitPropertyFormFilter form) {
		
		return digitPropertyRepository.findAll(new DigitPropertyAdapter(form), pageable);
	}

	@Override
	public DigitProperty findByNameAndCategory(String name, String categoryName) {
		return digitPropertyRepository.findByNameAndCategory(name, categoryName);
	}

	@Override
	public List<DigitPropertyJson> findByCategoryId(int id) {
		 List<DigitPropertyJson> jsons = new ArrayList<DigitPropertyJson>();
		 for(DigitProperty digitProperty : digitPropertyRepository.findByCategory(id) ) {
			 DigitPropertyJson json = new DigitPropertyJson();
			 json.setId(digitProperty.getId());
			 json.setName(digitProperty.getName());
			 jsons.add(json);
		}
		return jsons;
	}


}
