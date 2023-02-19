package com.egg.services.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import com.egg.services.exceptions.ServicesException;

public interface CrudService<T> {
	@Transactional
	public void create(T entity) throws ServicesException;

	@Transactional(readOnly = true)
	public List<T> getAll();

	@Transactional(readOnly = true)
	public T getById(Integer id) throws ServicesException;

	@Transactional
	public void update(T entity) throws ServicesException;

	@Transactional
	public void delete(Integer id) throws ServicesException;
}
