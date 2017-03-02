package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import ua.dto.ItemJson;
import ua.entity.Item;
import ua.service.ItemService;
import ua.service.PageService;
import ua.service.StringPropertyService;
import ua.service.StringValueService;

@Controller
public class ProductContoller {


	@Autowired
	private ItemService itemService;
	
	@Autowired
	private StringPropertyService stringPropertyService;
	
	@Autowired
	private StringValueService stringValueService;
	
	@Autowired
	private PageService pageService; 
	
	@RequestMapping(value="/item/productPage/{id}", method=RequestMethod.GET)
	public String getProduct(@PathVariable("id") int id, Model model){
		Item item = itemService.findById(id);
		itemService.addReviews(id);
		model.addAttribute("itemId", item.getId());
		model.addAttribute("image", item.getPath());
		model.addAttribute("brend", item.getBrend());
		model.addAttribute("model", item.getModel());
		model.addAttribute("price", item.getPrice());
		model.addAttribute("listValue", pageService.findValueByItemId(id));
		return "productPage";
		
	}
	
	@RequestMapping(value="/admin/adminProductPage/{id}", method=RequestMethod.GET)
	public String getProductAdmin(@PathVariable("id") int id, Model model){
		model.addAttribute("itemId", id);
		return "adminProductPage";	
	}
	
	@ResponseBody
	@RequestMapping(value="adminProductPageGetOne/{id}", method=RequestMethod.GET)
	public ItemJson getOneProductAdmin(@PathVariable("id") int id){
		ItemJson item = itemService.findByIdJson(id);
		return item;	
	}
}
