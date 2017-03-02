package ua.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;










import ua.dto.DigitValueJson;
import ua.entity.DigitValue;
import ua.form.DigitValueForm;
import ua.form.filter.DigitValueFormFilter;
import ua.repository.DigitPropertyRepository;
import ua.repository.DigitValueRepository;
import ua.repository.ItemRepository;
import ua.service.DigitValueService;
import ua.service.implementation.specification.DigitValueAdapter;

@Service
public class DigitValueServiceImpl implements DigitValueService{

	@Autowired
	private DigitValueRepository digitValueRepository;
	@Autowired
	private DigitPropertyRepository digitPropertyRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	
	
	@Override
	@Transactional
	public void save(DigitValueForm digitValueForm) {
	DigitValue value = new DigitValue();
	value.setId(digitValueForm.getId());
	value.setDigitProperty(digitValueForm.getDigitProperty());
	System.out.println("------------------------------------SAVE"+BigDecimal.valueOf(Double.valueOf(digitValueForm.getValue())));
	value.setValue(BigDecimal.valueOf(Double.valueOf(digitValueForm.getValue())));
	value.setItems(digitValueForm.getItems());
	digitValueRepository.save(value);
    }

	@Override
	public DigitValue findByValue(BigDecimal value) {
		return digitValueRepository.findByValue(value);
	}

	@Override
	public List<DigitValue> findAll() {
		return digitValueRepository.findAll();
	}

	@Override
	public DigitValue findById(int id) {
		return digitValueRepository.findOne(id);
	}

	@Override
	public DigitValueForm findForForm(int id) {
		DigitValue value = digitValueRepository.findWithValue(id);
		DigitValueForm form = new DigitValueForm();
		form.setId(value.getId());
		form.setItems(value.getItems());
		System.out.println("--------------------------------------FORMA"+value.getValue().toString());
		form.setValue(value.getValue().toString());
		form.setDigitProperty(value.getDigitProperty());
		return form;
	}

	@Override
	public Page<DigitValue> findAll(Pageable pageable,
			DigitValueFormFilter form) {
		return digitValueRepository.findAll(new DigitValueAdapter(form), pageable);
	}

	@Override
	public DigitValueJson findByDigitPropertyIdAndItemId(int digitPropertyId,
			int itemId) {
		DigitValue digitValue = digitValueRepository.findByDigitPropertyIdAndItemId(itemId, digitPropertyId);
		
		DigitValueJson json = new DigitValueJson();
		if(digitValue !=null){
			json.setValue(Integer.valueOf(digitValue.getValue().toBigInteger().toString()));
			json.setId(digitValue.getId());
		}
		return json;
	}

	




}
