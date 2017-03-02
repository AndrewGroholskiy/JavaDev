package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.StringValueJson;
import ua.entity.StringProperty;
import ua.entity.StringValue;
import ua.form.StringValueForm;
import ua.form.filter.StringValueFormFilter;

public interface StringValueService {

	void save(StringValueForm stringValueForm);
	
	void delete(int id);
	
	StringValue findByName(String name);
	
	StringValue findOne(int id);
	
	StringValueJson findByStringPropertyIdAndItemId(int stringPropertyId, int itemId);
	
	List<StringValue> findAll();
	
	StringValueForm findForForm(int id);
	
	StringValue findByStringProperty(StringProperty stringProperty);
	
	StringValue findOneWithProperty(int id);
	
	Page<StringValue> findAll(Pageable pageable,StringValueFormFilter form);
	
	
}
