package com.egg.services.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.egg.services.enums.Rol;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplier")
public final class Supplier extends Person {

	@OneToMany
	@JoinColumn(name = "review_id")
	private List<Review> reviews;


	@OneToMany
	@JoinColumn(name = "offering_id")
	private List<Offering> offerings;


	@NotEmpty
	private Boolean state;

	@NotBlank
	private String biography;

	public Supplier() {
		super();
		initializeDefaults();
	}

	public Supplier(String name, String lastname, String phoneNumber, String mail, String image, String password,
			@NotBlank String biography) {
		super(name, lastname, phoneNumber, mail, image, password, Rol.SUPPLIER);
		initializeDefaults();
	}

	private void initializeDefaults() {
		this.state = true;
		this.rol = Rol.SUPPLIER;
		this.offerings = new ArrayList<Offering>();
		this.reviews = new ArrayList<Review>();
	}

}

