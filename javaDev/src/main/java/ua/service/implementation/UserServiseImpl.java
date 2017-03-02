package ua.service.implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.entity.Item;
import ua.entity.Role;
import ua.entity.User;
import ua.form.UserCheckInfoBeforConfirm;
import ua.form.UserForm;
import ua.repository.UserRepository;
import ua.service.ItemService;
import ua.service.UserServise;


@Service("userDetailsService")
public class UserServiseImpl implements UserServise, UserDetailsService{

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public User findByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public User findById(int id) {
		return repository.findOne(id);
	}

	@PostConstruct
	public void saveAdmin(){
		User user = repository.findOne(1);
		if(user==null){
			user = new User();
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("admin"));
			user.setLogin("admin");
			user.setId(1);
			user.setMailConfirm(true);
			repository.save(user);
		}
	}
	
	
	
	
	@Override
	public void save(UserForm userForm) {
		User user = repository.findOne(userForm.getId());
		if(user == null) user= new User();
		
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(userForm.getPassword()));
		
		user.setName(userForm.getName());
		user.setLastName(userForm.getLastName());
		user.setAddres(userForm.getAddres());
		user.setPhoneNamber(userForm.getPhoneNamber());
		user.setLogin(userForm.getMail());
		user.setMail(userForm.getMail());
		user.setMailConfirm(userForm.getMailConfirm());		
		user.setPassword(encoder.encode(userForm.getPassword()));
		
		repository.saveAndFlush(user);
		
		String urlPath = "Ви зареєструвались на моєму сайті localhost8080."+"\n"
						+"Login: " + user.getLogin()+"\n"+
						"email: " + user.getMail()+"\n"+
						"Password: "+userForm.getPassword()+"\n"+	
						"Для підтвердження активації перейдіть по посиланню."+"\n"+
						"http://localhost:8080/confirm/"+user.getUuid() +"/"+ user.getId();
		
		mailSender.sendMail("confirm registration", user.getMail(), urlPath);
	}

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		return repository.findByLogin(login);
	}

	@Override
	public User findByUuid(String uuid) {
		return repository.findByUuid(uuid);
	}

	@Override
	public UserForm findForForm(int id) {
		User user = repository.findOne(id);
		UserForm form = new UserForm();
		
		form.setId(user.getId());
		form.setLogin(user.getLogin());
		form.setMail(user.getMail());
		form.setPassword(user.getPassword());
		form.setRole(user.getRole());
		form.setUuid(user.getUuid());
		
		return form;
	}

	@Override
	public void confirmEmail(int userId) {
		User user = repository.findOne(userId);
		user.setMailConfirm(true);
		repository.save(user);
	}

	@Override
	public UserCheckInfoBeforConfirm findForeForm(int userID) {
		User user = repository.findOne(userID);
		UserCheckInfoBeforConfirm form = new UserCheckInfoBeforConfirm();
		form.setName(user.getName());
		form.setLastName(user.getLastName());
		form.setAddres(user.getAddres());
		form.setPhoneNamber(user.getPhoneNamber());
		form.setMail(user.getMail());
		return form;
	}

	@Override
	public User findByMail(String mail) {
		return repository.findByMail(mail);
	}

	@Override
	public void sendCheckOutMail(UserCheckInfoBeforConfirm form) {
		String urlPath = "Дякуємо за коримтування наштм сервісом localhost8080."+"\n"
				+form.getName() + " " + form.getLastName() + " ви здійснили покупку товару :" + "\n";
		
		String [] ids = form.getIds().split(",");
		String [] quantitys = form.getQuantity().split(",");
 		
		for (int i = 0; i < quantitys.length; i++) {
			int id = Integer.valueOf(ids[i]);
			
			Item item = itemService.findById(id);
			
			String brend = item.getBrend();
			String model = item.getModel();
			float price = item.getPrice();
			int quantity = Integer.valueOf(quantitys[i]);
			
			String items = (i+1) + ". " + brend + " " + model +".  Кількість " + quantity + ", ціна " + (quantity * price) + "\n";
			
			urlPath +=items;
		}
		 urlPath +=" Наш оператор скоро з вами звяжеться за телефоном  +"+form.getPhoneNamber()  +" для підтвердження.";	
		 
		mailSender.sendMail("Підтвердженн покупки ", form.getMail(), urlPath);
	}
	
	
}
