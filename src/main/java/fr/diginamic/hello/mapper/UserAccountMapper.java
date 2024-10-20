package fr.diginamic.hello.mapper;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import fr.diginamic.hello.model.UserAccount;

public class UserAccountMapper {

	public static UserDetails toUseDetails(UserAccount userAccount) {
		return User.builder().username(userAccount.getUsername()).password(userAccount.getPassword()).authorities(userAccount.getAutorities()).build();
		
	}

}
