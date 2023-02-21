package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.egg.services.entities.Review;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.ReviewService;

public class ReviewController implements CrudController<Review> {

	@Autowired
	private ReviewService reviewService;
	
	@Override
	public String getAll(ModelMap model) {
		List<Review> reviews = reviewService.getAll();
		model.put("reviews", reviews);
		return "reviews-view";
	}

	@Override
	public String getForm(ModelMap model) {
		return "review-form";
	}

	@Override
	public String create(@Valid Review review, ModelMap model) {
		try {
			reviewService.create(review);
			model.put("success", "review added successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("review", review);
			return "review-form";
		}

		return "redirect:/review";
	}

	@Override
	public String modify(Integer id, ModelMap model) {
		
		 try {
			Review review = reviewService.getById(id);
			model.put("review", review);
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			return "reviews-view";
		}
		return "review-form";
		 
	}

	@Override
	public String modify(@Valid Review review, ModelMap model) {
		
		 try {
			reviewService.update(review);
			model.put("success", "review modified successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("review", review);
			return "review-form";
		}
		return "redirect:/review";
		 
	}

	@Override
	public String delete(Integer id, ModelMap model) {
		
		 try {
			reviewService.delete(id);
			model.put("success", "review dissmissed successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/review";
	}

}
