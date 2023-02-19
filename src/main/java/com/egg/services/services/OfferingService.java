package com.egg.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.egg.services.entities.Offering;
import com.egg.services.exceptions.ServicesException;

import org.springframework.stereotype.Service;

import com.egg.services.repositories.OfferingRepository;

@Service
public class OfferingService implements CrudService<Offering> {

	@Autowired
	private OfferingRepository offeringRepository;

	@Override
	public List<Offering> getAll() {
		return offeringRepository.findAll();
	}

	
	@Override
	public Offering getById(Integer id) throws ServicesException {
		Optional<Offering> offeringOpt = offeringRepository.findById(id);
		if (offeringOpt.isEmpty()) {
			throw new ServicesException("No offering found");
		}
		return offeringOpt.get();
	}


	@Override
	public void create(Offering offering) throws ServicesException {
		validateOffering(offering);
		offeringRepository.save(offering);

	}

	
	@Override
	public void update(Offering offering) throws ServicesException {
		if (null == offering || null == offering.getId()) {
			throw new ServicesException("Invalid offering");
		}
		create(offering);

	}

	
	@Override
	public void delete(Integer id) throws ServicesException {
		Offering offering = getById(id);
		offering.setState(false);
		offeringRepository.save(offering);
	}

	private void validateOffering(Offering offering) throws ServicesException {

		if (null == offering.getServiceType() || offering.getServiceType().isEmpty()) {
			throw new ServicesException("No valid service type");
		}

		if (null == offering.getDescription() || offering.getDescription().isEmpty()) {
			throw new ServicesException("No valid description");
		}

	}
}
