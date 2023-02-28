package com.egg.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.egg.services.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/save/**", "/delete/**", "/modify/**") //These routes will change when controllers routes are available
	    .hasRole("ADMIN")
	    .antMatchers("/")
	    .hasAnyRole("ADMIN","SUPPLIER","CUSTOMER")
	    .antMatchers("/supplier/new-supplier").permitAll()
		.antMatchers("/customer/new-customer").permitAll()
	    .and()
	    .formLogin()
	    .loginPage("/login").permitAll()
        .and().csrf()
        .disable();
	}

}
