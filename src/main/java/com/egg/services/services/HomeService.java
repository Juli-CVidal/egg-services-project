package com.egg.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.services.entities.Customer;
import com.egg.services.entities.Person;
import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.repositories.CustomerRepository;
import com.egg.services.repositories.SupplierRepository;

@Service
public class HomeService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public void signUpSupplier(Supplier supplier) throws ServicesException {
		validate(supplier, "supplier");
		supplierRepository.save(supplier);
	}

	public void signUpCustomer(Customer customer) throws ServicesException {
		validate(customer, "customer");
		customerRepository.save(customer);
	}

	private void validate(Person entity, String type) throws ServicesException {
		if (null == entity || null == entity.getId()) {
			throw new ServicesException("Invalid " + type);
		}
	}
}
