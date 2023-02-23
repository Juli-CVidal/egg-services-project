package com.egg.services.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}123")
		.roles("ADMIN","CUSTOMER","SUPPLIER")
		.and()
		.withUser("customer")
		.password("{noop}123")
		.roles("CUSTOMER")
		.and()
		.withUser("supplier")
		.password("{noop}123")
		.roles("SUPPLIER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/save/**", "/delete/**", "/modify/**") //These routes will change when controllers routes are available
	    .hasRole("ADMIN")
	    .antMatchers("/")
	    .hasAnyRole("ADMIN","SUPPLIER","CUSTOMER")
	    .and()
	    .formLogin()
	    .loginPage("/login");
	}

}
