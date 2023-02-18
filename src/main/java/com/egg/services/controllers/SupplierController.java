package com.egg.services.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.services.entities.Supplier;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.SupplierService;

//IMPORTANT
//ALL OF THE ROUTES/FILENAMES CAN (AND POSSIBBLY) BE CHANGED

@Controller
@RequestMapping("/supplier")
public class SupplierController implements CrudController<Supplier> {

	@Autowired
	private SupplierService supplierService;

	@Override
	@GetMapping("/")
	// If the getAllMethod is modified to throw an exception, add the catch
	// Surrounding the first two lines
	public String getAll(ModelMap model) {
		List<Supplier> suppliers = supplierService.getAll();
		model.put("suppliers", suppliers);
		return "suppliers-view";
	}

	@Override
	@GetMapping("/save")
	// GetMapping is responsible for sending the form to perform the action.
	public String getForm(ModelMap model) {
		return "supplier-form";
	}

	@Override
	@PostMapping("/save")
	// PostMapping receives the form data and when validated it is sent to the
	// database.
	public String create(@Valid Supplier supplier, ModelMap model) {
		try {
			supplierService.create(supplier);
			model.put("success", "supplier added successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("supplier", supplier);
			return "supplier-form";

		}

		return "redirect:/supplier";
	}

	@Override
	@GetMapping("modify/{id}")
	// PathVariable is our way to obtain values sent through the url
	// the value we are looking for must be placed between braces
	public String modify(@PathVariable Integer id, ModelMap model) {
		try {
			Supplier supplier = supplierService.getById(id);
			model.put("supplier", supplier);
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			return "suppliers-view";
		}
		return "supplier-form";
	}

	@Override
	@PostMapping("/modify")
	public String modify(@Valid Supplier supplier, ModelMap model) {
		try {
			supplierService.update(supplier);
			model.put("success", "supplier modified successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("supplier", supplier);
			return "supplier-form";
		}
		return "redirect:/supplier";
	}

	@Override
	@PostMapping("/delete/{id}")
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
