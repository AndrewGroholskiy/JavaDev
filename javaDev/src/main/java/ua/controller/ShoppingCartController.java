package ua.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import ua.form.UserCheckInfoBeforConfirm;
import ua.service.ItemService;
import ua.service.UserServise;
import ua.service.implementation.MailSender;
import ua.service.implementation.validator.ShopingCartFormValidator;


@Controller
public class ShoppingCartController {
	
	@Autowired
	private UserServise userServise;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private ItemService itemService; 
	
	@ModelAttribute("form")
	public UserCheckInfoBeforConfirm getForm(){
		return new UserCheckInfoBeforConfirm();
	}
	@InitBinder("form")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ShopingCartFormValidator());
	}
	
	@RequestMapping(value = "/cart" , method=RequestMethod.GET)
	public String showCart(){
		return "shoppingCart";
	}
	
	
	@RequestMapping(value = "/checkout" , method=RequestMethod.GET)
	public String checkOut(Model model,
								@ModelAttribute("form") UserCheckInfoBeforConfirm form){	
		if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){
			int id = Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
			model.addAttribute("form", userServise.findForeForm(id));
		}
		
		return "checkout";
	}
	
	@RequestMapping(value = "/checkout" , method=RequestMethod.POST)
	public String checkOutFinal(@ModelAttribute("form") @Valid UserCheckInfoBeforConfirm form,
								BindingResult br){	
		if(br.hasErrors()){
			return "checkout";
		}
		userServise.sendCheckOutMail(form);
		return "redirect:/aftreShopping";
	}
	
	
	@RequestMapping(value = "/aftreShopping")
	public String afterShoping(){	
		return "aftreShopping";
	}

}
