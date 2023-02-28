package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.services.entities.Review;
import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends AccountController<Supplier>{

	@Autowired
	private SupplierService supplierService;
	
	public SupplierController(SupplierService service) {
		super(service,"supplier");
	}

	// ============= READ =============
	@GetMapping("/")
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
	@GetMapping("/profile/{id}")
    public String getProfile(@PathVariable("id")Integer id, ModelMap model) throws ServicesException {
        Supplier supplier = supplierService.getById(id);
        if (null == supplier) {
            throw new ServicesException("No supplier found");
        }
        model.put("supplier", supplier);
        return "supplier-profile";
    }

	// ============= CREATE =============

	@Override
	@GetMapping("/new-supplier")
	public String getForm(ModelMap model) {
		return super.getForm(model);
	}

	@Override
	// SIGN UP AS SUPPLIER
	@PostMapping("/new-supplier")
	public String create(@Valid Supplier supplier,@RequestParam("repeat") String repeat, ModelMap model, BindingResult result) {
		return super.create(supplier,repeat,model,result);
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

	@PostMapping("/delete")
	public String delete(@PathVariable("id") Integer id, ModelMap model) {
		try {
			supplierService.delete(id);
			model.put("success", "supplier dissmissed successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/supplier";
	}
	
    @Override
    protected void addAdditionalValidations(Supplier supplier, BindingResult result) {
        if (supplier.getBiography() == null || supplier.getBiography().isEmpty()) {
            result.rejectValue("biography", "field.required", "Biography is required");
        }
    }
}
