package com.egg.services.services;

import com.egg.services.entities.Person;
import com.egg.services.exceptions.ServicesException;

public class PersonService<T extends Person> {
	
	//This method is in charge of validating the attributes corresponding to the abstract class
	protected void validate(T person) throws ServicesException{
		if (null == person) {
			throw new ServicesException("The person has not been found");
		}
		if (null == person.getName() || person.getName().isEmpty()) {
			throw new ServicesException("No valid name entered");
		}
		if (null == person.getLastname() || person.getLastname().isEmpty()) {
			throw new ServicesException("No valid lastname entered");
		}
		if (null == person.getPhoneNumber() || person.getPhoneNumber().isEmpty()) {
			throw new ServicesException("No valid phone number entered");
		}
		if (null == person.getMail() || person.getMail().isEmpty()) {
			throw new ServicesException("No valid mail entered");
		}
		if (null == person.getImage() || person.getImage().isEmpty()) {
			throw new ServicesException("No valid image entered");
		}
	}
}
