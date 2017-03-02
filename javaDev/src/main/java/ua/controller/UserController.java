package ua.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.form.UserForm;
import ua.service.UserServise;
import ua.service.implementation.MailSender;
import ua.service.implementation.validator.RegistrationFormValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserServise userService;

	@Autowired
	private MailSender mailSender;
	
	@ModelAttribute("user")
	public UserForm getForm(){
		return new UserForm();
	}
	
	
	@InitBinder("user")
	protected void initBinder(WebDataBinder binder){
	binder.setValidator(new RegistrationFormValidator(userService));
	}  
	
	@RequestMapping("/registration")
	public String register(){
		return "registration";
	}

	@RequestMapping("/afterRegistration")
	public String afterRegister(){
		return "afterRegistration";
	}
	
	@RequestMapping("/confirm/{UUID}/{id}")
	public String login(@PathVariable("UUID") String uuid, @PathVariable("id") int id, @ModelAttribute("logForm") UserForm userForm){
		if(userService.findById(id).getUuid().equals(userService.findByUuid(uuid).getUuid())) {
			userService.confirmEmail(id);
			return "redirect:/login";
		}
		return "registration";
	}

	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String save(@ModelAttribute("user") @Valid UserForm userForm,
						BindingResult br){
		
		if(br.hasErrors()){
			return "registration";
		}
		userService.save(userForm);	
		return "redirect:afterRegistration";
	}
	
	
	
	
}