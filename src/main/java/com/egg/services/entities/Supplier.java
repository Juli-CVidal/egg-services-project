package com.egg.services.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.egg.services.enums.Rol;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplier")
public final class Supplier extends Person {

	@NotEmpty
	@OneToMany
	private List<Offering> services;

	@NotEmpty
	@OneToMany
	private List<Review> reviews;

	@NotEmpty
	private ArrayList<Double> scores;

	@NotEmpty
	private Boolean state;

	@NotEmpty
	private String biography;

	public Supplier() {
		super();
		initializeDefaults();
	}

	public Supplier(String name, String lastname, String phoneNumber, String mail, String image,
			String password, String biography) {
		super(name, lastname, phoneNumber, mail, image, password, Rol.SUPPLIER);
		initializeDefaults();
	}
	
	
	private void initializeDefaults() {
		this.state = true;
		this.rol = Rol.SUPPLIER;
		this.services = new ArrayList<Offering>();
		this.reviews = new ArrayList<Review>();
		this.scores = new ArrayList<Double>();
	}

}
