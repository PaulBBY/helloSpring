package fr.diginamic.hello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserAccount {

	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<GrantedAuthority> autorities = new ArrayList<>();
	
	public UserAccount(String username, String password, String ... autorities) {
		super();
		this.username = username;
		this.password = password;
		this.autorities = Arrays.stream(autorities)
								.map(SimpleGrantedAuthority::new)
								.map(GrantedAuthority.class::cast)
								.toList();
	}
	
	

	public UserAccount() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GrantedAuthority> getAutorities() {
		return autorities;
	}

	public void setAutorities(List<GrantedAuthority> autorities) {
		this.autorities = autorities;
	}
	
	
	
}
