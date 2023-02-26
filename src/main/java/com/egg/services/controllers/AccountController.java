package com.egg.services.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.egg.services.entities.Customer;
import com.egg.services.entities.Person;
import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.CrudService;

public class AccountController<T extends Person> {

	@Autowired
	private CrudService<T> service;

	private final String accountType;

	public AccountController(CrudService<T> service, String accountType) {
		this.accountType = accountType;
		this.service = service;
	}

	// ============= CREATE =============
	public String getForm(ModelMap model) {
		model.put("accountType", accountType);
		model.put("form_name", "sign-up-form");
		return "form-basic.html";
	}

	// CREATE PROFILE
	public String create(@Valid T person, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			addValuesToModelMap(person, "sign-up-form", model);
			model.put("errors", result.getAllErrors());
			return "form-basic.html";
		}
		try {
			service.create(person);
			model.put("success", accountType + " added successfully");
			return "redirect:/";
		} catch (ServicesException se) {
			addValuesToModelMap(person, "sign-up-form", model);
			model.put("error", se.getMessage());
			return "form-basic.html";
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
		model.put("form-name", "modify-profile-form");
		return "form-basic.html";
	}

	// MODIFY PROFILE
	public String modify(@Valid T person, ModelMap model, BindingResult result) {
		if (result.hasErrors()) {
			addValuesToModelMap(person, "modify-profile-form", model);
			model.put("errors", result.getAllErrors());
			return "form-basic.html";
		}
		try {
			service.update(person);
			model.put("success", accountType + " added successfully");
			return "redirect:/";
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			addValuesToModelMap(person, "modify-profile-form", model);
			return "form-basic.html";
		}
	}

	private void addValuesToModelMap(T person, String formName, ModelMap model) {
		model.put("accountType", accountType.toUpperCase());
		model.put(accountType, person);
		model.put("form_name", formName);
	}
}
