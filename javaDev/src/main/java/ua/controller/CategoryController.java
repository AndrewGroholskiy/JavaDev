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
import ua.form.CategoryForm;
import ua.form.filter.CategoryFormFilter;
import ua.service.CategoryService;
import ua.service.implementation.editor.CategoryEditor;


@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@ModelAttribute("categoryForm")
	public CategoryForm getCategoryForm(){
		return new CategoryForm();
	}
	
	@ModelAttribute("filter")
	public CategoryFormFilter getFilter(){
		return new CategoryFormFilter();
	}
	
	@InitBinder("categoryForm")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
		binder.setValidator(new ua.service.implementation.validator.CategoryValidator(categoryService));
	}
	
	@RequestMapping("/admin/category")
	public String showCategory(Model model,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute(value="filter") CategoryFormFilter form){
		
		model.addAttribute("page", categoryService.findAll(pageable, form));
		model.addAttribute("category", categoryService.findWithoutChildren());
		model.addAttribute("subCategory", categoryService.findAllCategory());
		return "category";
	}
	
	@RequestMapping(value= "/admin/category", method=RequestMethod.POST)
	public String save(
					@ModelAttribute(value="filter") CategoryFormFilter form,
					@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm,
					BindingResult br,
					Model model,
					@PageableDefault(5) Pageable pageable
						){
		if(br.hasErrors()){
			model.addAttribute("page", categoryService.findAll(pageable, form));
			model.addAttribute("subCategory", categoryService.findAllCategory());
			return "category";	
		}
		categoryService.save(categoryForm);
		return "redirect:/admin/category"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/category/update/{id}")
	public String update(
						@ModelAttribute(value="filter") CategoryFormFilter form,
							@PathVariable("id") int id, 
							@PageableDefault(5) Pageable pageable,
							Model model){
		model.addAttribute("page", categoryService.findAll(pageable, form));
		model.addAttribute("categoryForm", categoryService.findForForm(id));
		//model.addAttribute("category", categoryService.findAllMainCategory());
		model.addAttribute("subCategory", categoryService.findAllCategory());
		return "category";
	}
	
	
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(@PathVariable int id,
					Model model,
					@PageableDefault(5) Pageable pageable,
					@ModelAttribute(value="filter")	CategoryFormFilter form){
		if(categoryService.findChilds(id).isEmpty() && categoryService.findWithStringProperty(id).getStringProperties().isEmpty()){
			categoryService.delete(id);			
			
		return "redirect:/admin/category"+getParams(pageable, form);
		}
		else {		
			model.addAttribute("errorsDelete", id);
			model.addAttribute("page", categoryService.findAll(pageable, form));
			model.addAttribute("category", categoryService.findWithoutChildren());
			model.addAttribute("subCategory", categoryService.findAllCategory());
			return "category";
			
		}
	}
	
	private String getParams(Pageable pageable, CategoryFormFilter form){
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
		return buffer.toString();
}
}
