package ua.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.DigitValueJson;
import ua.entity.DigitValue;
import ua.form.DigitValueForm;
import ua.form.filter.DigitValueFormFilter;



public interface DigitValueService {

	void save(DigitValueForm digitValueForm);
	
	DigitValue findByValue(BigDecimal value);
	
	List<DigitValue> findAll();
	
	DigitValue findById(int id);
	
	DigitValueForm findForForm(int id);
	 
	Page<DigitValue> findAll(Pageable pageable,DigitValueFormFilter form);
	
	DigitValueJson findByDigitPropertyIdAndItemId(int digitPropertyId, int itemId);
	
	
}
