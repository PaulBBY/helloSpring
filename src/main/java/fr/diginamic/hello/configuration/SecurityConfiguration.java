package fr.diginamic.hello.configuration;

import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.diginamic.hello.mapper.UserAccountMapper;
import fr.diginamic.hello.repository.UserAccountRepository;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	UserAccountRepository userRepository;
	
	@Bean
    UserDetailsService userDetailsService() {
        return username -> UserAccountMapper.toUseDetails(this.userRepository.findByUsername(username));
    }
	
	@Bean
	public PasswordEncoder paswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
              /*  .requestMatchers("/", "/login").permitAll()
                .requestMatchers("/logout").authenticated() 
                .requestMatchers("/town/getAllTowns").authenticated()
                .requestMatchers("/h2-console/**").permitAll() */
                .anyRequest().permitAll() 
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .ignoringRequestMatchers(new AntPathRequestMatcher("/api/**")) 
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
