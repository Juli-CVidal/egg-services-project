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

import com.egg.services.entities.Offering;
import com.egg.services.exceptions.ServicesException;
import com.egg.services.services.OfferingService;

@Controller
@RequestMapping("/offering")
public class OfferingController implements CrudController<Offering> {

	@Autowired
	private OfferingService offeringService;

	@Override
	public String getAll(ModelMap model) {
		List<Offering> offerings = offeringService.getAll();
		model.put("offerings", offerings);
		return "offerings-view";
	}

	@Override
	public String getForm(ModelMap model) {
		return "offering-form";
	}

	@Override
	public String create(@Valid Offering offering, ModelMap model) {
		try {
			offeringService.create(offering);
			model.put("success", "offering added successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("offering", offering);
			return "offering-form";
		}

		return "redirect:/offering";
	}

	@Override
	public String modify(@PathVariable Integer id, ModelMap model) {
		try {
			Offering offering = offeringService.getById(id);
			model.put("offering", offering);
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			return "offerings-view";
		}
		return "offering-form";
	}

	@Override
	public String modify(@Valid Offering offering, ModelMap model) {
		try {
			offeringService.update(offering);
			model.put("success", "offering modified successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
			model.put("offering", offering);
			return "offering-form";
		}
		return "redirect:/offering";
	}

	@Override
	public String delete(@PathVariable("id") Integer id, ModelMap model) {
		try {
			offeringService.delete(id);
			model.put("success", "offering dissmissed successfully");
		} catch (ServicesException se) {
			model.put("error", se.getMessage());
		}
		return "redirect:/offering";
	}

}
