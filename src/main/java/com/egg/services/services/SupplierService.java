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
public class SupplierService extends PersonService<Supplier> implements CrudService<Supplier> {

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
		if (supplierOpt.isEmpty()) {
			throw new ServicesException("No supplier found");
		}
		return supplierOpt.get();
	}

	// If the field name is unique, change to public Supplier ...
	// And manage the logic the same way
	@Transactional(readOnly = true)
	public List<Supplier> getByName(String name) throws ServicesException {
		if (null == name || name.isBlank()) {
			throw new ServicesException("No valid name entered");
		}
		List<Supplier> suppliers = supplierRepository.findByName(name);
		// Here, if the list has been returned empty, an exception can be thrown
		// (if (suppliers.isEmpty()) {throw new ...})
		return suppliers;
	}

	
	@Transactional(readOnly = true)
	public List<Review> getReviews(Review review, Supplier supplier) throws ServicesException {
		List<Review> reviews = reviewRepository.getFromSupplier(supplier.getId());
		return reviews;
	}

	@Override
	public void create(Supplier supplier) throws ServicesException {
		validateSupplier(supplier);
		supplierRepository.save(supplier);
	}

	@Override
	public void update(Supplier supplier) throws ServicesException {
		if (null == supplier || null == supplier.getId()) {
			throw new ServicesException("Invalid supplier");
		}

		// The create method does not create another entry if two entries
		// share the same id
		// If this happens, the new one will replace the old one
		create(supplier);
	}

	// Soft delete (Only change the state)
	@Override
	public void delete(Integer id) throws ServicesException {
		Supplier supplier = getById(id);
		supplier.setState(false);
		supplierRepository.save(supplier);
	}

	private void validateSupplier(Supplier supplier) throws ServicesException {
		super.validate(supplier);
		if (null == supplier.getOfferings()) {
			throw new ServicesException("The offerings list has not been created");
		}
		if (null == supplier.getReviews()) {
			throw new ServicesException("The reviews list has not been created");
		}
		if (null == supplier.getBiography() || supplier.getBiography().isBlank()) {
			throw new ServicesException("No valid biography entered");
		}
	}
}
