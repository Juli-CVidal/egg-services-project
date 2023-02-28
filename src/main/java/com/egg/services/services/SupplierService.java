package com.egg.services.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.services.entities.Review;
import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.repositories.ReviewRepository;
import com.egg.services.repositories.SupplierRepository;

@Service
public class SupplierService implements CrudService<Supplier> {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public List<Supplier> getAll() {
		return supplierRepository.findAll();
	}
	
	@Override
	public Supplier getById(Integer id) throws ServicesException {
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		return getFromOptional(supplierOpt);
	}

	@Transactional(readOnly = true)
	public Supplier getByName(String name) throws ServicesException {
		if (null == name || name.isBlank()) {
			throw new ServicesException("No valid name entered");
		}
		Supplier supplier= supplierRepository.findByName(name);
		if (null == supplier) {
			throw new ServicesException("No supplier found");
		}
		return supplier;
	}

	
	@Transactional(readOnly = true)
	public List<Review> getReviews(Integer supplierId) throws ServicesException {
		List<Review> reviews = reviewRepository.getFromSupplier(supplierId);
		return reviews;
	}

	@Override
	public void create(Supplier supplier) throws ServicesException {
		supplierRepository.save(supplier);
	}

	@Override
	public void update(Supplier supplier) throws ServicesException {
		create(supplier);
	}

	// Soft delete (Only change the state)
	@Override
	public void delete(Integer id) throws ServicesException {
		Supplier supplier = getById(id);
		supplier.setState(false);
		supplierRepository.save(supplier);
	}


	private Supplier getFromOptional(Optional<Supplier> supplierOpt) throws ServicesException{
		if (supplierOpt.isEmpty()) {
			throw new ServicesException("No supplier found");
		}
		return supplierOpt.get();
	}
}