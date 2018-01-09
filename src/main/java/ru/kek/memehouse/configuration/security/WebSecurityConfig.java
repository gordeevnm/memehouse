package ru.kek.memehouse.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.web.http.HttpSessionStrategy;
import ru.kek.memehouse.models.Roles;
import ru.kek.memehouse.services.Utils;

import javax.servlet.http.Cookie;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * gordeevnm@gmail.com
 * 09.11.17
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;
	
	@Autowired
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return (request, response, authentication) -> {
			String rememberParam = request.getParameter("remember");
			int rememberTime = 60 * 60;
			if (!isNullOrEmpty(rememberParam))
				rememberTime = 365 * 24 * 60 * 60;
			request.getSession().setMaxInactiveInterval(rememberTime);
			
			request.getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, String.valueOf(Utils.getUserId(authentication)));
			
			// FIXME: 30.12.17 ВРЕМЕННЫЙ КОСТЫЛЬ
			response.addCookie(new Cookie("FL-SESSION", request.getSession().getId()));
		};
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/api/admin/**").hasAuthority(Roles.ROLE_ADMIN.getAuthority())
				.anyRequest().permitAll()
				
				.and()
				.headers()
				
				.and()
				.formLogin()
				.permitAll()
				.loginPage("/login")
				.usernameParameter("phone-mail")
				.passwordParameter("password")
				.successForwardUrl("/profile")
				.successHandler(authenticationSuccessHandler())
				
				.and()
				.logout()
				.logoutSuccessHandler((request, response, authentication) -> {
					Cookie cookie = new Cookie("FL-SESSION", null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					response.sendRedirect("/login");
				})
				.permitAll()
				
				.and()
				.csrf()
				.disable();
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderOrCookieSessionStrategy();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}
}
