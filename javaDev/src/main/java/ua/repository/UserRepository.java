package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByLogin(String login);
	
	User findByUuid(String uuid);
	
	User findByMail(String mail);
}
