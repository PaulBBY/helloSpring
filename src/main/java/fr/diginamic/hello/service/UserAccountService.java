package fr.diginamic.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.model.UserAccount;
import fr.diginamic.hello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	
	@PostConstruct
	public void createUser() {
		UserAccount userAccount = new UserAccount("user", passwordEncoder.encode("password"), "ROLE_USER");
		UserAccount adminAccount = new UserAccount("admin", passwordEncoder.encode("password"), "ROLE_ADMIN");

		userRepository.save(userAccount);
		userRepository.save(adminAccount);
		
	}


}
