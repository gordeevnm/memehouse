package ru.kek.memehouse.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * gordeevnm@gmail.com
 * 09.11.17
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String SESSION_HEADER_NAME = "X-Auth-Token";
	
	private final UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			  .authorizeRequests()
			  .anyRequest().permitAll()
			  
//			  .and()
//			  .formLogin()
//			  .loginProcessingUrl("/api/login")
//			  .permitAll()
//			  .usernameParameter("username")
//			  .passwordParameter("password")
//			  .successHandler((request, response, authentication) -> {
//				  response.setStatus(Response.SC_OK);
//				  response.setHeader("Content-Type", "application/json;charset=UTF-8");
//				  HttpSession session = request.getSession();
//				  User user = (User) authentication.getPrincipal();
//				  UserDto userDto = UserDto.from(user);
//				  userDto.setAuthToken(session.getId());
//				  response.getWriter().print(ObjectMapperBean.get().writeValueAsString(userDto));
//			  })
//			  .failureHandler((request, response, exception) -> {
//				  response.setStatus(Response.SC_FORBIDDEN);
//				  response.setHeader("Content-Type", "application/json;charset=UTF-8");
//				  response.getWriter().print(
//						 ObjectMapperBean.get().writeValueAsString(
//								new ExceptionDto<>(
//									  "AuthenticationException",
//									  "Неверный логин или пароль",
//									  exception.getMessage()
//								)
//						 )
//				  );
//				  response.getWriter().flush();
//				  response.getWriter().close();
//			  })
//
//			  .and()
//			  .sessionManagement()
//			  .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//
//			  .and()
//			  .logout()
//			  .logoutUrl("/api/logout")
//			  .permitAll()
			  
			  .and()
			  .csrf()
			  .disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			  .userDetailsService(userDetailsService)
			  .passwordEncoder(passwordEncoder());
	}
}
