package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.services.entities.Review;
import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends AccountController<Supplier>
implements CrudController<Supplier> {

	@Autowired
	private SupplierService supplierService;
	
	public SupplierController(SupplierService service) {
		super(service,"supplier");
	}

	// ============= READ =============
	@Override
	public String getAll(ModelMap model) {
		List<Supplier> suppliers = supplierService.getAll();
		model.put("suppliers", suppliers);
		return "suppliers-view";
	}

	@GetMapping("/getReviewsSupplier")
	public String getAllReviews(Supplier supplier, ModelMap model) throws ServicesException {
		List<Review> reviews = supplierService.getReviews(supplier.getId());
		model.put("reviews", reviews);
		return "supplierReviews-view";
	}

	// ============= CREATE =============

	@Override
	@GetMapping("/new-supplier")
	public String getForm(ModelMap model) {
		return super.getForm(model);
	}

	@Override
	// SIGN UP AS SUPPLIER
	public String create(@Valid Supplier supplier, ModelMap model, BindingResult result) {
		return super.create(supplier,model,result);
	}

	// ============= MODIFY =============

	@Override
	public String modify(@PathVariable Integer id, ModelMap model) {
		return super.modify(id, model);
	}

	@Override
	// MODIFY SUPPLIER PROFILE
	public String modify(@Valid Supplier supplier, ModelMap model, BindingResult result) {
		return super.modify(supplier, model, result);
	}

	// ============= DELETE =============

	@Override
	public String delete(@PathVariable("id") Integer id, ModelMap model) {
		try {
			supplierService.delete(id);
			model.put("success", "supplier dissmissed successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/supplier";
	}
}
