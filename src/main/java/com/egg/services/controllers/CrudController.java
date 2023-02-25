package com.egg.services.controllers;

import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface CrudController<T> {
	
	@GetMapping("/")
	public String getAll(ModelMap model);

	@GetMapping("/save")
	public String getForm(ModelMap model);

	@PostMapping("/save")
	public String create(@Valid T entity, ModelMap model, BindingResult result);

	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") Integer id, ModelMap model);

	@PostMapping("/modify")
	public String modify(@Valid T entity, ModelMap model, BindingResult result);

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, ModelMap model);
}
