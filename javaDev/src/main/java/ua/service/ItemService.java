package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.ItemFilterByBrend;
import ua.dto.ItemJson;
import ua.dto.ItemJsonMain;
import ua.dto.ItemsJson;
import ua.entity.Item;
import ua.form.ItemDigitForm;
import ua.form.ItemForm;
import ua.form.ItemStringForm;
import ua.form.filter.IndexSearchForm;
import ua.form.filter.ItemAdminFormFilter;
import ua.form.filter.ItemFormFilter;

public interface ItemService {
	
	void saveString(ItemStringForm itemStringForm);
	
	void saveDigit(ItemDigitForm itemDigitForm);
	
	void save(String json);
	
	Item findById(int id);
	
	ItemJson findByIdJson(int id);
	
	void delete(int id);
	
	ItemsJson findAll(Pageable pageable, ItemAdminFormFilter form);

	ItemForm findForForm(int id);

	ItemStringForm findForStringForm(int id, int stringValueId);
	
	ItemDigitForm findForDigitForm(int id, int digitValueId);

	Item findOneWithValue(int id);
	
	Item findByStringProperty(int id, String stringProperty);
	
	Item findByDigitProperty(int id, String digitProperty);
	
	List<ItemJsonMain> findAllByCategoryId(int id);
	
	Page<Item> FindAll(Pageable pageable, ItemFormFilter form);
	
	void addReviews(int id);
	
	List<ItemFilterByBrend> findByCategoryId(int id);
	
	Page<Item> FindAll(Pageable pageable, ItemAdminFormFilter form);
	
	Page<Item> FindAll(Pageable pageable, IndexSearchForm form);
	
	
}
