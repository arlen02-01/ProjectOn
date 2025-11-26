package com.arlen.ProjectOn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.arlen.ProjectOn.domain.user.UserRepository;
import com.arlen.ProjectOn.web.auth.AuthController;
import lombok.RequiredArgsConstructor;

@Configuration @ RequiredArgsConstructor
public class SecurityConfig {

	private final UserRepository userRepository;
	
	@Bean PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> userRepository.findByUsername(username)
				.map(u->User.builder()
						.username(u.getUsername())
						.password(u.getPassword())
						.roles(u.getRole().replace("ROLE_", ""))
						.build())
				.orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
	}
	
	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth->auth
				.requestMatchers("/", "/auth/**", "/login", "/signup", "/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/board/write", "/board/edit/**", "/board/delete/**").authenticated()
				.anyRequest().permitAll()
			)
			.formLogin(login -> login
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/board",true)
				.successHandler((req,res,auth)->{
					req.getSession().setAttribute("LOGIN_USER", auth.getName());
					res.sendRedirect("/board");
				})
				.failureUrl("/login?error")
				.permitAll()
			)
			.logout(logout->logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/board")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
			);
		return http.build();
	}
}
