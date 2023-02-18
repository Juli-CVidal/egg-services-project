package com.egg.services.services;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.egg.services.entities.Person;
import com.egg.services.exceptions.ServicesException;

public interface CrudService <T>{
	public void create(T entity) throws ServicesException;
	
	public List<T> getAll();
	
	public T getById(Integer id) throws ServicesException;
	
	//If the field name is unique, change to public T...
	public List<T> getByName(String name) throws ServicesException;
	
	public void update(T entity) throws ServicesException;
	
	public void delete(Integer id) throws ServicesException;
}
