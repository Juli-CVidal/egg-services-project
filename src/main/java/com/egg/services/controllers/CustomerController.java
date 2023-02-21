package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.egg.services.entities.Customer;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.CustomerService;

public class CustomerController implements CrudController<Customer> {

	
	 @Autowired
	private CustomerService customerService;
	 
	
	  public String getAll(ModelMap model) {
		List<Customer> customers = customerService.getAll();
		model.put("customers", customers);
		return "customers-view";
	}


	@Override
	public String getForm(ModelMap model) {
		
		return "customer-form";
	}


	@Override
	public String create(@Valid Customer customer, ModelMap model) {
		
		 try {
			customerService.create(customer);
			model.put("success", "customer added successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("customer", customer);
			return "customer-form";
		}

		return "redirect:/customer";
	}
		 


	@Override
	public String modify(Integer id, ModelMap model) {
		
		 try {
			Customer customer = customerService.getById(id);
			model.put("customer", customer);
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			return "customers-view";
		}
		return "customer-form";
	}
		 


	@Override
	public String modify(@Valid Customer customer, ModelMap model) {
		try {
			customerService.update(customer);
			model.put("success", "customer modified successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("customer", customer);
			return "customer-form";
		}
		return "redirect:/customer";
	}


	@Override
	public String delete(Integer id, ModelMap model) {
		try {
			customerService.delete(id);
			model.put("success", "customer dissmissed successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/offering";
	}
	 
	
}
