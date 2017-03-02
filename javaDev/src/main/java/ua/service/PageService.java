package ua.service;

import java.util.List;

import ua.dto.ProductPage;

public interface PageService {

	List<ProductPage> findValueByItemId(int id);
	
}
