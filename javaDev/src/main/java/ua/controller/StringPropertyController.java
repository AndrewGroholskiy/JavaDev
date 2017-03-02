package ua.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;









import ua.entity.Category;
import ua.entity.StringProperty;
import ua.form.filter.StringPropertyFormFilter;
import ua.service.CategoryService;
import ua.service.StringPropertyService;
import ua.service.implementation.editor.CategoryEditor;
import ua.service.implementation.validator.StrigPropertyFormValidator;

@Controller
public class StringPropertyController {

	
	
	@Autowired
	private StringPropertyService stringPropertyService; 
	@Autowired 
	private CategoryService categoryService;
	
	@ModelAttribute("form")
	public StringProperty getStringProperty(){
		return new StringProperty();
	}
	
	@ModelAttribute("filter")
	public StringPropertyFormFilter getFilter(){
		return new StringPropertyFormFilter();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));  
	binder.setValidator(new StrigPropertyFormValidator(stringPropertyService));
	
	}  
	
	@RequestMapping(value="/admin/stringProperty/update/{id}")
	public String update(Model model,
					@PathVariable int id,
					@PageableDefault(5) Pageable pageable,
					@ModelAttribute(value="filter") StringPropertyFormFilter form){
		model.addAttribute("form", stringPropertyService.findForForm(id));
		model.addAttribute("category",categoryService.findWithoutChildren());
		model.addAttribute("page", stringPropertyService.findAll(pageable, form));
		
		System.out.println("----------------------update----------------------------");
		System.out.println(form.getCategorys()+"|");
		System.out.println(form.getSerch()+"|");
		System.out.println("------------------------------------------------------");
		return "stringProperty";
	}
	
	
	@RequestMapping("/admin/stringProperty")
	public String showStringProperty(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") StringPropertyFormFilter form
			){
		model.addAttribute("page", stringPropertyService.findAll(pageable, form));		
		model.addAttribute("category", categoryService.findWithoutChildren());
		
		System.out.println("----------------------show----------------------------");
		System.out.println(form.getCategorys()+"|");
		System.out.println(form.getSerch()+"|");
		System.out.println("------------------------------------------------------");
		return "stringProperty";
	} 
	
	@RequestMapping(value="/admin/stringProperty",method=RequestMethod.POST)
	public String save(@ModelAttribute(value="filter") StringPropertyFormFilter form,
			@Valid @ModelAttribute("form")  StringProperty property, 
					BindingResult br,
					Model model,
					@PageableDefault(5) Pageable pageable
					){
		if(br.hasErrors()){
			model.addAttribute("page", stringPropertyService.findAll(pageable,form));
			model.addAttribute("category", categoryService.findWithoutChildren());
			return "stringProperty";
		}
		
		stringPropertyService.save(property);
		
		System.out.println("----------------------save----------------------------");
		System.out.println(form.getCategorys()+"|");
		System.out.println(form.getSerch()+"|");
		System.out.println("------------------------------------------------------");
		return "redirect:/admin/stringProperty"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/stringProperty/delete/{id}")
	public String delete(@PathVariable int id, Model model,@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") StringPropertyFormFilter form){
		if(stringPropertyService.findOneWithValue(id).getValues().isEmpty()){
		stringPropertyService.delete(id);
		return "redirect:/admin/stringProperty"+getParams(pageable, form);
		}
		else{
		model.addAttribute("errorsDelete", id);
		model.addAttribute("page", stringPropertyService.findAll(pageable, form));		
		model.addAttribute("category", categoryService.findWithoutChildren());
		return "stringProperty";
		}
	}
	
	private String getParams(Pageable pageable, StringPropertyFormFilter form){
			StringBuilder buffer = new StringBuilder();
			buffer.append("?page=");
			buffer.append(String.valueOf(pageable.getPageNumber()+1));
			buffer.append("&size=");
			buffer.append(String.valueOf(pageable.getPageSize()));
			if(pageable.getSort()!=null){
				buffer.append("&sort=");
				Sort sort = pageable.getSort();
				sort.forEach((order)->{
					buffer.append(order.getProperty());
					if(order.getDirection()!=Direction.ASC)
					buffer.append(",desc");
				});
			}
			buffer.append("&serch=");
			buffer.append(form.getSerch());
			for(Integer i : form.getCategorys()){
				buffer.append("&categorys=");
				buffer.append(i.toString());
			}
			return buffer.toString();
	}
}
