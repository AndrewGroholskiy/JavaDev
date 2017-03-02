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









import ua.entity.StringProperty;
import ua.form.StringValueForm;
import ua.form.filter.StringValueFormFilter;
import ua.service.StringPropertyService;
import ua.service.StringValueService;
import ua.service.implementation.editor.StringPropertyEditor;
import ua.service.implementation.validator.StringValueFormValidator;


@Controller
public class StringValueController {

	@Autowired
	private StringValueService stringValueService;
	@Autowired
	private StringPropertyService stringPropertyService; 
	
	@ModelAttribute("stringValueForm")
	public StringValueForm getStringValueForm(){
		return new StringValueForm();
	}
	
	@ModelAttribute("filter")
	public StringValueFormFilter getFilter(){
		return new StringValueFormFilter();
	}
	
	@InitBinder("stringValueForm")
	protected void InitBinder(WebDataBinder binder){
		binder.registerCustomEditor(StringProperty.class, new StringPropertyEditor(stringPropertyService));
		binder.setValidator(new StringValueFormValidator());
	}
	
	
	@RequestMapping("/admin/stringValue")
	public String showStringValue(Model model,
					@PageableDefault(5) Pageable pageable,
					@ModelAttribute("filter") StringValueFormFilter form){
		model.addAttribute("page", stringValueService.findAll(pageable, form));
		model.addAttribute("stingPropertys", stringPropertyService.findAll());
		return "stringValue";
	}
	
	@RequestMapping(value="/admin/stringValue",method=RequestMethod.POST)
	public String save(@ModelAttribute("stringValueForm") @Valid StringValueForm stringValueForm,
						BindingResult br,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") StringValueFormFilter form,
						Model model){
		if(br.hasErrors()){
			model.addAttribute("page", stringValueService.findAll(pageable, form));
			model.addAttribute("stingPropertys", stringPropertyService.findAll());
			return "stringValue";
		}
		stringValueService.save(stringValueForm);
		return "redirect:/admin/stringValue"+getParams(pageable, form);
	}
	
	@RequestMapping( value="admin/stringValue/update/{id}")
	public String update(Model model, @PathVariable int id,
					@PageableDefault(5) Pageable pageable,
					@ModelAttribute("filter") StringValueFormFilter form){
		model.addAttribute("stringValueForm", stringValueService.findForForm(id));
		model.addAttribute("page", stringValueService.findAll(pageable, form));
		model.addAttribute("stingPropertys", stringPropertyService.findAll());
		return "stringValue";
	}
	
	
	private String getParams(Pageable pageable, StringValueFormFilter form){
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
