package com.egg.services.controllers;

import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

public interface CrudController<T> {

	public String getAll(ModelMap model);

	public String getForm(ModelMap model);

	public String create(@Valid T entity, ModelMap model);

	public String modify(@PathVariable("id") Integer id, ModelMap model);

	public String modify(@Valid T entity, ModelMap model);

	public String delete(@PathVariable("id") Integer id, ModelMap model);
}
