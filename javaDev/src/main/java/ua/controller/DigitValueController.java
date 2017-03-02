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

import ua.entity.DigitProperty;
import ua.form.DigitValueForm;
import ua.form.filter.DigitValueFormFilter;
import ua.service.DigitPropertyService;
import ua.service.DigitValueService;
import ua.service.implementation.editor.DigitPropertyEditor;
import ua.service.implementation.validator.DigitValueFormValidator;

@Controller
public class DigitValueController {

	@Autowired
	private DigitValueService digitValueService;
	@Autowired
	private DigitPropertyService digitPropertyService; 

	@ModelAttribute("digitValueForm")
	public DigitValueForm getDigitValueForm(){
		return new DigitValueForm();
	}
	

	@ModelAttribute("filter")
	public DigitValueFormFilter getFilter(){
		return new DigitValueFormFilter();
	}
	
	
	@InitBinder("digitValueForm")
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(DigitProperty.class, new DigitPropertyEditor(digitPropertyService));
		binder.setValidator(new DigitValueFormValidator());
		
	}
	
	@RequestMapping("/admin/digitValue")
	public String showDigitValue(Model model,
						@PageableDefault(5) Pageable pageable,
						@ModelAttribute("filter") DigitValueFormFilter form){
		model.addAttribute("page", digitValueService.findAll(pageable, form));
		model.addAttribute("digitProperty", digitPropertyService.findAll());
		return "digitValue";
	}
	@RequestMapping("admin/digitValue/update/{id}")
	public String update(Model model, @PathVariable() int id,
							@PageableDefault(5) Pageable pageable,
							@ModelAttribute("filter") DigitValueFormFilter form){
		model.addAttribute("digitValueForm", digitValueService.findForForm(id));
		model.addAttribute("page", digitValueService.findAll(pageable, form));
		model.addAttribute("digitProperty", digitPropertyService.findAll());
		return "digitValue";
	}	
	
	@RequestMapping(value="/admin/digitValue",method=RequestMethod.POST)
	public String save(@ModelAttribute("digitValueForm") @Valid DigitValueForm digitValueForm,
							BindingResult br,
							Model model,
							@PageableDefault(5) Pageable pageable,
							@ModelAttribute("filter") DigitValueFormFilter form){
		if(br.hasErrors()){
			model.addAttribute("digitValues", digitValueService.findAll());
			model.addAttribute("page", digitValueService.findAll(pageable, form));

			model.addAttribute("digitProperty", digitPropertyService.findAll());
			return "digitValue";
		}
		digitValueService.save(digitValueForm);
		return "redirect:/admin/digitValue"+getParams(pageable, form);
	}
	
	private String getParams(Pageable pageable, DigitValueFormFilter form){
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
		buffer.append("&min=");
		buffer.append(form.getMin());
		buffer.append("&max=");
		buffer.append(form.getMax());
		return buffer.toString();
}
}
