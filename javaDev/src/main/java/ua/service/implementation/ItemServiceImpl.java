package ua.service.implementation;



import java.io.IOException;
import java.math.BigDecimal;
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

import ua.dto.DigitValueJson;
import ua.dto.ItemFilterByBrend;
import ua.dto.ItemJson;
import ua.dto.ItemJsonMain;
import ua.dto.ItemsJson;
import ua.dto.StringValueJson;
import ua.entity.DigitProperty;
import ua.entity.DigitValue;
import ua.entity.Filters;
import ua.entity.FiltersValue;
import ua.entity.Item;
import ua.entity.StringProperty;
import ua.entity.StringValue;
import ua.form.ItemDigitForm;
import ua.form.ItemForm;
import ua.form.ItemStringForm;
import ua.form.filter.IndexSearchForm;
import ua.form.filter.ItemAdminFormFilter;
import ua.form.filter.ItemFormFilter;
import ua.repository.CategoryRepository;
import ua.repository.DigitPropertyRepository;
import ua.repository.DigitValueRepository;
import ua.repository.FilterRepository;
import ua.repository.FiltersValueRepository;
import ua.repository.ItemRepository;
import ua.repository.StringPropertyRepository;
import ua.repository.StringValueRepository;
import ua.service.FileWriter;
import ua.service.ItemService;
import ua.service.implementation.specification.IndexSearchAdapter;
import ua.service.implementation.specification.ItemAdapter;
import ua.service.implementation.specification.ItemAdminFilterAdapter;


@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private StringPropertyRepository stringPropertyRepository;
	@Autowired
	private StringValueRepository stringValueRepository;
	@Autowired
	private DigitPropertyRepository digitPropertyRepository;
	@Autowired
	private DigitValueRepository  digitValueRepository; 
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FileWriter fileWriter;
	@Autowired
	private FilterRepository filterRepository; 
	@Autowired
	private FiltersValueRepository filtersValueRepository; 
	
	@Transactional
	@Override
	public void saveDigit(ItemDigitForm itemDigitForm) {
		Item item = itemRepository.findOneWithValues(itemDigitForm.getId());
		DigitValue digitValue =new DigitValue();

		List<DigitValue> listDigitValues =item.getDigitValues();
	
		if(itemRepository.findByDigitProperty(itemDigitForm.getId(), itemDigitForm.getDigitProperty().getName()) == null){
		
			digitValue.setValue(BigDecimal.valueOf(Long.valueOf(itemDigitForm.getValueNane())));
			digitValue.setDigitProperty(itemDigitForm.getDigitProperty());
			
			listDigitValues.add(digitValue);
			
			List<Item> items = digitValue.getItems();
			if(items == null) items = new ArrayList<Item>();
			items.add(item);
			item.setId(itemDigitForm.getId());
			item.setDigitValues(listDigitValues);
			digitValue.setItems(items);
			digitValueRepository.save(digitValue);
			
		}else{
			DigitProperty digitProperty = digitPropertyRepository.findOne(itemDigitForm.getDigitPropertyId());
			for (DigitValue digitValues : listDigitValues) {
				if(digitValues.getDigitProperty().getName() == digitProperty.getName()){
					digitValues.setValue(BigDecimal.valueOf(Double.valueOf(itemDigitForm.getValueNane())));
				}
			}
		}	
		
		
		itemRepository.save(item);
	}
	
	@Transactional
	@Override
	public void saveString(ItemStringForm itemStringForm) {
		Item item = itemRepository.findOneWithValues(itemStringForm.getId());
		StringValue stringValues = new StringValue();
		

		List<StringValue> listStringValues = item.getStringValues();
		if(itemRepository.findByStringProperty(itemStringForm.getId(), itemStringForm.getStringProperty().getName()) == null){
	
			stringValues.setName(itemStringForm.getValueNane());
			stringValues.setStringProperty(itemStringForm.getStringProperty());
			listStringValues.add(stringValues);
			List<Item> items = stringValues.getItems();
			
			if(items == null){ items = new ArrayList<Item>();}
			
			items.add(item);
			item.setId(itemStringForm.getId());
			item.setStringValues(listStringValues);
			stringValues.setItems(items);
			stringValueRepository.save(stringValues);
			
		}else{
			
			StringProperty stringProperty = stringPropertyRepository.findOne(itemStringForm.getStringPropertyId());
			for (StringValue stringValue : listStringValues) {
				if(stringValue.getStringProperty().getName() == stringProperty.getName())
					stringValue.setName(itemStringForm.getValueNane());
			}
		}

		itemRepository.save(item);
	}

	@Override
	public Item findById(int id) {
		return itemRepository.findOne(id);
	}

	@Override
	public ItemsJson findAll(Pageable pageable, ItemAdminFormFilter form) {
		
		
		
		Page<Item> itemsPage = FindAll(pageable, form);
		ItemsJson itemsJson = new ItemsJson();
		List<ItemJson> jsons = new ArrayList<ItemJson>();
		List<Item> items = itemsPage.getContent();
		
		
		itemsJson.setPageSize(pageable.getPageSize());
		itemsJson.setCarentPage(pageable.getPageNumber());
		itemsJson.setPageNamber(itemsPage.getTotalPages());
		System.out.println("-----------------------------------------------");
		System.out.println(pageable.getPageNumber());
		System.out.println(itemsPage.getTotalPages());
		System.out.println("-----------------------------------------------");
		for (Item item : items) {
			ItemJson json = new ItemJson();
			json.setItemId(item.getId());
			json.setBrend(item.getBrend());
			json.setModel(item.getModel());
			json.setPrice(item.getPrice());
			json.setPath(item.getPath());
			
			List<StringValueJson> stringValueJson = new ArrayList<StringValueJson>();
			for (StringValue stringValue : item.getStringValues()) {
				StringValueJson strJson = new StringValueJson();
				strJson.setName(stringValue.getName());
				strJson.setStringProperty(stringValue.getStringProperty().getName());
				stringValueJson.add(strJson);
			}
			json.setStringValue(stringValueJson);
			
			List<DigitValueJson> digitValueJson = new ArrayList<DigitValueJson>();
			for (DigitValue digitValue : item.getDigitValues()) {
				DigitValueJson digitJson = new DigitValueJson();
				digitJson.setValue(Integer.valueOf(digitValue.getValue().toBigInteger().toString()));
				digitJson.setDigitProperty(digitValue.getDigitProperty().getName());
				digitValueJson.add(digitJson);
			}
			json.setDigitValue(digitValueJson);
			
			
			jsons.add(json);
		}
		
 		itemsJson.setItems(jsons);
		
		return itemsJson;
	}
	@Override
	public void save(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ItemJson itemJson = mapper.readValue(json, ItemJson.class);
			System.out.println(itemJson);
			Item item = itemRepository.findOne(itemJson.getItemId());
			if(item == null) item = new Item();
			item.setId(itemJson.getItemId());
			item.setBrend(itemJson.getBrend());
			item.setModel(itemJson.getModel());
			item.setPrice(itemJson.getPrice());
			item.setCategoryId(itemJson.getCategoryId());
			if(itemJson.getPath().length() < 3){				
				item.setPath(item.getPath());
			}else{
				item.setPath(itemJson.getPath());
			}
			
			
			List<StringValue> stringValues = item.getStringValues();
			if(stringValues == null) stringValues = new ArrayList<StringValue>();
			
			
			for (StringValueJson stringJson : itemJson.getStringValue()) {
				StringValue stringValue = stringValueRepository.findById(stringJson.getId());
				
				if(stringJson.getId() == 0) {
					stringValue = new StringValue();
					
					StringProperty stringProperty = stringPropertyRepository.findByNameAndCategory(stringJson.getStringProperty(), itemJson.getCategoryId());
					
					Filters filters = filterRepository.findOneWhithValues(stringProperty.getName(),categoryRepository.findOne(itemJson.getCategoryId()));
					if(filters != null){
						List<FiltersValue> listFiltersValue =filters.getFiltersValue();

						if(listFiltersValue.isEmpty()) {
							listFiltersValue = new ArrayList<FiltersValue>();	
						}
						if(filtersValueRepository.findByValueAndFiltersValue(stringJson.getName(), filters) == null){
							
							FiltersValue filtersValue = new FiltersValue();
							filtersValue.setFiltersValue(filters);
							filtersValue.setValue(stringJson.getName());
							listFiltersValue.add(filtersValue);
							filters.setFiltersValue(listFiltersValue);
							
							filtersValueRepository.save(filtersValue);
							
							filterRepository.save(filters);
						}
					}
					
					stringValue.setName(stringJson.getName());
					stringValue.setStringProperty(stringProperty);
					stringValues.add(stringValue);
				} else{
					
					stringValue.setName(stringJson.getName());
				}
				stringValueRepository.save(stringValue);
			}
		
			List<DigitValue> digitValues = item.getDigitValues();
			if(digitValues == null ) digitValues = new ArrayList<DigitValue>();
			
			for (DigitValueJson digitJson : itemJson.getDigitValue()) {
				DigitValue digitValue = digitValueRepository.findOne(digitJson.getId());
				
				if(digitJson.getId() == 0){
					
					digitValue = new DigitValue();
				
					DigitProperty digitProperty = digitPropertyRepository.findByNameAndCategory(digitJson.getDigitProperty(),categoryRepository.findOne(itemJson.getCategoryId()).getName());
					
					Filters filters = filterRepository.findOneWhithValues(digitProperty.getName(),categoryRepository.findOne(itemJson.getCategoryId()));
					if(filters != null){
						List<FiltersValue> listFiltersValue =filters.getFiltersValue();

						if(listFiltersValue.isEmpty()) {
							listFiltersValue = new ArrayList<FiltersValue>();	
						}
						if(filtersValueRepository.findByValueAndFiltersValue(String.valueOf(digitJson.getValue()), filters) == null){
							
							FiltersValue filtersValue = new FiltersValue();
							filtersValue.setFiltersValue(filters);
							filtersValue.setValue(String.valueOf(digitJson.getValue()));
							listFiltersValue.add(filtersValue);
							filters.setFiltersValue(listFiltersValue);
							
							filtersValueRepository.save(filtersValue);
							
							filterRepository.save(filters);
						}
					}
					
					digitValue.setValue(BigDecimal.valueOf(Double.valueOf(digitJson.getValue())));
					digitValue.setDigitProperty(digitProperty);
					digitValues.add(digitValue);					
				}else{
					digitValue.setValue(BigDecimal.valueOf(Double.valueOf(digitJson.getValue())));
				}
				digitValueRepository.save(digitValue);
			}
			item.setStringValues(stringValues);
			item.setDigitValues(digitValues);	
		
		
			itemRepository.save(item);
			
		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ItemForm findForForm(int id) {
		Item item = itemRepository.findOne(id);
		ItemForm itemForm = new ItemForm();	
		itemForm.setId(item.getId());
		itemForm.setBrend(item.getBrend());
		itemForm.setModel(item.getModel());
		itemForm.setCategoryId(item.getCategoryId());
		itemForm.setPrice(String.valueOf(item.getPrice()));
		return itemForm;
	}



	@Override
	public ItemStringForm findForStringForm(int id, int stringValueId) {
		Item item = itemRepository.findOneWithValues(id);
		ItemStringForm itemStringForm = new ItemStringForm();
		itemStringForm.setId(id);
		itemStringForm.setCategoryId(item.getCategoryId());
		itemStringForm.setStringPropertyId(stringValueRepository.findById(stringValueId).getStringProperty().getId());
		itemStringForm.setValueNane(stringValueRepository.findOne(stringValueId).getName());
		itemStringForm.setStringProperty(stringValueRepository.findOne(stringValueId).getStringProperty());
		itemStringForm.setStringValues(item.getStringValues());
		return itemStringForm;
	}

	@Override
	public ItemDigitForm findForDigitForm(int id, int digitValueId) {
		Item item = itemRepository.findOneWithValues(id);
		ItemDigitForm itemDigitForm = new ItemDigitForm();
		itemDigitForm.setId(item.getId());
		itemDigitForm.setDigitPropertyId(digitValueRepository.findOne(digitValueId).getDigitProperty().getId());		
		itemDigitForm.setValueNane(digitValueRepository.findOne(digitValueId).getValue().toString());
		itemDigitForm.setDigitProperty(digitPropertyRepository.findOne(id));
		itemDigitForm.setDigitValues(item.getDigitValues());
		return itemDigitForm;
	}

	@Override
	public Item findOneWithValue(int id) {
		return itemRepository.findOneWithValues(id);
	}

	@Override
	public Item findByStringProperty(int id, String stringProperty) {
		return itemRepository.findByStringProperty(id, stringProperty);
	}

	@Override
	public Item findByDigitProperty(int id, String digitProperty) {
		return itemRepository.findByDigitProperty(id, digitProperty);
	}

	@Override
	public ItemJson findByIdJson(int id) {
		Item item = itemRepository.findOne(id);
		ItemJson json = new ItemJson();
		json.setItemId(item.getId());
		json.setBrend(item.getBrend());
		json.setModel(item.getModel());
		json.setPrice(item.getPrice());
		json.setCategoryId(item.getCategoryId());
		json.setPath(item.getPath());
		
		List<StringValueJson> stringValueJson = new ArrayList<StringValueJson>();
		for (StringValue stringValue : item.getStringValues()) {
			StringValueJson strJson = new StringValueJson();
			strJson.setName(stringValue.getName());
			strJson.setStringProperty(stringValue.getStringProperty().getName());
			strJson.setId(stringValue.getId());
			stringValueJson.add(strJson);
		}
		json.setStringValue(stringValueJson);
		
		List<DigitValueJson> digitValueJson = new ArrayList<DigitValueJson>();
		for (DigitValue digitValue : item.getDigitValues()) {
			DigitValueJson digitJson = new DigitValueJson();
			digitJson.setValue(Integer.valueOf(digitValue.getValue().toBigInteger().toString()));
			digitJson.setDigitProperty(digitValue.getDigitProperty().getName());
			digitJson.setId(digitValue.getId());
			digitValueJson.add(digitJson);
		}
		json.setDigitValue(digitValueJson);
		
		return json;
	}

	@Override
	public void delete(int id) {
		itemRepository.delete(id);	
	}

	@Override
	public List<ItemJsonMain> findAllByCategoryId(int id) {
		List<Item> items = itemRepository.findByCategoryId(id);
		List<ItemJsonMain> jsons = new ArrayList<ItemJsonMain>();
		
		for (Item item : items) {
			ItemJsonMain json = new ItemJsonMain();
			json.setBrend(item.getBrend());
			json.setImage(item.getPath());
			json.setItemId(item.getId());
			json.setModel(item.getModel());
			json.setPrice(item.getPrice());
			jsons.add(json);
		}
		return jsons;
	}

	@Override
	public Page<Item> FindAll(Pageable pageable,
			ItemFormFilter form) {
		return itemRepository.findAll(new ItemAdapter(form), pageable);
	}

	@Override
	public void addReviews(int id) {
		Item item = itemRepository.findOne(id);
		int reviews = item.getReviews();
		item.setReviews(reviews + 1);
		itemRepository.save(item);
	}

	@Override
	public List<ItemFilterByBrend> findByCategoryId(int id) {
		List<Item> list = itemRepository.findByCategoryId(id);
		List<ItemFilterByBrend> listNew = new ArrayList<ItemFilterByBrend>();
		
		for (Item item : list){
			ItemFilterByBrend newBrend = new ItemFilterByBrend();
			newBrend.setBrend(item.getBrend());
			if(listNew.isEmpty()){
				listNew.add(newBrend);
			}else{
				Boolean conteins = false; 
				for (ItemFilterByBrend itemFilterByBrend : listNew) {
					if(itemFilterByBrend.getBrend().equalsIgnoreCase(item.getBrend())){
						conteins = true;
					}
				}
				if(!conteins){
					listNew.add(newBrend);
				}
			}
		}
		return listNew;
	}

	@Override
	public Page<Item> FindAll(Pageable pageable, ItemAdminFormFilter form) {
		return itemRepository.findAll(new ItemAdminFilterAdapter(form), pageable);
	}

	@Override
	public Page<Item> FindAll(Pageable pageable, IndexSearchForm form) {
		return itemRepository.findAll(new IndexSearchAdapter(form), pageable);
	}
}