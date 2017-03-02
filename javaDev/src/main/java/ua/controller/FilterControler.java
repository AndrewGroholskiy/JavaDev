package ua.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.dto.CategoryJson;
import ua.dto.PropertyJson;
import ua.entity.Category;
import ua.entity.Filters;
import ua.form.filter.FilterFormFilter;
import ua.service.CategoryService;
import ua.service.FilterService;
import ua.service.implementation.editor.CategoryEditor;



@Controller
public class FilterControler {

	
	
	@Autowired
	private FilterService filtersService; 
	@Autowired 
	private CategoryService categoryService;
	
	@ModelAttribute("form")
	public Filters getFilters(){
		return new Filters();
	}
	
	@ModelAttribute("filter")
	public FilterFormFilter getFilter(){
		return new FilterFormFilter();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));  
	}  
	
	
	@RequestMapping("/admin/filter")
	public String showStringProperty(Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute(value="filter") FilterFormFilter form){
		model.addAttribute("category", categoryService.findWithoutChildren());
		
		
		model.addAttribute("page", filtersService.findAll(pageable, form));
		return "filter";
	} 
	@ResponseBody
	@RequestMapping(value="/admin/filter/category", method=RequestMethod.GET)
	public List<CategoryJson> getCategory(){
		return categoryService.findAllCategoryJson();
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/filter/stringAndDigitProperty/{id}", method=RequestMethod.GET)
	public List<PropertyJson> getProperty(@PathVariable int id){
		return filtersService.findStringAndDigitPropertyByCategoryId(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/filter/", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HttpStatus save(@RequestBody  String json){
		filtersService.save(json);
		return HttpStatus.OK;
	}
}
