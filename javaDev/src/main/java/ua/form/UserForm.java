package ua.form;


import ua.entity.Role;

public class UserForm {
	private int id;	
	private String name;
	
	private String lastName;
	
	private String phoneNamber;
	
	private String addres;
	
	private String login;
	
	private String mail;
	
	private String password;

	private Role role;
	
	private String uuid;
	
	private Boolean mailConfirm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNamber() {
		return phoneNamber;
	}

	public void setPhoneNamber(String phoneNamber) {
		this.phoneNamber = phoneNamber;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getMailConfirm() {
		return mailConfirm;
	}

	public void setMailConfirm(Boolean mailConfirm) {
		this.mailConfirm = mailConfirm;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	
	
}
