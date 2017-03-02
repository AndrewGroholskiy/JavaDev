package ua.service;

import ua.entity.User;
import ua.form.UserCheckInfoBeforConfirm;
import ua.form.UserForm;

public interface UserServise {

	User findByLogin(String login);
	
	User findById(int id);
	
	User findByUuid(String uuid);
	
	void save(UserForm userForm);
	
	UserForm findForForm(int in);
	
	User findByMail(String mail);
	
	void confirmEmail(int userId);
	
	UserCheckInfoBeforConfirm findForeForm(int userID);
	
	void sendCheckOutMail(UserCheckInfoBeforConfirm form);
}
