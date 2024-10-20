package fr.diginamic.hello.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{

	
	UserAccount findByUsername(String username); 
}
