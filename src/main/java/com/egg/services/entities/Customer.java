package com.egg.services.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.egg.services.enums.Rol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public final class Customer extends Person {

	@NotBlank(message = "Please enter your street")
	private String street;

	@NotBlank(message = "Please select your neighborhood")
	private String neighborhood;

	@NotNull
	@Min(value = 0, message = "Please enter a valid height")
	private Integer height;

	@OneToMany
	@JoinColumn(name = "review_id")
	private List<Review> reviews;

	private boolean state;

	public Customer() {
		super();
		initializeDefaults();
	}

	public Customer(String name, String lastname, String phoneNumber, String mail, String image, String password,
			String street, String neighborhood, Integer height) {
		super(name, lastname, phoneNumber, mail, image, password, Rol.CUSTOMER);
		this.street = street;
		this.neighborhood = neighborhood;
		this.height = height;
		initializeDefaults();
	}

	private void initializeDefaults() {
		this.rol = Rol.CUSTOMER;
		this.reviews = new ArrayList<>();
		this.state = true;
	}

}
