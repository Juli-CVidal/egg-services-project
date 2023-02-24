package com.egg.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.services.entities.Customer;
import com.egg.services.entities.Review;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.repositories.CustomerRepository;
import com.egg.services.repositories.ReviewRepository;

@Service
public class CustomerService extends PersonService<Customer> implements CrudService<Customer> {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(Integer id) throws ServicesException {
		Optional<Customer> custommerOpt = customerRepository.findById(id);
		if (custommerOpt.isEmpty()) {
			throw new ServicesException("No customer found");
		}
		return custommerOpt.get();
	}

	@Transactional(readOnly = true)
	public List<Customer> getByName(String name) throws ServicesException {
		if (null == name || name.isBlank()) {
			throw new ServicesException("No valid name entered");
		}
		List<Customer> customers = customerRepository.findByName(name);
		return customers;
	}
	
	
	 @Transactional(readOnly = true)
	public List<Review> getReviews(Review review, Customer customer) throws ServicesException {
		List<Review> reviews = reviewRepository.getFromSupplier(customer.getId());
		return reviews;
	}
	 
	 @Transactional
	 public void createReview(Review review, Customer customer) throws ServicesException{
			validateReview(review);
			
			List<Review> reviews = customer.getReviews();
			reviews.add(review);
			customer.setReviews(reviews);
			customerRepository.save(customer);
	 }

	@Override
	public void create(Customer customer) throws ServicesException {
		validateCustomer(customer);
		customerRepository.save(customer);
	}

	@Override
	public void update(Customer customer) throws ServicesException {
		if (null == customer || null == customer.getId()) {
			throw new ServicesException("Invalid customer");
		}
		create(customer);

	}

	@Override
	public void delete(Integer id) throws ServicesException {
		Customer customer = getById(id);
		customer.setState(false);
		customerRepository.save(customer);
	}

	private void validateCustomer(Customer customer) throws ServicesException {
		super.validate(customer);
		if (null == customer.getDirection() || customer.getDirection().isEmpty()) {
			throw new ServicesException("No valid direction entered");
		}
	}
	
	public void validateReview(Review review) throws ServicesException{

		if (review.getScore() > 5){
			
			throw new ServicesException("No valid score");
		}
		
        if (review.getScore() < 1){
			
			throw new ServicesException("No valid score");
		}
		
		if (null == review.getContent() || review.getContent().isEmpty()) {
			throw new ServicesException("No valid review");
		}
	}
}
