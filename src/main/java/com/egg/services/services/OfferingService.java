package com.egg.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.egg.services.entities.Customer;
import com.egg.services.entities.Offering;
import com.egg.services.exceptions.ServicesException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.services.repositories.OfferingRepository;

@Service
public class OfferingService {
	
	@Autowired
	private OfferingRepository offeringR;

	@Transactional(readOnly = true)
	public List<Offering> getAll(){
		return offeringR.findAll();
	}
	
	@Transactional(readOnly = true)
	 public Offering getById(Integer id) throws ServicesException {
		Optional<Offering> offeringOpt = offeringR.findById(id);
		if (offeringOpt.isEmpty()) {
			throw new ServicesException("No offering found");	
		} 
		return offeringOpt.get();
	}
	
	
	  @Transactional
	  public void create (Offering offering) throws ServicesException{
		 validateOffering(offering);
		 offeringR.save(offering);
	 
	  }
	  
	  
	   @Transactional
	 public void update (Offering offering) throws ServicesException{
		  if (null == offering || null == offering.getId()) {
			throw new ServicesException("Invalid offering");
		}
		create(offering);
		  
	 }
	   
	   
	     @Transactional
	 public void delete (Integer id) throws ServicesException{
		 Offering offering = getById(id);
		 offering.setState(false);
		 offeringR.save(offering);
	 }

	     
	       private void validateOffering(Offering offering) throws ServicesException{
	    	   
	    	   if (null == offering.getServiceType() || offering.getServiceType().isEmpty()) {
		   			throw new ServicesException("No valid service type");
		   		}
	    	   
	    	   if (null == offering.getDescription() || offering.getDescription().isEmpty()) {
		   			throw new ServicesException("No valid description");
		   		}
	    	   
	       }
}
