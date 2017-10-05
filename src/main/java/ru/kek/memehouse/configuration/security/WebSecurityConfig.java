package ru.kek.memehouse.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public Filter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter(authenticationManagerBean());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() {
		return new TokenAuthenticationManager(userDetailsService());
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilter(tokenAuthenticationFilter())

				.authorizeRequests()
				.antMatchers("/api/auth/login").permitAll()
				.antMatchers("/api/**").authenticated()
				.anyRequest().permitAll()
				.and().cors();
	}
}
