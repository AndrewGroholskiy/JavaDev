package ua.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.dto.ProductPage;
import ua.entity.DigitValue;
import ua.entity.StringValue;
import ua.repository.DigitValueRepository;
import ua.repository.StringValueRepository;
import ua.service.PageService;
@Service
public class PageServiceImpl implements PageService{

	@Autowired
	private StringValueRepository stringValueRepository;
	@Autowired
	private DigitValueRepository digitValueRepository;

	@Override
	public List<ProductPage> findValueByItemId(int id) {
		List<ProductPage> propertys = new ArrayList<ProductPage>();
		List<StringValue> stringValues = stringValueRepository.findValueByItemId(id);
		
		for (StringValue stringValue : stringValues) {
			ProductPage property = new ProductPage();
			property.setName(stringValue.getStringProperty().getName());
			property.setValue(stringValue.getName());
			propertys.add(property);
		}
		
		List<DigitValue> digitValues = digitValueRepository.findValueByItemId(id);
		for (DigitValue digitValue : digitValues) {
			ProductPage property = new ProductPage();
			property.setName(digitValue.getDigitProperty().getName());
			property.setValue(String.valueOf(digitValue.getValue().intValue()));
			propertys.add(property);
		}
		propertys.sort((a,b) -> a.getName().compareTo(b.getName()));
		return propertys;
	}

}
