package ua.service.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ua.dto.FilterJson;
import ua.dto.PropertyJson;
import ua.entity.Category;
import ua.entity.DigitProperty;
import ua.entity.Filters;
import ua.entity.StringProperty;
import ua.form.filter.FilterFormFilter;
import ua.repository.CategoryRepository;
import ua.repository.FilterRepository;
import ua.service.FilterService;
import ua.service.implementation.specification.FilterFilterAdapter;

@Service
public class FilterServiceImplementation implements FilterService {

	@Autowired
	private FilterRepository filterRepository; 
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Override
	public void save(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			FilterJson filterJson = mapper.readValue(json, FilterJson.class);
			Filters filters = new Filters();
			filters.setCategory(categoryRepository.findOne(filterJson.getCategoryId()));
			filters.setName(filterJson.getName());
			filterRepository.save(filters);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Filters findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filters findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filters> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Filters> findAll(Pageable pageable, FilterFormFilter form) {
		return filterRepository.findAll(new FilterFilterAdapter(form), pageable);
	}

	@Transactional
	@Override
	public List<PropertyJson> findStringAndDigitPropertyByCategoryId(int id) {
		List<PropertyJson> propertJson = new ArrayList<PropertyJson>();
		List<DigitProperty> digitProperty = categoryRepository.findWithDigitProperty(id).getDigitProperties();
		List<StringProperty> stringProperty = categoryRepository.findWithStringProperty(id).getStringProperties();


		for (StringProperty stringProp : stringProperty) {
			PropertyJson json = new PropertyJson();
			json.setName(stringProp.getName());
			propertJson.add(json);
		}
		
		for (DigitProperty digitProp : digitProperty) {
			PropertyJson json = new PropertyJson();
			json.setName(digitProp.getName());
			propertJson.add(json);
		}
		
		return propertJson;
	}

	@Override
	public List<Filters> findByCategoryId(Category category) {
		return filterRepository.findByCategoryId(category);
	}

	@Override
	public Filters findOneByCategoryIdAndNameWhithValue(Category category,
			String name) {
		return filterRepository.findOneWhithValues(name, category);
	}

}
