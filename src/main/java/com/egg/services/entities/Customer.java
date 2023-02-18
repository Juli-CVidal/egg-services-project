package com.egg.services.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "customer")
public final class Customer extends Person {

	@NotEmpty
	private String direction;

	@NotEmpty
	private boolean state;

	public Customer() {
		super();
		rol = Rol.CUSTOMER;
	}

	public Customer(String name, String lastname, String phoneNumber, String mail, String image,
			String password, String direction) {
		super(name, lastname, phoneNumber, mail, image, password, Rol.CUSTOMER);
		this.state = true;
	}

}
