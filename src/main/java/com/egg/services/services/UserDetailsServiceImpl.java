package com.egg.services.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.egg.services.entities.Customer;
import com.egg.services.entities.Supplier;
import com.egg.services.repositories.CustomerRepository;
import com.egg.services.repositories.SupplierRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByName(username);
		if (customer != null) {
			return new User(customer.getName(), customer.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
		}

		Supplier supplier = supplierRepository.findByName(username);
		if (supplier != null) {
			return new User(supplier.getName(), supplier.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_SUPPLIER")));
		}

		throw new UsernameNotFoundException("User not found");
	}
}
