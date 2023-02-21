package com.egg.services.services;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.egg.services.entities.Offering;
import com.egg.services.entities.Review;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.repositories.ReviewRepository;

public class ReviewService implements CrudService<Review>{

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public void create(Review review) throws ServicesException {
		validateReview(review);
		reviewRepository.save(review);
		
	}

	@Override
	public List<Review> getAll() {
		return reviewRepository.findAll();
	}

	@Override
	public Review getById(Integer id) throws ServicesException {
		Optional<Review> reviewOpt = reviewRepository.findById(id);
		if (reviewOpt.isEmpty()) {
			throw new ServicesException("No Review found");
		}
		return reviewOpt.get();
	}

	@Override
	public void update(Review review) throws ServicesException {
		if (null == review || null == review.getId()) {
			throw new ServicesException("Invalid review");
		}
		create(review);
		
	}

	@Override
	public void delete(Integer id) throws ServicesException {
		Review review = getById(id);
		reviewRepository.delete(review);
		
	}
	
	private void validateReview(Review review) throws ServicesException {

		if (null == review.getContent() || review.getContent().isEmpty()) {
			throw new ServicesException("No valid review");
		}


	}

}
