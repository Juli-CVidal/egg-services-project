package com.egg.services.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.services.entities.Customer;
import com.egg.services.entities.Person;
import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.CrudService;

public class AccountController<T extends Person> {

	@Autowired
	private CrudService<T> service;

	private final String accountType;
	private final String formName;

	public AccountController(CrudService<T> service, String accountType) {
		this.accountType = accountType;
		this.service = service;
		this.formName = accountType.equals("customer") ? "new-customer" : "new-supplier";
	}

	// ============= CREATE =============
	public String getForm(ModelMap model) {
		model.put(accountType, accountType.equals("customer") ? new Customer() : new Supplier());
		return formName;
	}

	// CREATE PROFILE
	public String create(@Valid T person, @RequestParam("repeat") String repeat, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			model.put(accountType, person);
			model.put("errors", result.getAllErrors());
			return formName;
		}
		addAdditionalValidations(person, result);
		try {
			service.create(person);
			model.put("success", accountType + " added successfully");
			return "redirect:/login";
		} catch (ServicesException se) {
			model.put(accountType, person);
			model.put("error", se.getMessage());
			return formName;
		}
	}

	// ============= MODIFY =============
	public String modify(@PathVariable Integer id, ModelMap model) {
		try {
			Person person = service.getById(id);
			model.put(accountType, person instanceof Customer ? (Customer) person : (Supplier) person);
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			return accountType + "-view";
		}
		return formName;
	}

	// MODIFY PROFILE
	public String modify(@Valid T person, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			model.put(accountType, person);
			model.put("errors", result.getAllErrors());
			return formName;
		}
		try {
			service.update(person);
			model.put("success", accountType + " added successfully");
			return "redirect:/";
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put(accountType, person);
			return formName;
		}
	}

	
    protected void addAdditionalValidations(T person, BindingResult result) {
    }
}
