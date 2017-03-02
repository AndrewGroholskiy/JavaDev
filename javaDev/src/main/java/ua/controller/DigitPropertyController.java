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
import ua.entity.DigitProperty;
import ua.form.filter.DigitPropertyFormFilter;
import ua.service.CategoryService;
import ua.service.DigitPropertyService;
import ua.service.implementation.editor.CategoryEditor;
import ua.service.implementation.validator.DigitPropertyFormValidator;

@Controller
public class DigitPropertyController {

	@Autowired
	private DigitPropertyService digitPropertyService;
	@Autowired
	private CategoryService categoryService; 
	
	@ModelAttribute("filter")
	public DigitPropertyFormFilter getFilter(){
		return new DigitPropertyFormFilter();
	}
	
	@ModelAttribute("form")
	public DigitProperty getDigitProperty(){
		return new DigitProperty();
	}
	
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
	binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));  
	binder.setValidator(new DigitPropertyFormValidator(digitPropertyService));
	}
	
	@RequestMapping("/admin/digitProperty")
	public String showDigitProperty(Model  model,
					@PageableDefault(5) Pageable pageable,
					@ModelAttribute("filter") DigitPropertyFormFilter form){
		model.addAttribute("page", digitPropertyService.findAll(pageable, form));
		model.addAttribute("category", categoryService.findWithoutChildren());
		return "digitProperty";
		
	}
	
	@RequestMapping("/admin/digitProperty/update/{id}")
	public String update(Model model, 
							@PathVariable("id") int id,
							@PageableDefault(5) Pageable pageable,
							@ModelAttribute("filter") DigitPropertyFormFilter form){
		model.addAttribute("page", digitPropertyService.findAll(pageable, form));
		model.addAttribute("form", digitPropertyService.findForForm(id));
		model.addAttribute("category", categoryService.findWithoutChildren());
		return "digitProperty";
		
	}
	
	
	
	@RequestMapping(value="/admin/digitProperty",method=RequestMethod.POST)
	public String save(@ModelAttribute("form") @Valid DigitProperty digitProperty,
					BindingResult br,
					@PageableDefault(5) Pageable pageable,
					@ModelAttribute(value="filter") DigitPropertyFormFilter form,
					Model model){
		if(br.hasErrors()){
		model.addAttribute("page", digitPropertyService.findAll(pageable, form));
		model.addAttribute("category", categoryService.findWithoutChildren());
		return "digitProperty";
		}
		
		digitPropertyService.save(digitProperty);
		System.out.println("-------------------SAVE-------------------------------------");
		System.out.println(form.getSerch());
		return "redirect:/admin/digitProperty"+getParams(pageable, form);
	}
	
	@RequestMapping("/admin/digitProperty/delete/{id}")
	public String delete(@PathVariable int id,
			@PageableDefault(5) Pageable pageable,
			@ModelAttribute("filter") DigitPropertyFormFilter form,
			Model model){
		if(digitPropertyService.findOneWithValue(id).getValues().isEmpty()) {
			digitPropertyService.delete(id);
			return "redirect:/admin/digitProperty"+getParams(pageable, form);
		}
		else {
			model.addAttribute("page", digitPropertyService.findAll(pageable, form));
			model.addAttribute("errorsDelete", id);
			model.addAttribute("categorys", categoryService.findWithoutChildren());
			return "digitProperty";
			
		}
	}
	
	private String getParams(Pageable pageable, DigitPropertyFormFilter form){
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


