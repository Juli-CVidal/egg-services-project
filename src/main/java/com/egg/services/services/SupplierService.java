package com.egg.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.repositories.SupplierRepository;

@Service
public class SupplierService extends PersonService<Supplier>
implements CrudService<Supplier>{

	@Autowired 
	private SupplierRepository supplierRepository;
	
	@Override
	@Transactional(readOnly = true)
	//ReadOnly indicates that the query will not make changes to the database.
	public List<Supplier> getAll(){
		return supplierRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Supplier getById(Integer id) throws ServicesException {
		//Optional is our way of checking if what we have requested is in the database.
		Optional<Supplier> supplierOpt = supplierRepository.findById(id);
		//It is like a box, if it is empty the object has not been found
		if (supplierOpt.isEmpty()) {
			throw new ServicesException("No supplier found");	
		} 
		//otherwise, what we are looking for is inside
		return supplierOpt.get();
	}
	
	@Override
	@Transactional(readOnly = true)
	//If the field name is unique, change to public Supplier ...
	//And manage the logic the same way
	public List<Supplier> getByName(String name) throws ServicesException{
		if (null == name || name.isBlank()) {
			throw new ServicesException("No valid name entered");
		}		
		List<Supplier> suppliers = supplierRepository.findByName(name);
		//Here, if the list has been returned empty, an exception can be thrown
		//(if (suppliers.isEmpty()) {throw new ...})
		return suppliers;
	}
	
	
	@Override
	@Transactional
	//This method can also be used to update a supplier
	//A name change might be a good option
	public void create(Supplier supplier) throws ServicesException{
		validateSupplier(supplier);
		supplierRepository.save(supplier);
	}
	
	
	@Override
	@Transactional
	public void update(Supplier supplier) throws ServicesException{
		if (null == supplier || null == supplier.getId()) {
			throw new ServicesException("Invalid supplier");
		}
		
		//The create method does not create another entry if two entries share the same id
		//If this happens, the new one will replace the old one
		create(supplier);
	}
	
	
	@Override
	@Transactional 
	//Soft delete (Only change the state)
	public void delete(Integer id) throws ServicesException{
		Supplier supplier = getById(id);
		supplier.setState(false);
		supplierRepository.save(supplier);
	}
	
	private void validateSupplier(Supplier supplier) throws ServicesException{
		//Inherited attributes are validated
		super.validate(supplier);
		//And then we continue with the proper attributes
		if (null == supplier.getServices()) {
			throw new ServicesException("The services list has not been created");
		}
		if (null == supplier.getReviews()) {
			throw new ServicesException("The reviews list has not been created");
		}
		if (null == supplier.getScores()) {
			throw new ServicesException("The scores list has not been created");
		}
		
		if (null == supplier.getBiography() || supplier.getBiography().isBlank()) {			
			throw new ServicesException("No valid biography entered");
		}
	}
}
