package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.services.entities.Customer;
import com.egg.services.entities.Review;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AccountController<Customer> 
implements CrudController<Customer> {

	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService service) {
		super(service, "customer");
	}

	// ============= READ =============
	@Override
	public String getAll(ModelMap model) {
		List<Customer> customers = customerService.getAll();
		model.put("customers", customers);
		return "customers-view";
	}

	@PostMapping("/getReviewsCustomer")
	public String getAllReviews(ModelMap model, Customer customer) throws ServicesException {
		List<Review> reviews = customerService.getReviews(customer.getId());
		model.put("reviews", reviews);
		return "customerReviews-view";
	}

	@PostMapping("/saveReviewCustomer")
	public String createReview(ModelMap model, Review review, Customer customer) throws ServicesException {

		try {
			customerService.createReview(review, customer);
			model.put("success", "review added successfully");
		} catch (ServicesException se) {

			model.put("error", se.getMessage());
			model.put("review", review);
			return "review-form";
		}

		return "redirect:/customer";
	}

	// ============= CREATE =============

	@Override
	public String getForm(ModelMap model) {
		return super.getForm(model);
	}

	@Override
	// SIGN UP AS CUSTOMER
	public String create(@Valid Customer customer, ModelMap model, BindingResult result) {
		return super.create(customer, model, result);
	}

	// ============= MODIFY =============

	@Override
	public String modify(Integer id, ModelMap model) {
		return super.modify(id, model);
	}

	@Override
	// MODIFY CUSTOMER PROFILE
	public String modify(@Valid Customer customer, ModelMap model, BindingResult result) {
		return super.modify(customer, model, result);
	}

	// ============= DELETE =============

	@Override
	public String delete(Integer id, ModelMap model) {

		try {
			customerService.delete(id);
			model.put("success", "customer dissmissed successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/customer";
	}
}
