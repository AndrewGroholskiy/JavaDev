package ua.service.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;










import ua.dto.StringValueJson;
import ua.entity.StringProperty;
import ua.entity.StringValue;
import ua.form.StringValueForm;
import ua.form.filter.StringValueFormFilter;
import ua.repository.ItemRepository;
import ua.repository.StringPropertyRepository;
import ua.repository.StringValueRepository;
import ua.service.StringValueService;
import ua.service.implementation.specification.StringValueAdapter;
@Service
public class StringValueServiceImpl implements StringValueService{

	@Autowired
	private StringValueRepository stringValueRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired 	
	private StringPropertyRepository stringPropertyRepository;


	
	@Override
	@Transactional
	public void save(StringValueForm stringValueForm) {
		StringValue value = new StringValue();
		value.setId(stringValueForm.getId());
		value.setStringProperty(stringValueForm.getStringProperty());
		value.setName(stringValueForm.getName());
		value.setItems(stringValueForm.getItems());
		stringValueRepository.save(value);
	}
	
	

	@Override
	public StringValue findByName(String name) {
		return stringValueRepository.findByName(name);
	}

	

	@Override
	public List<StringValue> findAll() {
		return stringValueRepository.findAll();
	}

	@Override
	public StringValueForm findForForm(int id) {
		StringValue value = stringValueRepository.findWithProperty(id);
		StringValueForm form = new StringValueForm();
		form.setId(id);
		form.setItems(value.getItems());
		form.setName(value.getName());
		form.setStringProperty(value.getStringProperty());
		return form;
	
	}



	@Override
	public StringValue findOne(int id) {
		return stringValueRepository.findById(id);
	}



	@Override
	public StringValue findByStringProperty(StringProperty stringProperty) {
		return stringValueRepository.findByStringProperty(stringProperty);
	}



	@Override
	public StringValue findOneWithProperty(int id) {
		return stringValueRepository.findWithProperty(id);
	}



	@Override
	public Page<StringValue> findAll(Pageable pageable,
			StringValueFormFilter form) {
		return stringValueRepository.findAll(new StringValueAdapter(form), pageable);
	}



	@Override
	public void delete(int id) {
		stringValueRepository.delete(id);
	}



	@Override
	public StringValueJson findByStringPropertyIdAndItemId(int stringPropertyId,
			int itemId) {
		StringValue stringValue = stringValueRepository.findByStringPropertyIdAndItemId(itemId, stringPropertyId);
		
		StringValueJson json = new StringValueJson();
		if(stringValue !=null){
			json.setName(stringValue.getName());
			json.setId(stringValue.getId());
		}
		return json;
	}




}
